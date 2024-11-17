package com.sky.skyrpc.server;

import io.vertx.core.Vertx;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-17 20:03
 * @description 编写基于 Vert.x 实现的 web 服务器 VertxHttpServer，能够监听指定端口并处理请求。
 */
public class VertxHttpServer implements HttpServer{

    /**
     * 启动服务器
     * @param port
     */
    @Override
    public void doStart(int port) {
        //创建vertx实例
        Vertx vertx= Vertx.vertx();

        // 创建 HTTP 服务器
        io.vertx.core.http.HttpServer httpServer= vertx.createHttpServer();

        //监听端口并处理请求
        httpServer.requestHandler(httpServerRequest -> {
            //处理http请求
            System.out.println("Received request: "+httpServerRequest.method());

            //发送HTTP请求
            httpServerRequest.response()
                    .putHeader("content-type","text/plain")
                    .end("Hello from Vert.x Http Server");
        });

        //启动HTTP服务器并监听指定端口
        httpServer.listen(port,result->{
           if(result.succeeded()){
               System.out.println("Server is now listening on port");
           }else{
               System.err.println("Failed to start server: "+result.cause());
           }
        });
    }
}
