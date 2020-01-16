package com.neo.strategy.service;

import com.neo.strategy.domain.OrderDTO;

/**
 * <p></p>
 *
 * @author neo
 * @date 2019/12/4 13:38
 */
public interface IOrderService {
    /**
     * 根据订单的不同类型作出不同的处理
     *
     * @param dto 订单实体
     * @return 为了简单，返回字符串
     */
    String handle(OrderDTO dto);
}
