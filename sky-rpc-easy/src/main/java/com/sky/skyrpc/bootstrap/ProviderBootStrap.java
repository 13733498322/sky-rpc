package com.sky.skyrpc.bootstrap;


import com.sky.skyrpc.RpcApplication;
import com.sky.skyrpc.config.RegistryConfig;
import com.sky.skyrpc.config.RpcConfig;
import com.sky.skyrpc.model.ServiceMetaInfo;
import com.sky.skyrpc.model.ServiceRegisterInfo;
import com.sky.skyrpc.registry.LocalRegistry;
import com.sky.skyrpc.registry.Registry;
import com.sky.skyrpc.registry.RegistryFactory;
import com.sky.skyrpc.server.tcp.VertxTcpServer;

import java.util.List;


/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 21:51
 * @description 服务提供者初始化
 */
public class ProviderBootStrap {

    /**
     * 初始化
     */
    public static void init(List<ServiceRegisterInfo<?>> serviceRegisterInfoList) {
        // RPC 框架初始化（配置和注册中心）
        RpcApplication.init();
        // 全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        // 注册服务
        for (ServiceRegisterInfo<?> serviceRegisterInfo : serviceRegisterInfoList) {
            String serviceName = serviceRegisterInfo.getServiceName();
            // 本地注册
            LocalRegistry.register(serviceName, serviceRegisterInfo.getImplClass());

            // 注册服务到注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
            try {
                registry.register(serviceMetaInfo);
            } catch (Exception e) {
                throw new RuntimeException(serviceName + " 服务注册失败", e);
            }
        }

        // 启动服务器
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(rpcConfig.getServerPort());
    }
}
