package com.sky.skyrpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import io.netty.util.internal.StringUtil;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-18 10:18
 * @description 配置工具类
 */
public class ConfigUtils {

    /**
     * 加载配置对象
     * @param tClass
     * @param prefix
     * @return
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> tClass,String prefix){
        return loadConfig(tClass,prefix,"");
    }


    /**
     * 加载配置对象，支持区分环境
     *
     * @param tClass
     * @param prefix
     * @param environment
     * @return
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> tClass,String prefix,String environment){
        StringBuilder configFileBuilder = new StringBuilder("application");
        if(StrUtil.isNotBlank(environment)){
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(tClass,prefix);
    }

    // TODO: 2024/11/18  扩展支持读取application.yml、application.yaml等不同格式的配置文件
//    public static <T> T loadConfig(Class<T> tClass,String prefix,String environment,String suffix){
//        StringBuilder configFileBuilder = new StringBuilder("application");
//        if(StrUtil.isNotBlank(environment)){
//            configFileBuilder.append("-").append(environment);
//        }
//        configFileBuilder.append(suffix);
//        Props props = new Props(configFileBuilder.toString());
//        return props.toBean(tClass,prefix);
//    }
}
