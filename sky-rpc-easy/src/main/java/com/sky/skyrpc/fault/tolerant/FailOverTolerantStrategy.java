package com.sky.skyrpc.fault.tolerant;

import com.sky.skyrpc.model.RpcResponse;

import java.util.Map;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 21:33
 * @description 转移到其他服务节点 - 容错策略
 */
public class FailOverTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        // todo 可自行扩展，获取其他服务节点并调用
        return null;
    }
}
