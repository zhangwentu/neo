package com.neo.wechatpay.service;

import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.github.binarywang.wxpay.bean.result.WxPayBillResult;
import com.github.binarywang.wxpay.service.WxPayService;
import com.neo.wechatpay.dto.*;
import com.neo.wechatpay.util.WechatAuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

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

    @Value("${wechat.appId}")
    private String wechatAppId;
    @Value("${wechat.mchId}")
    private String wechatMchId;
    @Value("${wechat.apiV3Key}")
    private String privateKey;
    @Value("${wechat.serialNo}")
    private String serialNo;

    private static final String WECHAT_PAY_HOST = "https://api.mch.weixin.qq.com";

    @Override
    public PlaceOrderResponseDTO placeOrder(PlaceOrderBodyDTO placeOrderBodyDTO) {
        log.info("[微信接口] 统一下单，请求 params:{}", JSON.toJSONString(placeOrderBodyDTO));
        placeOrderBodyDTO.setAppId(wechatAppId);
        placeOrderBodyDTO.setMchId(wechatMchId);
        try {
            String path = "/v3/pay/transactions/jsapi";
            long timestamp = System.currentTimeMillis() / 1000;
            String nonceStr = UUID.fastUUID().toString(true);
            String body = JSON.toJSONString(placeOrderBodyDTO);
            String signature = WechatAuthUtils.getSign(HttpMethod.POST, path, timestamp, nonceStr, body);
            HttpResponse response = HttpRequest.post(WECHAT_PAY_HOST + path)
                    .body(body)
                    .header("Authorization", signature)
                    .header("mchid", wechatMchId)
                    .header("nonce_str", nonceStr)
                    .header("timestamp", String.valueOf(timestamp))
                    .header("serial_no", serialNo)
                    .execute();
            String res = response.body();
            log.info("[微信接口] 统一下单，响应 params:{},res:{}", JSON.toJSONString(placeOrderBodyDTO), res);
            return JSON.parseObject(res, PlaceOrderResponseDTO.class);
        } catch (Exception e) {
            log.error("[微信接口] 统一下单异常 params:{}", JSON.toJSONString(placeOrderBodyDTO), e);
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
