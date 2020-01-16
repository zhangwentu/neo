package com.neo.ratelimit.annotation;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <p>@Description: 限流处理类，执行lua脚本记录访问次数</p>
 *
 * @author neo
 * @date 2019/2/20 5:24 PM
 */
@Service
public class RateLimitHandler implements InitializingBean {
    private final static Logger logger = LoggerFactory.getLogger(RateLimitHandler.class);

    @Value(value = "classpath:rate_limiter.lua")
    private Resource file;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private String RATE_LIMIT_LUA_SCRIPT;

    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化后加载lua脚本
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append(" ");
            }
            RATE_LIMIT_LUA_SCRIPT = sb.toString();
        } catch (Exception e) {
            logger.error("[限流] 初始化读取lua脚本异常！", e);
        } finally {
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (Exception e) {
                logger.error("[限流] 初始化读取lua脚本关闭流异常！", e);
            }
        }
    }

    public boolean access(String key, int maxPermits, int expire) {
        try {
            DefaultRedisScript<Boolean> script = new DefaultRedisScript<>(RATE_LIMIT_LUA_SCRIPT, Boolean.class);

            ImmutableList<String> keys = ImmutableList.of(key);

            Boolean result = redisTemplate.execute(script, keys, maxPermits, expire);
            if (null == result) {
                return true;
            }
            return result;
        } catch (Exception e) {
            logger.error("[限流] 执行限流lua脚本异常！", e);
        }
        return true;
    }
}
