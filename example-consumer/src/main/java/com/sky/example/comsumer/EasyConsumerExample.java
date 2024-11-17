package com.sky.example.comsumer;

import com.sky.eaxample.common.model.User;
import com.sky.eaxample.common.service.UserService;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-17 19:27
 * @description  简易服务消费者示例
 */
public class EasyConsumerExample {
    public static void main(String[] args) {
        UserService userService=null;
        User user=new User();
        user.setName("sky");
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}