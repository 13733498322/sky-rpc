package com.sky.skyrpc.loadbalancer;

import com.sky.skyrpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 19:36
 * @description 负载均衡器（消费端使用）
 */
public interface LoadBalancer {

    /**
     * 选择服务调用
     * @param requestParmas   请求参数
     * @param serviceMetaInfoList   可用服务列表
     * @return
     */
    ServiceMetaInfo select(Map<String,Object> requestParmas, List<ServiceMetaInfo> serviceMetaInfoList);
}
