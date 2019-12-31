package com.neo.ratelimit.annotation;

import java.lang.annotation.*;

/**
 * <p>@Description: 限流注解</p>
 *
 * @author buxianguan
 * @date 2019/2/20 10:31 AM
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    /**
     * 允许访问的最大次数，默认值MAX_VALUE
     */
    int maxPermits() default Integer.MAX_VALUE;

    /**
     * 时间段间隔类型，默认值秒
     */
    RateLimitIntervalTypeEnum intervalType() default RateLimitIntervalTypeEnum.SECOND;

    /**
     * 时间段间隔系数，默认是1
     */
    int intervalFactor() default 1;
}
