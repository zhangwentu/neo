package com.neo.small;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/5/7 14:57
 */
@SpringBootApplication(scanBasePackages = {"com.neo.small"})
public class SmallApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmallApplication.class, args);
    }

}
