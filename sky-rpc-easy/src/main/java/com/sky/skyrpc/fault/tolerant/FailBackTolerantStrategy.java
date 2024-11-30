package com.sky.skyrpc.fault.tolerant;

import com.sky.skyrpc.model.RpcResponse;

import java.util.Map;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 21:31
 * @description 降级到其他服务 - 容错策略
 */
public class FailBackTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        // todo 可自行扩展，获取降级的服务调用
        return null;
    }
}
