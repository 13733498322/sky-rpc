package com.sky.skyrpc.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import io.netty.util.internal.StringUtil;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.Map;

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
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        //先加载yml和yaml文件
        T configYmlAndYaml = loadConfigYmlAndYaml(tClass, "application.yaml", prefix);
        //再加载properties文件
        configFileBuilder.append(".properties");
        if(FileUtil.isEmpty(new File(configFileBuilder.toString()))){
            return configYmlAndYaml;
        }
        Props props = new Props(configFileBuilder.toString());
        T bean = props.toBean(tClass, prefix);
        BeanUtil.copyProperties(bean,configYmlAndYaml);
        return configYmlAndYaml;
    }

    public static <T> T loadConfigYmlAndYaml(Class<T> tClass,String filePath,String prefix){
        // 使用 Hutool 加载资源文件
        String yamlContent = ResourceUtil.readUtf8Str(filePath);

        // 解析 YAML 文件
        Yaml yaml = new Yaml();
        Map<String, Object> configMap = yaml.load(yamlContent);

        // 获取特定前缀下的配置
        Map<String, Object> config = (Map<String, Object>) configMap.get(prefix);
        //将配置转换特定的配置类
        T t = BeanUtil.mapToBean(config, tClass, true);

        return t;
    }

}
