package com.neo.wechatpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author neo
 * @date 2021/12/9 11:56
 */
@SpringBootApplication(scanBasePackages = {"com.neo.wechatpay"})
public class WechatPayApplication {
    public static void main(String[] args) {
        SpringApplication.run(WechatPayApplication.class, args);
    }
}
