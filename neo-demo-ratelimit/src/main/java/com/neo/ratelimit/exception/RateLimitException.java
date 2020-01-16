package com.neo.ratelimit.exception;

/**
 * <p>自定义接口是否可用异常</p>
 *
 * @author neo
 * @date 2019/2/19 09:51
 */
public class RateLimitException extends RuntimeException {

    @Override
    public String getMessage() {
        //TODO 文案
        return "超过请求限制";
    }
}
