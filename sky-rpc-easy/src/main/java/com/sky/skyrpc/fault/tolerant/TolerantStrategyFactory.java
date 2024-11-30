package com.sky.skyrpc.fault.tolerant;

import com.sky.skyrpc.spi.SpiLoader;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 21:35
 * @description 容错策略工厂（工厂模式，用于获取容错策略对象）
 */
public class TolerantStrategyFactory {

    static {
        SpiLoader.load(TolerantStrategy.class);
    }

    /**
     * 默认容错策略
     */
    private static final TolerantStrategy DEFAULT_RETRY_STRATEGY = new FailFastTolerantStrategy();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static TolerantStrategy getInstance(String key) {
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }

}
