package com.sky.skyrpc.fault.retry;

import com.sky.skyrpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 20:55
 * @description 重试策略
 */
public interface RetryStrategy {

    /**
     * 重试
     * @param callable
     * @return
     * @throws Exception
     */
    RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception;
}
