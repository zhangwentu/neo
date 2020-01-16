package com.neo.strategy.common;

/**
 * <p>订单类型枚举</p>
 *
 * @author neo
 * @date 2019/12/4 14:46
 */
public enum OrderTypeEnum {
    /***/
    NORMAL(0, "普通订单"),
    GROUP(1, "团购订单"),
    DISCOUNT(2, "打折订单");

    OrderTypeEnum(int type, String message) {
        this.type = type;
        this.message = message;
    }

    private int type;
    private String message;

    public static OrderTypeEnum findByType(int type) {
        for (OrderTypeEnum orderType : OrderTypeEnum.values()) {
            if (orderType.getType() == type) {
                return orderType;
            }
        }
        return null;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
