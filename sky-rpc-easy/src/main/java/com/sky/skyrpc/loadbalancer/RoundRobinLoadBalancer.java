package com.sky.skyrpc.loadbalancer;

import com.sky.skyrpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 19:39
 * @description 轮询负载均衡器
 */
public class RoundRobinLoadBalancer implements LoadBalancer{

    /**
     * 当前轮询下标
     */
    private final AtomicInteger currentIndex = new AtomicInteger();

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParmas, List<ServiceMetaInfo> serviceMetaInfoList) {
        if(serviceMetaInfoList.isEmpty()){
            return null;
        }
        //只有一个服务
        int size = serviceMetaInfoList.size();
        if(size==1){
            return serviceMetaInfoList.get(0);
        }

        //取模算法轮询
        int index = currentIndex.getAndIncrement() % size;
        return serviceMetaInfoList.get(index);
    }
}
