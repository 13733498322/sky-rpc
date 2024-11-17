package com.sky.example.provider;

import com.sky.eaxample.common.model.User;
import com.sky.eaxample.common.service.UserService;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-17 19:24
 * @description 用户服务实现类
 */
public class UerServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名： "+user.getName());
        return user;
    }
}
