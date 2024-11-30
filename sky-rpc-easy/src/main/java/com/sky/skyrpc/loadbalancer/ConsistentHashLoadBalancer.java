package com.sky.skyrpc.loadbalancer;

import com.sky.skyrpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-30 19:47
 * @description 一致性哈希负载均衡器
 */
public class ConsistentHashLoadBalancer implements LoadBalancer{

    /**
     * 一致性Hash环，存放虚拟节点
     */
    private final TreeMap<Integer,ServiceMetaInfo> virtualNodes = new TreeMap<>();

    /**
     * 虚拟节点数
     */
    private static final int VIRTUAL_NODE_NUM = 100;

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParmas, List<ServiceMetaInfo> serviceMetaInfoList) {
        if(serviceMetaInfoList.isEmpty()){
            return null;
        }

        //构建虚拟节点环
        for (ServiceMetaInfo serviceMetaInfo : serviceMetaInfoList) {
            for(int i = 0; i < VIRTUAL_NODE_NUM; i ++){
                int hash = getHash(serviceMetaInfo.getServiceAddress() + "#" + i);
                virtualNodes.put(hash,serviceMetaInfo);
            }
        }

        //获取调用请求的 hash 值
        int hash = getHash(requestParmas);

        //选择最接近且大于等于调用请求 hash 值的虚拟节点
        Map.Entry<Integer, ServiceMetaInfo> entry = virtualNodes.ceilingEntry(hash);
        if(entry == null){
            //如果没有大于等于调用请求Hash值的虚拟节点，则返回环首部的节点
            entry = virtualNodes.firstEntry();
        }

        return entry.getValue();
    }

    /**
     * hash算法，可自行实现
     *
     * @param key
     * @return
     */
    private int getHash(Object key) {
        return key.hashCode();
    }
}