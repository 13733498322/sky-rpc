package com.sky.skyrpc.fault.tolerant;

import com.sky.skyrpc.model.RpcResponse;

import java.util.Map;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 21:26
 * @description 容错策略
 */
public interface TolerantStrategy {
    /**
     * 容错
     * @param context 上下文，用于传递数据
     * @param e       异常
     * @return
     */
    RpcResponse doTolerant(Map<String,Object> context , Exception e);
}
