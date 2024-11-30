package com.sky.skyrpc.loadbalancer;

import com.sky.skyrpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 19:44
 * @description 随机负载均衡器
 */
public class RandomLoadBalancer implements LoadBalancer{

    private final Random random = new Random();

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParmas, List<ServiceMetaInfo> serviceMetaInfoList) {
        int size = serviceMetaInfoList.size();
        if(size==0){
            return null;
        }

        //只有一个服务，不用随机
        if(size == 1){
            return serviceMetaInfoList.get(0);
        }

        return serviceMetaInfoList.get(random.nextInt(size));
    }
}
