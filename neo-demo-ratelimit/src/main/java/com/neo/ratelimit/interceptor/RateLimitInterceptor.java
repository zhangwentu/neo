package com.neo.ratelimit.interceptor;

import com.neo.ratelimit.annotation.RateLimit;
import com.neo.ratelimit.annotation.RateLimitPolicy;
import com.neo.ratelimit.exception.RateLimitException;
import com.neo.ratelimit.utils.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * <p>@Description: 限流拦截器</p>
 *
 * @author neo
 * @date 2019/1/29 1:59 PM
 */
public class RateLimitInterceptor extends HandlerInterceptorAdapter {
    private final static Logger logger = LoggerFactory.getLogger(RateLimitInterceptor.class);

    @Autowired
    private RateLimitPolicy rateLimitPolicy;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 获取出方法上的 RateLimit 注解
        RateLimit rateLimit = method.getAnnotation(RateLimit.class);
        if (null == rateLimit) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }

        String ip = IPUtils.getIpAddr(request);
        String url = request.getRequestURI();

        /*
        //验证单ip是否超过限制
        boolean access = rateLimitPolicy.access(100, 1 * 60, ip);
        */

        //验证单ip单接口是否超过限制
        int maxPermits = rateLimit.maxPermits();
        int interval = rateLimit.intervalFactor() * rateLimit.intervalType().getSecond();

        boolean access = rateLimitPolicy.access(maxPermits, interval, ip, url);
        logger.info("[请求] IP:{} URL:{} access:{}",  ip, url, access);

        if (!access) {
            //抛出限流异常
            throw new RateLimitException();
        }

        return true;
    }
}