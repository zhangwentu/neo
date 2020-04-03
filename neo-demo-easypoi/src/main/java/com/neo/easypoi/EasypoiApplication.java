package com.neo.easypoi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p></p>
 *
 * @author neo
 * @date 2019/12/30 16:26
 */
@SpringBootApplication(scanBasePackages = {"com.neo.easypoi"})
public class EasypoiApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasypoiApplication.class, args);
    }

}
