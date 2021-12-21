package com.neo.udp.server;

import lombok.extern.slf4j.Slf4j;
import org.tio.core.Node;
import org.tio.core.udp.UdpPacket;
import org.tio.core.udp.intf.UdpHandler;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @author neo
 * @date 2021/12/16 16:07
 */
@Slf4j
public class UdpServerHandler implements UdpHandler {

    public UdpServerHandler() {
    }

    @Override
    public void handler(UdpPacket udpPacket, DatagramSocket datagramSocket) {
        byte[] data = udpPacket.getData();
        String msg = new String(data);
        Node remote = udpPacket.getRemote();

        log.info("收到来自{}的消息:【{}】", remote, msg);
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, new InetSocketAddress(remote.getIp(), remote.getPort()));
        try {
            datagramSocket.send(datagramPacket);
        } catch (Throwable e) {
            log.error(e.toString(), e);
        }
    }
}
