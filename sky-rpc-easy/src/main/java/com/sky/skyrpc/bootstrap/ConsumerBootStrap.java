package com.sky.skyrpc.bootstrap;

import com.sky.skyrpc.RpcApplication;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 21:57
 * @description 服务消费者启动类（初始化）
 */
public class ConsumerBootStrap {

    /**
     * 初始化
     */
    public static void init(){
        //RPC框架初始化（配置和注册中心）
        RpcApplication.init();
    }
}
