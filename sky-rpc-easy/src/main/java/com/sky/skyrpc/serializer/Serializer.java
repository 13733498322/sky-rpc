package com.sky.skyrpc.serializer;

import java.io.IOException;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-17 20:29
 * @description 序列化器接口
 */
public interface Serializer {

    /**
     * 序列化
     *
     * @param object
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * 反序列化
     *
     * @param bytes
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
}
