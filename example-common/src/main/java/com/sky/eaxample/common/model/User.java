package com.sky.eaxample.common.model;

import java.io.Serializable;
/**
 * @date 2024/11/17
 * @author 胖了又胖的胖凯
 * @description 用户
 */
public class User implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}