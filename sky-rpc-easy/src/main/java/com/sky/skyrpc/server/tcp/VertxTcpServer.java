package com.sky.skyrpc.server.tcp;

import com.sky.skyrpc.server.HttpServer;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-27 23:12
 * @description TCP服务服务端
 */
public class VertxTcpServer implements HttpServer {

    private byte[] handleRequest(byte[] requestData){
        //在这里编写处理请求的逻辑，更具requestData构造响应数据并返回
        //这里只是一个示例，实际逻辑需要根据具体的业务需求来实现
        return "Hello,client".getBytes();
    }
    @Override
    public void doStart(int port) {
        //创建Vert.x实例
        Vertx vertx=Vertx.vertx();

        //创建TCP服务器
        NetServer server=Vertx.vertx().createNetServer();

        //处理请求
        server.connectHandler(socket ->{
            //处理链接
            socket.handler(buffer -> {
                //处理接收到的字节数组
                byte[] requestData = buffer.getBytes();
                //在这里进行自定义的字节数组处理逻辑，比如解析请求、调用服务、构造响应等
                byte[] responsetData = handleRequest(requestData);
                //发送响应
                socket.write(Buffer.buffer(responsetData));
            });
        });

        //启动TCP服务器并监听指定端口
        server.listen(port,result->{
            if(result.succeeded()){
                System.out.println("TCP server started on port "+ port);
            }else {
                System.out.println("Failed to start TCP server "+ result.cause());
            }
        });
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}
