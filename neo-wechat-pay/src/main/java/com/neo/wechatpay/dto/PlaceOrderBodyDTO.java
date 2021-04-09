package com.neo.wechatpay.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p></p>
 *
 * @author neo
 * @date 2021/4/8 14:03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderBodyDTO {

    /**
     * 应用ID
     * 直连商户申请的公众号appid
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
     * 商品描述
     * 是否必填：Y
     */
    @JSONField(name = "description")
    String description;

    /**
     * 商户订单号
     * 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一
     * 是否必填：Y
     */
    @JSONField(name = "out_trade_no")
    String outTradeNo;

    /**
     * 交易结束时间
     * 单失效时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
     * 是否必填：N
     */
    @JSONField(name = "time_expire")
    String timeExpire;

    /**
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
     * 是否必填：N
     */
    @JSONField(name = "attach")
    String attach;

    /**
     * 通知地址
     * 知URL必须为直接可访问的URL，不允许携带查询串，要求必须为https地址。
     * 是否必填：Y
     */
    @JSONField(name = "notify_url")
    String notifyUrl;

    /**
     * 订单优惠标记
     * 是否必填：N
     */
    @JSONField(name = "goods_tag")
    String goodsTag;

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
     * 优惠功能
     * 是否必填：N
     */
    @JSONField(name = "detail")
    Object detail;

    /**
     * 场景信息
     * 是否必填：N
     */
    @JSONField(name = "scene_info")
    Object sceneInfo;

    /**
     * 结算信息
     * 是否必填：N
     */
    @JSONField(name = "settle_info")
    Object settleInfo;
}
