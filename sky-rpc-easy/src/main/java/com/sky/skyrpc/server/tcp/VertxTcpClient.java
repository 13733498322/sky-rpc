package com.sky.skyrpc.server.tcp;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-29 02:06
 * @description TCP服务客户端
 */
public class VertxTcpClient {
    public void start(){
        //创建Vertx实例
        Vertx vertx=Vertx.vertx();

        vertx.createNetClient().connect(8888,"localhost",result->{
            if(result.succeeded()){
                System.out.println("Connect to TCP server");
                NetSocket socket=result.result();
                //发送数据
                socket.write("Hello,server");
                //接收响应
                socket.handler(buffer -> {
                    System.out.println("Received response from sever: "+ buffer.toString());
                });
            }else {
                System.out.println("Failed to connect to TCP server");
            }
        });
    }

    public static void main(String[] args) {
        new VertxTcpClient().start();
    }
}
