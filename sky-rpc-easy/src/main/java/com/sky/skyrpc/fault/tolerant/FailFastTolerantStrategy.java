package com.sky.skyrpc.fault.tolerant;

import com.sky.skyrpc.model.RpcResponse;

import java.util.Map;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 21:28
 * @description 快速失败 - 容错策略（立刻通知外层调用方）
 */
public class FailFastTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        throw new RuntimeException("服务报错",e);
    }
}
