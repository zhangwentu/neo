package com.neo.producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/1/19 13:16
 */
@Slf4j
@RestController
public class DemoController {
    @Value("${server.port}")
    private String port;

    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {
        log.info("[生产者服务] 接口测试 name:{}", name);
        return String.format("Hello %s ,服务[%s]被调用", name, port);
    }
}
