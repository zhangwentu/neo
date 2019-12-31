package com.neo.ratelimit.annotation;

/**
 * <p>@Description: 限速频率枚举类，计算过期时间</p>
 *
 * @author neo
 * @date 2019/2/2 3:06 PM
 */
public enum RateLimitIntervalTypeEnum {
    SECOND(1, 1),
    MINUTE(2, 60),
    HOUR(3, 60 * 60),
    DAY(4, 24 * 60 * 60);

    public static RateLimitIntervalTypeEnum findByType(int type) {
        for (RateLimitIntervalTypeEnum frequencyEnum : RateLimitIntervalTypeEnum.values()) {
            if (frequencyEnum.getType() == type) {
                return frequencyEnum;
            }
        }
        return null;
    }

    RateLimitIntervalTypeEnum(int type, int second) {
        this.type = type;
        this.second = second;
    }

    private int type;
    private int second;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
