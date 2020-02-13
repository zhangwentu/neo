package com.neo.zuul.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/2/11 17:17
 */
@RestController
public class ZuulTestController {

    @RequestMapping(value = "/testDemo")
    public String test(@RequestParam String name) {
        return "localhost server , hello " + name;
    }
}
