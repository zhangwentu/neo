package com.neo.ratelimit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p></p>
 *
 * @author neo
 * @date 2019/12/30 16:26
 */
@SpringBootApplication(scanBasePackages = {"com.neo.ratelimit"})
public class RateLimitApplication {
    public static void main(String[] args) {
        SpringApplication.run(RateLimitApplication.class, args);
    }

}
