package com.neo.ratelimit.controller;

import com.neo.ratelimit.annotation.RateLimit;
import com.neo.ratelimit.annotation.RateLimitIntervalTypeEnum;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author neo
 * @date 2019/12/30 17:42
 */
@RestController
@RequestMapping("/demo")
@Validated
public class DemoController {

    @GetMapping("/test")
    @ResponseBody
    @RateLimit(maxPermits = 10, intervalType = RateLimitIntervalTypeEnum.SECOND, intervalFactor = 60)
    public String test() {
        return "success";
    }
}
