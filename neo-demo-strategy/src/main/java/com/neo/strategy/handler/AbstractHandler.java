package com.neo.strategy.handler;

import com.neo.strategy.domain.OrderDTO;

/**
 * <p></p>
 *
 * @author neo
 * @date 2019/12/4 13:56
 */
public abstract class AbstractHandler {
    abstract public String handle(OrderDTO dto);
}
