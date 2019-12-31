package com.neo.ratelimit.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * <p>@Description: 项目拦截器</p>
 * @author neo
 * @date 2019/2/2 5:37 PM
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // 限流拦截器
        registry.addInterceptor(getRateLimitInterceptor()).addPathPatterns("/**");

        super.addInterceptors(registry);
    }

    /**
     * 拦截器配置，为了拦截器中使用autowired
     */
    @Bean
    public HandlerInterceptor getRateLimitInterceptor() {
        return new RateLimitInterceptor();
    }


}
