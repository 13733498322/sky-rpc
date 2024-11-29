package com.sky.skyrpc.proxy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.sky.skyrpc.RpcApplication;
import com.sky.skyrpc.config.RpcConfig;
import com.sky.skyrpc.constant.RpcConstant;
import com.sky.skyrpc.model.RpcRequest;
import com.sky.skyrpc.model.RpcResponse;
import com.sky.skyrpc.model.ServiceMetaInfo;
import com.sky.skyrpc.protocol.*;
import com.sky.skyrpc.registry.Registry;
import com.sky.skyrpc.registry.RegistryFactory;
import com.sky.skyrpc.serializer.JdkSerializer;
import com.sky.skyrpc.serializer.Serializer;
import com.sky.skyrpc.serializer.SerializerFactory;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 服务代理（JDK 动态代理）
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @learn <a href="https://codefather.cn">编程宝典</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public class ServiceProxy implements InvocationHandler {

    /**
     * 调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        // 构造请求
        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);

            // 从注册中心获取服务提供者请求地址
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无服务地址");
            }
            ServiceMetaInfo selectedServiceMetaInfo = serviceMetaInfoList.get(0);

            //发送TCP请求
            Vertx vertx=Vertx.vertx();
            NetClient client=vertx.createNetClient();
            CompletableFuture<RpcResponse> responseFuture = new CompletableFuture<>();
            client.connect(selectedServiceMetaInfo.getServicePort()
                    ,selectedServiceMetaInfo.getServiceHost()
                    ,result -> {
                        if(result.succeeded()){
                            System.out.println("connect to TCP server");
                            NetSocket socket = result.result();
                            //发送数据
                            //构造数据
                            ProtocolMessage<RpcRequest> protocolMessage =new ProtocolMessage<>();
                            ProtocolMessage.Header header =new ProtocolMessage.Header();
                            header.setMagic(ProtocolConstant.PROTOCOL_MAGIC);
                            header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
                            header.setSerializer((byte)ProtocolMessageSerializerEnum
                                    .getEnumByValue(RpcApplication.getRpcConfig().getSerializer())
                                    .getKey());
                            header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
                            header.setRequestId(IdUtil.getSnowflakeNextId());
                            protocolMessage.setHeader(header);
                            protocolMessage.setBody(rpcRequest);

                            //编码请求
                            try {
                                Buffer encoderBuffer = ProtocolMessageEncoder.encode(protocolMessage);
                                socket.write(encoderBuffer);
                            } catch (IOException e) {
                                throw new RuntimeException("协议编码信息错误");
                            }

                            //接收响应
                            socket.handler(buffer -> {
                                try {
                                    ProtocolMessage<RpcResponse> rpcResponseProtocolMessage = (ProtocolMessage<RpcResponse>) ProtocolMessageDecoder.decode(buffer);
                                    responseFuture.complete(rpcResponseProtocolMessage.getBody());
                                } catch (IOException e) {
                                    throw new RuntimeException("协议解码错误");
                                }
                            });

                        }else {
                            System.out.println("Failed to connect to TCP server");
                        }
                    });
                RpcResponse rpcResponse = responseFuture.get();
                //记得关闭连接
                client.close();
                return rpcResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}