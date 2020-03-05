package com.neo.socketclient;

import org.tio.client.ClientChannelContext;
import org.tio.client.ClientTioConfig;
import org.tio.client.ReconnConf;
import org.tio.client.TioClient;
import org.tio.client.intf.ClientAioHandler;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Node;
import org.tio.core.Tio;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/3/5 13:06
 */
public class HelloClientStarter {
    //服务器节点
    public static Node serverNode = new Node(Const.SERVER, Const.PORT);
    //handler, 包括编码、解码、消息处理
    public static ClientAioHandler tioClientHandler = new HelloClientAioHandler();
    //事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
    public static ClientAioListener aioListener = null;
    //断链后自动连接的，不想自动连接请设为null
    private static ReconnConf reconnConf = new ReconnConf(5000L);
    //一组连接共用的上下文对象
    public static ClientTioConfig clientTioConfig = new ClientTioConfig(tioClientHandler, aioListener, reconnConf);
    public static TioClient tioClient = null;
    public static ClientChannelContext clientChannelContext = null;

    /**
     * 启动程序入口
     */
    public static void main(String[] args) throws Exception {
        clientTioConfig.setHeartbeatTimeout(Const.TIMEOUT);
        tioClient = new TioClient(clientTioConfig);
        clientChannelContext = tioClient.connect(serverNode);
        //连上后，发条消息玩玩
        send("hello world");
        send("hello 2");
        send("hello 3");


    }

    private static void send(String msg) throws Exception {
        MsgPacket packet = new MsgPacket();
        packet.setBody(msg.getBytes(MsgPacket.CHARSET));
        Tio.send(clientChannelContext, packet);
    }
}