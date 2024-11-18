package com.sky.example.comsumer;

import com.sky.skyrpc.RpcApplication;
import com.sky.skyrpc.config.RpcConfig;
import com.sky.skyrpc.utils.ConfigUtils;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-18 10:38
 * @description 简易服务消费者示例
 */
public class ConsumerExample {
    public static void main(String[] args) {
        RpcConfig rpcConfig= ConfigUtils.loadConfig(RpcConfig.class,"rpc");
        System.out.println(rpcConfig);
    }
}
