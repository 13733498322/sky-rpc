package com.sky.skyrpc.fault.retry;

import com.sky.skyrpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 20:57
 * @description 不重试 - 重试策略
 */
public class NoRetryStrategy implements RetryStrategy{

    /**
     *
     * 重试
     * @param callable
     * @return
     * @throws Exception
     */
    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        return callable.call();
    }
}
