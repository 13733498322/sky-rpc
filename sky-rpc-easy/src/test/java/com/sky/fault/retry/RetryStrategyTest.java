package com.sky.fault.retry;

import com.sky.skyrpc.fault.retry.FixedIntervalRetryStrategy;
import com.sky.skyrpc.fault.retry.NoRetryStrategy;
import com.sky.skyrpc.fault.retry.RetryStrategy;
import com.sky.skyrpc.model.RpcResponse;
import org.junit.Test;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 21:05
 * @description description
 */
public class RetryStrategyTest {
    RetryStrategy retryStrategy = new FixedIntervalRetryStrategy();

    @Test
    public void doRetry(){
        try {
            RpcResponse rpcResponse = retryStrategy.doRetry(()->{
                System.out.println("测试重试");
                throw  new RuntimeException("模拟重试失败");
            });
        } catch (Exception e) {
            System.out.println("重试多次失败");
            e.printStackTrace();
        }
    }
}
