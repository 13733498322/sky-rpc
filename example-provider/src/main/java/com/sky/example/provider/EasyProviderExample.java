package com.sky.example.provider;

import com.sky.eaxample.common.service.UserService;
import com.sky.skyrpc.RpcApplication;
import com.sky.skyrpc.config.RegistryConfig;
import com.sky.skyrpc.config.RpcConfig;
import com.sky.skyrpc.model.ServiceMetaInfo;
import com.sky.skyrpc.registry.LocalRegistry;
import com.sky.skyrpc.registry.Registry;
import com.sky.skyrpc.registry.RegistryFactory;
import com.sky.skyrpc.server.HttpServer;
import com.sky.skyrpc.server.VertxHttpServer;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-17 19:25
 * @description 简易服务提供者示例
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
