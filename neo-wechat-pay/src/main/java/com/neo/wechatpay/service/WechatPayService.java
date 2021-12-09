package com.neo.wechatpay.service;

import com.github.binarywang.wxpay.bean.result.WxPayBillResult;
import com.neo.wechatpay.dto.*;

/**
 * <p>微信支付相关业务</p>
 *
 * @author neo
 * @date 2021/4/8 13:47
 */
public interface WechatPayService {

    /**
     * 统一下单
     *
     * @param placeOrderBodyDTO 下单信息
     * @return PlaceOrderResponseDTO  响应信息
     */
    PlaceOrderResponseDTO placeOrder(PlaceOrderBodyDTO placeOrderBodyDTO);

    /**
     * 查询订单
     */
    String getOrder();

    /**
     * 关闭订单
     */
    String closeOrder();

    /**
     * 支付结果通知
     *
     * @param syncOrderBodyDTO 微信的回调信息
     * @return 给微信的响应
     */
    PayResponseDTO syncOrder(SyncOrderBodyDTO syncOrderBodyDTO);

    /**
     * 申请退款
     * todo
     */
    String refund();

    /**
     * 退款结果通知
     * todo
     */
    String syncRefund();


    /**
     * 申请交易账单
     *
     * @param params 参数
     * @return 响应
     */
    WxPayBillResult getTradeBill(TradeBillParams params);

    /**
     * 申请交易账单
     *
     * @param params 参数
     * @return 响应链接
     */
    String getTradeBillUrl(TradeBillParams params);
}
