package com.neo.customer.controller;

import com.neo.customer.service.IFeignService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FeignServiceController {
    @Autowired
    private IFeignService iFeignService;

    @RequestMapping(value = "/test")
    public String test(@RequestParam String name) {

        return iFeignService.hello(name);
    }
}
