package com.sky.skyrpcspringbootstarter.annotation;

import com.sky.skyrpcspringbootstarter.bootstrap.RpcConsumerBootstrap;
import com.sky.skyrpcspringbootstarter.bootstrap.RpcInitBootstrap;
import com.sky.skyrpcspringbootstarter.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 23:03
 * @description 用于全局标识项目需要引入 RPC 框架、执行初始化方法。
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {

    /**
     * 需要启动 server
     *
     * @return
     */
    boolean needServer() default true;
}
