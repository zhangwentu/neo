package com.neo.socketclient;

import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.TioConfig;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;

import java.nio.ByteBuffer;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/3/5 13:06
 */
public class HelloClientAioHandler implements ClientAioHandler {
    private static MsgPacket heartbeatPacket = new MsgPacket();

    /**
     * 解码：把接收到的ByteBuffer，解码成应用可以识别的业务消息包
     * 总的消息结构：消息头 + 消息体
     * 消息头结构：    4个字节，存储消息体的长度
     * 消息体结构：   对象的json串的byte[]
     */
    @Override
    public MsgPacket decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws AioDecodeException {
        return PacketUtil.decode(buffer, limit, position, readableLength, channelContext);
    }

    /**
     * 编码：把业务消息包编码为可以发送的ByteBuffer
     * 总的消息结构：消息头 + 消息体
     * 消息头结构：    4个字节，存储消息体的长度
     * 消息体结构：   对象的json串的byte[]
     */
    @Override
    public ByteBuffer encode(Packet packet, TioConfig tioConfig, ChannelContext channelContext) {
        return PacketUtil.encode(packet, tioConfig, channelContext);
    }

    /**
     * 处理消息
     */
    @Override
    public void handler(Packet packet, ChannelContext channelContext) throws Exception {
        MsgPacket helloPacket = (MsgPacket) packet;
        byte[] body = helloPacket.getBody();
        if (body != null) {
            String str = new String(body, MsgPacket.CHARSET);
            System.out.println("收到消息：" + str);
        }
        return;
    }

    /**
     * 此方法如果返回null，框架层面则不会发心跳；如果返回非null，框架层面会定时发本方法返回的消息包
     */
    @Override
    public MsgPacket heartbeatPacket(ChannelContext channelContext) {
        return heartbeatPacket;
    }
}