package com.neo.wechatpay.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;

import java.io.ByteArrayInputStream;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

/**
 * <p></p>
 *
 * @author neo
 * @date 2021/4/8 17:26
 */
@Slf4j
public class WechatPayAuthUtils {

    public static String getToken(String method, String url, String body, String nonceStr,
                                  String merchantId, String certificateSerialNo, String privateKey) {
        try {
            long timestamp = System.currentTimeMillis() / 1000;
            String message = buildMessage(method, HttpUrl.parse(url), timestamp, nonceStr, body);

            String signature = sign(message.getBytes("utf-8"), privateKey);
            String result = "mchid=\"" + merchantId + "\","
                    + "nonce_str=\"" + nonceStr + "\","
                    + "timestamp=\"" + timestamp + "\","
                    + "serial_no=\"" + certificateSerialNo + "\","
                    + "signature=\"" + signature + "\"";
            log.info("微信签名结果：{}", result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String sign(byte[] message, String privateKey) {
        Signature sign = null;
        try {
            sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(createPrivateKey(privateKey));
            sign.update(message);
            return Base64.getEncoder().encodeToString(sign.sign());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String buildMessage(String method, HttpUrl url, long timestamp, String nonceStr, String body) {
        String canonicalUrl = url.encodedPath();
        if (url.encodedQuery() != null) {
            canonicalUrl += "?" + url.encodedQuery();
        }
        String message = method + "\n"
                + canonicalUrl + "\n"
                + timestamp + "\n"
                + nonceStr + "\n";
        if (StrUtil.isNotBlank(body)) {
            message = message + body + "\n";
        }
        return message;
    }

    private static PrivateKey createPrivateKey(String prikey) {
        PrivateKey merchantPrivateKey = null;
        try {
            merchantPrivateKey = PemUtil.loadPrivateKey(
                    new ByteArrayInputStream(prikey.getBytes("utf-8")));
        } catch (Exception e) {
            log.error("创建异常PrivateKey异常", e);
        }
        return merchantPrivateKey;
    }

}
