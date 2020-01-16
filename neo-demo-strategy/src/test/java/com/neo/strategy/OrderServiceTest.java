package com.neo.strategy;

import com.neo.strategy.common.OrderTypeEnum;
import com.neo.strategy.domain.OrderDTO;
import com.neo.strategy.service.impl.OrderServiceImpl;
import com.neo.strategy.service.impl.OrderServiceStrategyImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * <p></p>
 *
 * @author neo
 * @date 2019/12/4 14:27
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StrategyApplication.class)
public class OrderServiceTest {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private OrderServiceStrategyImpl orderServiceStrategy;

    @Test
    public void handle() {
        String msg = orderService.handle(new OrderDTO("肥皂", new BigDecimal("1"), OrderTypeEnum.NORMAL.getType()));
        log.info(msg);
    }

    @Test
    public void handleStrategy() {
        String msg = orderServiceStrategy.handle(new OrderDTO("肥皂", new BigDecimal("0.1"), OrderTypeEnum.GROUP.getType()));
        log.info(msg);
    }
}
