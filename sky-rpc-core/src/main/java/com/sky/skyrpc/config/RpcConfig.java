package com.sky.skyrpc.config;

import lombok.Data;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-18 10:15
 * @description RPC 框架配置
 */
@Data
public class RpcConfig {
    /**
     * 名称
     */
    private String name = "sky-rpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;
}