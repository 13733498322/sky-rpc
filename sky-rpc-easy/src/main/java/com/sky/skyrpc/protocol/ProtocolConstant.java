package com.sky.skyrpc.protocol;

/**
 * @author 胖了又胖的胖凯
 * @date 2024-11-27 22:32
 * @description 协议常量
 */
public interface ProtocolConstant {

    /**
     * 消息头长度
     */
    int MESSAGE_HEADER_LENGTH = 17;

    /**
     * 协议魔数
     */
    byte PROTOCOL_MAGIC = 0x1;

    /**
     * 协议版本号
     */
    byte PROTOCOL_VERSION = 0x1;
}
