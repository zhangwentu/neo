package com.neo.socketserver.handler;

import com.neo.socketserver.bean.MsgPacket;
import com.neo.socketserver.utils.PacketUtil;
import org.tio.common.starter.annotation.TioServerMsgHandler;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.TioConfig;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

import java.nio.ByteBuffer;

/**
 * <p></p>
 * 消息处理 handler, 通过加 {@link TioServerMsgHandler} 注解启用，否则不会启用
 * 注意: handler 是必须要启用的，否则启动会抛出 {@link } 异常
 *
 * @author neo
 * @date 2020/3/5 11:42
 */
@TioServerMsgHandler
public class ServerMsgHandler implements ServerAioHandler {
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
            MsgPacket resppacket = new MsgPacket();
            resppacket.setBody(("收到了你的消息，你的消息是:" + str).getBytes(MsgPacket.CHARSET));
            Tio.send(channelContext, resppacket);
        }
        return;
    }
}