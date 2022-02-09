package com.neo.wechatpay.service;

import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayBillResult;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import com.neo.wechatpay.dto.*;
import com.neo.wechatpay.util.WechatAuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>微信支付实现</p>
 *
 * @author neo
 * @date 2021/4/8 15:14
 */
@Service
@Slf4j
public class WechatPayServiceImpl implements WechatPayService {


    @Autowired
    private WxPayService wxPayService;


    @Override
    public WxPayMpOrderResult creatOrderV3(WxPayUnifiedOrderV3Request request) {
        try {
            log.info("[微信接口] 统一下单，请求 params:{}", JSON.toJSONString(request));
            WxPayMpOrderResult result = wxPayService.createOrderV3(TradeTypeEnum.JSAPI, request);
            log.info("[微信接口] 统一下单，响应 params:{},res:{}", JSON.toJSONString(result), JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            log.error("[微信接口] 统一下单异常 params:{}", JSON.toJSONString(request), e);
        }
        return null;
    }

    @Override
    public String getOrder() {
        return null;
    }

    @Override
    public String closeOrder() {
        return null;
    }

    @Override
    public PayResponseDTO syncOrder(SyncOrderBodyDTO syncOrderBodyDTO) {
        return null;
    }

    @Override
    public String refund() {
        return null;
    }

    @Override
    public String syncRefund() {
        return null;
    }

    @Override
    public WxPayBillResult getTradeBill(TradeBillParams params) {
        try {
            return wxPayService.downloadBill(params.getBillDate(), params.getBillType(), params.getTarType(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getTradeBillUrl(TradeBillParams params) {
        try {
            return wxPayService.downloadRawBill(params.getBillDate(), params.getBillType(), params.getTarType(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public String getTradeBill(TradeBillParams params) {
//        log.info("[微信接口] 获取账单，请求 params:{}", JSON.toJSONString(params));
//        if (StrUtil.isBlank(params.getBillDate())) {
//            log.error("[微信接口] 获取账单，请求参数异常 params:{}", JSON.toJSONString(params));
//            return null;
//        }
//        try {
//            String path = "v3/bill/tradebill?bill_date=" + params.getBillDate();
//            if (StrUtil.isNotBlank(params.getBillType())) {
//                path = path + "&bill_type=+" + params.getBillType();
//            }
//            if (StrUtil.isNotBlank(params.getTarType())) {
//                path = path + "&tar_type=" + params.getTarType();
//            }
//            long timestamp = System.currentTimeMillis() / 1000;
//            String nonceStr = UUID.fastUUID().toString(true);
//            String url = WECHAT_PAY_HOST + path;
//            String signature = WechatPayAuthUtils.getToken(HttpMethod.GET.toString(), url, null, nonceStr, wechatMchId, serialNo, privateKey);
//            HttpResponse response = HttpRequest.get(url)
//                    .header("Authorization", signature)
//                    .header("mchid", wechatMchId)
//                    .header("nonce_str", nonceStr)
//                    .header("timestamp", String.valueOf(timestamp))
//                    .header("serial_no", serialNo)
//                    .execute();
//            String res = response.body();
//            log.info("[微信接口] 获取账单，响应 params:{},res:{}", JSON.toJSONString(params), res);
//            return res;
//        } catch (Exception e) {
//            log.error("[微信接口] 获取账单 params:{}", JSON.toJSONString(params), e);
//        }
//        return null;
//    }
}
