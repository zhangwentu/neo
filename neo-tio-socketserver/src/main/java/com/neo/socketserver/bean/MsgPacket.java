package com.neo.socketserver.bean;

import org.tio.core.intf.Packet;

/**
 * <p>消息包实体</p>
 *
 * @author neo
 * @date 2020/3/5 12:15
 */
public class MsgPacket extends Packet {
    private static final long serialVersionUID = -172060606924066412L;
    public static final int HEADER_LENGTH = 4;//消息头的长度
    public static final String CHARSET = "utf-8";
    private byte[] body;

    /**
     * @return the body
     */
    public byte[] getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(byte[] body) {
        this.body = body;
    }
}
