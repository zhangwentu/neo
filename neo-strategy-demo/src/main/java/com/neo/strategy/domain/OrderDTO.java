package com.neo.strategy.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p></p>
 *
 * @author neo
 * @date 2019/12/4 13:32
 */
@Getter
@Setter
public class OrderDTO {

    /**
     * 订单商品
     */
    private String name;
    /**
     * 订单价格
     */
    private BigDecimal price;
    /**
     * 订单类型
     */
    private Integer type;

    public OrderDTO() {
    }

    public OrderDTO(String name, BigDecimal price, int type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

}
