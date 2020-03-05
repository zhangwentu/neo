package com.neo.socketserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tio.core.starter.annotation.EnableTioServerServer;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/3/5 11:31
 */
@SpringBootApplication
@EnableTioServerServer
public class TioServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TioServerApplication.class, args);
    }
}
