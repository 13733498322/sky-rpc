package com.sky.skyrpc.fault.retry;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 21:08
 * @description 重试策略键名常量
 */
public interface RetryStrategyKeys {
    /**
     * 不重试
     */
    String NO = "no";

    /**
     * 固定时间间隔
     */
    String FIXED_INTERVAL = "fixedInterval";
}
