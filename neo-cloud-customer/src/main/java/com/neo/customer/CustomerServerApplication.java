package com.neo.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/1/16 15:32
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CustomerServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
