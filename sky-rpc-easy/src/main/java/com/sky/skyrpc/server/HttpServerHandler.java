package com.sky.skyrpc.server;



import com.sky.skyrpc.model.RpcRequest;
import com.sky.skyrpc.model.RpcResponse;
import com.sky.skyrpc.registry.LocalRegistry;
import com.sky.skyrpc.serializer.JdkSerializer;
import com.sky.skyrpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;


/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-17 20:41
 * @description HTTP 请求处理
 * 1.反序列化请求为对象，并从请求对象中获取参数。
 * 2.根据服务名称从本地注册器中获取到对应的服务实现类。
 * 3.通过反射机制调用方法，得到返回结果
 * 4.对返回结果进行封装和序列化，并写入到响应中。
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {

    @Override
    public void handle(HttpServerRequest httpServerRequest) {
        //指定序列器
        final Serializer serializer=new JdkSerializer();
        //记录日志
        System.out.println("Received Request: "+ httpServerRequest.method()+" "+httpServerRequest.uri());

        //异步处理HTTP请求
        httpServerRequest.bodyHandler(body->{
            byte[] bytes=body.getBytes();
            RpcRequest rpcRequest=null;
            try {
                rpcRequest = serializer.deserialize(bytes, rpcRequest.getClass());
            } catch (IOException e) {
                e.printStackTrace();
            }

            //构造响应结果对象
            RpcResponse rpcResponse=new RpcResponse();
            //如果请求结果为null,直接返回
            if(rpcRequest == null){
                rpcResponse.setMessage("rcpRequest is null");
                doResponse(httpServerRequest,rpcResponse,serializer);
                return;
            }

            //获取要调用的服务实现类。通过反射调用
            try {
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
                //封装返回结果
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("ok");
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }

            //响应
            doResponse(httpServerRequest,rpcResponse,serializer);
        });
    }

    /**
     * 响应
     * @param httpServerRequest
     * @param rpcResponse
     * @param serializer
     */
    private void doResponse(HttpServerRequest httpServerRequest, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse=httpServerRequest.response()
                .putHeader("content-type","application/json");

        try {
            //序列化
            byte[] serialized = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }
    }
}
