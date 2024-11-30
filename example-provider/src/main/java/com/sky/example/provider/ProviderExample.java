package com.sky.example.provider;

import com.sky.eaxample.common.service.UserService;
import com.sky.skyrpc.RpcApplication;
import com.sky.skyrpc.config.RegistryConfig;
import com.sky.skyrpc.config.RpcConfig;
import com.sky.skyrpc.model.ServiceMetaInfo;
import com.sky.skyrpc.registry.LocalRegistry;
import com.sky.skyrpc.registry.Registry;
import com.sky.skyrpc.registry.RegistryFactory;
import com.sky.skyrpc.server.tcp.VertxTcpServer;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-29 20:58
 * @description 服务提供者示例
 */
public class ProviderExample {

    public static void main(String[] args) {
        //RPC框架初始化
        RpcApplication.init();

        //注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName,UserServiceImpl.class);

        //注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceMetaInfo.getServiceName());
        serviceMetaInfo.setServiceHost(serviceMetaInfo.getServiceHost());
        serviceMetaInfo.setServicePort(serviceMetaInfo.getServicePort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //启动TCP服务
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(8081);
    }

}
