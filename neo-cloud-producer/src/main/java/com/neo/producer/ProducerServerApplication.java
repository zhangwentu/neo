package com.neo.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/1/16 15:32
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProducerServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProducerServerApplication.class, args);
    }
}
