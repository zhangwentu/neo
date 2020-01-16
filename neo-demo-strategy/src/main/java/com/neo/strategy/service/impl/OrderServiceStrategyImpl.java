package com.neo.strategy.service.impl;

import com.neo.strategy.domain.OrderDTO;
import com.neo.strategy.handler.AbstractHandler;
import com.neo.strategy.handler.HandlerContext;
import com.neo.strategy.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>策略模式处理</p>
 *
 * @author neo
 * @date 2019/12/4 14:16
 */
@Service
public class OrderServiceStrategyImpl implements IOrderService {

    @Autowired
    private HandlerContext handlerContext;

    @Override
    public String handle(OrderDTO dto) {
        AbstractHandler handler = handlerContext.getInstance(dto.getType());
        return handler.handle(dto);
    }

}
