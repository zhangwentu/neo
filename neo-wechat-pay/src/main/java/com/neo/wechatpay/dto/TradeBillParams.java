package com.neo.wechatpay.dto;

import lombok.Data;

/**
 * @author neo
 * @date 2021/12/9 11:15
 */
@Data
public class TradeBillParams {

    /**
     * 账单日期
     * query 格式YYYY-MM-DD
     * 仅支持三个月内的账单下载申请。
     * 示例值：2019-06-11
     */
    private String billDate;
    /**
     * 账单类型
     * query 不填则默认是ALL
     * 枚举值：
     * ALL：返回当日所有订单信息（不含充值退款订单）
     * SUCCESS：返回当日成功支付的订单（不含充值退款订单）
     * REFUND：返回当日退款订单（不含充值退款订单）
     * 示例值：ALL
     */
    private String billType;
    /**
     * 压缩类型
     * query 不填则默认是数据流
     * 枚举值：
     * GZIP：返回格式为.gzip的压缩包账单
     * 示例值：GZIP
     */
    private String tarType;
}
