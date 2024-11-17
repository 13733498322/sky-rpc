package com.sky.eaxample.common.service;

import com.sky.eaxample.common.model.User;

import java.io.Serializable;

/**
 * @author 胖了又胖的胖凯
 * @date 2024/11/17
 * @description 用户服务
 */
public interface UserService {

    /**
     * 获取用户
     * @param user
     */
    User getUser(User user);
}
