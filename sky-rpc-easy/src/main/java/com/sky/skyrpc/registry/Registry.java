package com.sky.skyrpc.registry;

import com.sky.skyrpc.config.RegistryConfig;
import com.sky.skyrpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-20 12:54
 * @description 注册中心
 */
public interface Registry {
    /**
     * 初始化
     * @param registryConfig
     */
    void init(RegistryConfig registryConfig);

    /**
     * 注册服务（服务端）
     * @param serviceMetaInfo
     * @throws Exception
     */
    void registry(ServiceMetaInfo serviceMetaInfo) throws Exception;

    /**
     * 注销服务（服务端）
     * @param serviceMetaInfo
     */
    void unRegistry(ServiceMetaInfo serviceMetaInfo);

    /**
     * 服务发现（获取某服务的所有节点，消费端）
     * @param serviceKey
     * @return
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    /**
     * 服务销毁
     */
    void destroy();
}
