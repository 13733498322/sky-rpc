package com.sky.skyrpc.registry;

import com.sky.skyrpc.spi.SpiLoader;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-20 14:36
 * @description 注册中心工厂（用于获取注册中心对象）
 */
public class RegistryFactory {


    static {
        SpiLoader.load(Registry.class);
    }

    /**
     * 默认注册中心
     */
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    public static Registry getInstance(String key){
        return SpiLoader.getInstance(Registry.class,key);
    }
}
