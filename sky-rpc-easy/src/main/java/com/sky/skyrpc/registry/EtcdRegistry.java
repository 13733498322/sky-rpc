package com.sky.skyrpc.registry;

import com.sky.skyrpc.config.RegistryConfig;
import com.sky.skyrpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-20 12:59
 * @description description
 */
public class EtcdRegistry implements Registry{

    @Override
    public void init(RegistryConfig registryConfig) {

    }

    @Override
    public void registry(ServiceMetaInfo serviceMetaInfo) throws Exception {

    }

    @Override
    public void unRegistry(ServiceMetaInfo serviceMetaInfo) {

    }

    @Override
    public List<ServiceMetaInfo> serviceDiscovery(String serviceKey) {
        return null;
    }

    @Override
    public void destroy() {

    }
}
