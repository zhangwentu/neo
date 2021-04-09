package com.neo.wechatpay.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * <p></p>
 *
 * @author neo
 * @date 2021/4/8 14:23
 */
@Data
public class PayerDTO {

    /**
     * 用户标识
     * 用户在直连商户appid下的唯一标识。
     * 是否必填：Y
     */
    @JSONField(name = "openid")
    String openId;
}
