package com.sky.skyrpc.fault.retry;

import com.sky.skyrpc.spi.SpiLoader;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 21:10
 * @description description
 */
public class RetryStrategyFactory {

    static {
        SpiLoader.load(RetryStrategy.class);
    }

    /**
     * 默认重试机制
     */
    private static final RetryStrategy DEFAULT_RETRY_STRATEGY = new NoRetryStrategy();


    /**
     * 获取实例
     * @param key
     * @return
     */
    public static RetryStrategy getInstance(String key){
        return SpiLoader.getInstance(RetryStrategy.class,key);
    }

}
