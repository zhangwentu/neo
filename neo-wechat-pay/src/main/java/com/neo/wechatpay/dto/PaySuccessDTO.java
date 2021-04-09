package com.neo.wechatpay.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * <p></p>
 *
 * @author neo
 * @date 2021/4/8 14:48
 */
@Data
public class PaySuccessDTO {

    /**
     * 直连商户申请的公众号或移动应用appid。
     * 是否必填：Y
     */
    @JSONField(name = "appid")
    String appId;
    /**
     * 直连商户号
     * 直连商户的商户号，由微信支付生成并下发。
     * 是否必填：Y
     */
    @JSONField(name = "mchid")
    String mchId;

    /**
     * 商户订单号
     * 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一
     * 是否必填：Y
     */
    @JSONField(name = "out_trade_no")
    String outTradeNo;

    /**
     * 微信支付系统生成的订单号。
     * 是否必填：Y
     */
    @JSONField(name = "transaction_id")
    String transactionId;

    /**
     * 交易类型，
     * 枚举值：
     * JSAPI：公众号支付
     * NATIVE：扫码支付
     * APP：APP支付
     * MICROPAY：付款码支付
     * MWEB：H5支付
     * FACEPAY：刷脸支付
     * 是否必填：N
     */
    @JSONField(name = "trade_type")
    String tradeType;

    /**
     * 交易状态
     * 枚举值：
     * SUCCESS：支付成功
     * REFUND：转入退款
     * NOTPAY：未支付
     * CLOSED：已关闭
     * REVOKED：已撤销（付款码支付）
     * USERPAYING：用户支付中（付款码支付）
     * PAYERROR：支付失败(其他原因，如银行返回失败)
     * 是否必填：Y
     */
    @JSONField(name = "trade_state")
    String tradeState;

    /**
     * 交易状态描述
     * 示例值：支付成功
     * 是否必填：Y
     */
    @JSONField(name = "trade_state_desc")
    String tradeStateDesc;

    /**
     * 银行类型，采用字符串类型的银行标识。银行标识请参考《银行类型对照表》
     * 是否必填：Y
     */
    @JSONField(name = "bank_type")
    String bankType;

    /**
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
     * 是否必填：N
     */
    @JSONField(name = "attach")
    String attach;

    /**
     * 支付完成时间
     * 单失效时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
     * 是否必填：N
     */
    @JSONField(name = "success_time")
    String successTime;

    /**
     * 订单金额信息
     * 是否必填：Y
     */
    @JSONField(name = "amount")
    PayAmountDTO amount;

    /**
     * 支付者信息
     * 是否必填：Y
     */
    @JSONField(name = "payer")
    PayerDTO payer;

    /**
     * 场景信息
     * 是否必填：N
     */
    @JSONField(name = "scene_info")
    Object sceneInfo;

    /**
     * 优惠功能，享受优惠时返回该字段。
     * 是否必填：N
     */
    @JSONField(name = "promotion_detail")
    Object promotionDetail;


}
