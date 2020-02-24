package com.neo.strategy.service.impl;

import com.neo.strategy.domain.OrderDTO;
import com.neo.strategy.handler.biz.DiscountHandler;
import com.neo.strategy.handler.biz.GroupHandler;
import com.neo.strategy.handler.biz.NormalHandler;
import com.neo.strategy.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>一般处理多重判断的方式</p>
 *
 * @author neo
 * @date 2019/12/4 14:16
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Resource(name = "normalHandler")
    private NormalHandler normalHandler;
    @Resource(name = "groupHandler")
    private GroupHandler groupHandler;
    @Resource(name = "discountHandler")
    private DiscountHandler discountHandler;

    @Override
    public String handle(OrderDTO dto) {
        int type = dto.getType();
        if (type == 0) {
            return normalHandler.handle(dto);
        } else if (type == 1) {
            return groupHandler.handle(dto);
        } else if (type == 2) {
            return discountHandler.handle(dto);
        }
        return null;
    }

}
