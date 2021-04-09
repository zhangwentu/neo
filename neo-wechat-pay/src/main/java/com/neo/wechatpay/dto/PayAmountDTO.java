package com.neo.wechatpay.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * <p></p>
 *
 * @author neo
 * @date 2021/4/8 14:19
 */
@Data
public class PayAmountDTO {

    /**
     * 总金额
     * 订单总金额，单位为分
     * 是否必填：Y
     */
    @JSONField(name = "total")
    Integer total;

    /**
     * 货币类型
     * CNY：人民币，境内商户号仅支持人民币。
     * 是否必填：N
     */
    @JSONField(name = "currency")
    String currency;

    /**
     * 用户支付金额，单位为分。
     * 是否必填：Y
     */
    @JSONField(name = "payer_total")
    Integer payerTotal;

    /**
     * 用户支付币种
     * CNY：人民币，境内商户号仅支持人民币。
     * 是否必填：N
     */
    @JSONField(name = "payer_currency")
    String payerCurrency;
}
