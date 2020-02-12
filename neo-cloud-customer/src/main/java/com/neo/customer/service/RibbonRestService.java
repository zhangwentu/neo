package com.neo.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/2/12 15:58
 */
@Service
public class RibbonRestService {
    @Autowired
    RestTemplate restTemplate;

    public String test(String name) {
        //这里因为注册到了注册中心，域名可以直接使用对应服务的 application name
        return restTemplate.getForObject("http://producer-server/hello?name=" + name, String.class);
    }
}
