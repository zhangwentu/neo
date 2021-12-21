package com.neo.udp.server;

import org.tio.core.udp.UdpServer;
import org.tio.core.udp.UdpServerConf;

import java.net.SocketException;

/**
 * @author neo
 * @date 2021/12/16 16:10
 */
public class UdpServerStarter {

    public static void main(String[] args) throws SocketException {
        UdpServerHandler fpmsUdpHandler = new UdpServerHandler();
        UdpServerConf udpServerConf = new UdpServerConf(3000, fpmsUdpHandler, 5000);
        UdpServer udpServer = new UdpServer(udpServerConf);
        udpServer.start();
    }
}
