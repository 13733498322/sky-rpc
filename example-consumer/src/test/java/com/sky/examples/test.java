package com.sky.examples;

import com.sky.skyrpc.config.RpcConfig;
import com.sky.skyrpc.spi.SpiLoader;
import com.sky.skyrpc.utils.ConfigUtils;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-20 14:41
 * @description description
 */
public class test {
    public static void main(String[] args) {
        System.out.println(ConfigUtils.loadConfig(RpcConfig.class, "rpc"));
    }
}
