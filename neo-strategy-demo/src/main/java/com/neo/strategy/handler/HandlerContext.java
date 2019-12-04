package com.neo.strategy.handler;

import com.neo.strategy.common.OrderTypeEnum;
import com.neo.strategy.utils.BeanTool;

import java.util.Map;

/**
 * <p>处理器上下文，根据类型获取相应的处理器</p>
 *
 * @author neo
 * @date 2019/12/4 13:57
 */
public class HandlerContext {
    private Map<OrderTypeEnum, Class> handlerMap;

    public HandlerContext(Map<OrderTypeEnum, Class> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public AbstractHandler getInstance(int type) {
        Class clazz = handlerMap.get(OrderTypeEnum.findByType(type));
        if (clazz == null) {
            throw new IllegalArgumentException("not found handler for type: " + type);
        }
        return (AbstractHandler) BeanTool.getBean(clazz);
    }
}
