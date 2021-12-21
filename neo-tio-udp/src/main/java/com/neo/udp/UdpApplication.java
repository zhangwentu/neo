package com.neo.udp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author neo
 * @date 2021/12/16 15:48
 */
@SpringBootApplication(scanBasePackages = {"com.neo.udp"})
public class UdpApplication {
    public static void main(String[] args) {
        SpringApplication.run(UdpApplication.class, args);
    }

}
