package com.sky.example.provider;

import com.sky.eaxample.common.service.UserService;
import com.sky.skyrpc.registry.LocalRegistry;
import com.sky.skyrpc.server.HttpServer;
import com.sky.skyrpc.server.VertxHttpServer;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-17 19:25
 * @description 简易服务提供者示例
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        //注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);

        //启动web
        HttpServer httpServer=new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
