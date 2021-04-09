package com.neo.wechatpay.service;

import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.neo.wechatpay.dto.PayResponseDTO;
import com.neo.wechatpay.dto.PlaceOrderBodyDTO;
import com.neo.wechatpay.dto.PlaceOrderResponseDTO;
import com.neo.wechatpay.dto.SyncOrderBodyDTO;
import com.neo.wechatpay.util.WechatAuthUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@AllArgsConstructor
public class WechatPayServiceImpl implements WechatPayService {

    @Value("${mp.appId}")
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
}
