package com.neo.wechatpay.dto;

import lombok.Data;

/**
 * <p></p>
 *
 * @author neo
 * @date 2021/4/8 14:03
 */
@Data
public class PlaceOrderResponseDTO extends PayResponseDTO{

    /**
     * 预支付交易会话标识。用于后续接口调用中使用，该值有效期为2小时
     */
    String prepayId;
}
