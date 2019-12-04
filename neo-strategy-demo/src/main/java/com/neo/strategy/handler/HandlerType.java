package com.neo.strategy.handler;

import com.neo.strategy.common.OrderTypeEnum;

import java.lang.annotation.*;

/**
 * <p></p>
 *
 * @author neo
 * @date 2019/12/4 14:00
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HandlerType {

    OrderTypeEnum value();

}
