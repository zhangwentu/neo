package com.neo.ratelimit.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>@Description: 限流策略类</p>
 *
 * @author neo
 * @date 2019/2/20 3:53 PM
 */
@Service
public class RateLimitPolicy {

    @Autowired
    private RateLimitHandler rateLimitHandler;

    /**
     * 限流redis缓存key前缀
     */
    private final static String CACHE_RATE_LIMIT_KEY_PRE = "api:rate:limit";

    /**
     * @param maxPermits 最大限制次数
     * @param expire     过期时间，单位秒
     * @param suffixes   key后缀参数，用于拼接key
     */
    public boolean access(int maxPermits, int expire, String... suffixes) {
        String key = getKey(suffixes);
        return rateLimitHandler.access(key, maxPermits, expire);
    }

    private String getKey(String... suffixes) {
        StringBuilder key = new StringBuilder(CACHE_RATE_LIMIT_KEY_PRE);
        for (String suffix : suffixes) {
            key.append(":").append(suffix);
        }
        return key.toString();
    }
}
