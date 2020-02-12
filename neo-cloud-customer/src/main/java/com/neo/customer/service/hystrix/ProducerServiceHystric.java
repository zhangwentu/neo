package com.neo.customer.service.hystrix;

import com.neo.customer.service.IFeignService;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/2/12 16:43
 */
@Component
public class ProducerServiceHystric implements IFeignService {
    @Override
    public String hello(String name) {
        return "sorry " + name;
    }
}
