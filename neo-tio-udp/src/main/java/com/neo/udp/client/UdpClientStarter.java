package com.neo.udp.client;

import org.tio.core.udp.UdpClient;
import org.tio.core.udp.UdpClientConf;

/**
 * @author neo
 * @date 2021/12/16 16:18
 */
public class UdpClientStarter {

    public static void main(String[] args) {
        UdpClientConf udpClientConf = new UdpClientConf("192.168.0.88", 1234, 5000);
        UdpClient udpClient = new UdpClient(udpClientConf);
        udpClient.start();
        long start = System.currentTimeMillis();
        String str = "get_single\n";
//        String str = "update_epc e28068900000400a23b36092 e28068900000400a23b36666\n";
        udpClient.send(str);
        long end = System.currentTimeMillis();
        long iv = end - start;
        System.out.println("耗时:" + iv + "ms");
    }

//    public static void main(String[] args) {
//        UdpClientConf udpClientConf = new UdpClientConf("127.0.0.1", 3000, 5000);
//        UdpClient udpClient = new UdpClient(udpClientConf);
//        udpClient.start();
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 1000; i++) {
//            String str = i + "、" + "用tio开发udp，有点意思";
//            udpClient.send(str.getBytes());
//        }
//        long end = System.currentTimeMillis();
//        long iv = end - start;
//        System.out.println("耗时:" + iv + "ms");
//    }
}
