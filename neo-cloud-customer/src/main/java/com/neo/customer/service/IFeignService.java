package com.neo.customer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/2/11 17:11
 */
@FeignClient(name = "producer-server")
public interface IFeignService {
    @RequestMapping("/hello")
    String hello(@RequestParam(name = "name") String name);
}
