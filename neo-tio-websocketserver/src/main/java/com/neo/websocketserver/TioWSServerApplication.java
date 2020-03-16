package com.neo.websocketserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tio.websocket.starter.EnableTioWebSocketServer;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/3/5 11:31
 */
@SpringBootApplication
@EnableTioWebSocketServer
public class TioWSServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TioWSServerApplication.class, args);
    }
}
