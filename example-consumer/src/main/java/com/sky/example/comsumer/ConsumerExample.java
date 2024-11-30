package com.sky.example.comsumer;

import com.sky.eaxample.common.model.User;
import com.sky.eaxample.common.service.UserService;
import com.sky.skyrpc.bootstrap.ConsumerBootStrap;
import com.sky.skyrpc.config.RpcConfig;
import com.sky.skyrpc.proxy.ServiceProxyFactory;
import com.sky.skyrpc.utils.ConfigUtils;

/**
 * @author 胖了又胖的胖凯
 * @date 2024/11/18
 * @description 简易服务消费者示例
 */

public class ConsumerExample {

    public static void main(String[] args) {
        //服务提供者初始化
        ConsumerBootStrap.init();
        // 获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("sky");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
        long number = userService.getNumber();
        System.out.println(number);
    }
}

