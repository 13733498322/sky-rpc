package com.sky.skyrpc.fault.tolerant;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 21:34
 * @description 容错策略键名常量
 */
public interface TolerantStrategyKeys {

    /**
     * 故障恢复
     */
    String FAIL_BACK = "failBack";

    /**
     * 快速失败
     */
    String FAIL_FAST = "failFast";

    /**
     * 故障转移
     */
    String FAIL_OVER = "failOver";

    /**
     * 静默处理
     */
    String FAIL_SAFE = "failSafe";

}
