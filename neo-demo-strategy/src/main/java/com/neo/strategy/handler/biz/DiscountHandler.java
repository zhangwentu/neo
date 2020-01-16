package com.neo.strategy.handler.biz;

import com.neo.strategy.common.OrderTypeEnum;
import com.neo.strategy.domain.OrderDTO;
import com.neo.strategy.handler.AbstractHandler;
import com.neo.strategy.handler.HandlerType;
import org.springframework.stereotype.Component;

/**
 * <p>打折订单处理器</p>
 *
 * @author neo
 * @date 2019/12/4 14:09
 */
@Component
@HandlerType(OrderTypeEnum.DISCOUNT)
public class DiscountHandler extends AbstractHandler {

    @Override
    public String handle(OrderDTO dto) {
        return "处理打折订单:" + dto.getName() + " 价格[" + dto.getPrice().toPlainString() + "]";
    }

}
