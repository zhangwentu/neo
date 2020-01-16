package com.neo.strategy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p></p>
 *
 * @author neo
 * @date 2019/12/4 11:07
 */
@SpringBootApplication(scanBasePackages = { "com.neo.strategy" })
public class StrategyApplication {
    public static void main(String[] args) {
        SpringApplication.run(StrategyApplication.class, args);
    }
}
