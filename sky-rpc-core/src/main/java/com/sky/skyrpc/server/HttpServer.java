package com.sky.skyrpc.server;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-17 19:33
 * @description Http 服务器接口
 */
public interface HttpServer {

    /**
     * 启动服务器
     * @param port
     */
    void doStart(int port);
}
