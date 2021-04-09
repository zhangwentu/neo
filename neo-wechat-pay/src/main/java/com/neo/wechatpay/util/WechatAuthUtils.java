package com.neo.wechatpay.util;

import cn.hutool.core.io.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import java.io.ByteArrayInputStream;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.Base64;

/**
 * <p></p>
 *
 * @author neo
 * @date 2021/4/8 17:26
 */
public class WechatAuthUtils {
    private static final Logger logger = LoggerFactory.getLogger(WechatAuthUtils.class);


    public static String getSign(HttpMethod method, String path, long timestamp, String nonceStr, String body) {
        String data = method + "\n"
                + path + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + body + "\n";
        return sign(data);
    }

    private static String sign(String data) {
        Signature sign;
        try {
            sign = Signature.getInstance("SHA256withRSA");
            //todo priKey常量化
            PrivateKey priKey = createPrivateKey();
            sign.initSign(priKey);
            sign.update(data.getBytes("utf-8"));
            return Base64.getEncoder().encodeToString(sign.sign());
        } catch (Exception e) {
            logger.error("执行加密异常", e);
        }
        return null;
    }

    private static String prikey = "-----BEGIN PRIVATE KEY-----" +
            "MIIC2zCCAcMCAQAwgZUxCzAJBgNVBAYTAkNOMRIwEAYDVQQID" +
            "AlHdWFuZ0RvbmcxETAPBgNVBAcMCFNoZW5aaGVuMRswGQYDVQQKD" +
            "BLlvq7kv6HllYbmiLfns7vnu58xLTArBgNVBAsMJOmdkuWym+i/qui" +
            "/que9kee7nOenkeaKgOaciemZkOWFrOWPuDETMBEGA1UEAwwKMTQ5MTU" +
            "4NDQ5MjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALTcrc" +
            "1lRf0sFnkD3HKDl5kUQR8sJ3bIBrIp0XLc7qsz4+E1UUoaOl3vTayCLR" +
            "DcSQBF5VyOZS4xi+0i0U+dWywqBFJXzPrbg6iPYWv/dTZzllO4YtlFdfi" +
            "BUrPajvLIxLC+c9ab5Sk2SEyWF40Rmuw4zFdbhCPLd46cH2QaMzn/TX5X2a" +
            "6lES3yHIhl1v2jeOIlMMJ+T07w/Cs0Fyqv0/plp6zginYCRBlRgavYDv65u4" +
            "7D9+zhkwfO1/YlohW8OQ82tojbr5TdyxfxsIomA7qhDhVxYjYEOuIY6WfIRRZ" +
            "OLcsefoFJ6C8pOBYDvK0cY1Hge563y+/ro9l98ntKr/UCAwEAAaAAMA0GCSq" +
            "GSIb3DQEBCwUAA4IBAQBe/GIlUT7cKNfyCMQjHIPOPHtbyqs4ie+VMNvh+/c" +
            "tJ/9kNIajt9CxnzySHdpUuTWHdbzvvkN/FGpJkP4DXNbOKT4MDC2Fvh1TU/U" +
            "6N7V7hVIFXE5PSAvkWWaUs3bXPxJS543oeLvUF+wO9HAzvku8pBFn/eR9858oT" +
            "5PuC9PFDaR8dMgICYbkz4aY6YvztyxQ7KzIPFuIdk1HfiL8Bccxw1TJCB1dg" +
            "K4uOl6+LWq9rFrTcF1CW2plvg+R9ABLUYrqllDgKUjAeoJu8U1ykqSRIM5rQIQ" +
            "Q+Sl/b2uMbsz2pJ+NHWaFla21z4gNZ9ou0p/d0G8J8Iy1gN4SY57DmFne" +
            "-----END PRIVATE KEY-----";

    private static PrivateKey createPrivateKey() {
        PrivateKey merchantPrivateKey = null;
        try {
//            merchantPrivateKey = PemUtil.loadPrivateKey(
//                    new ByteArrayInputStream(FileUtil.readBytes("/Users/zwt/Desktop/1491584492_cert.p12")));
            merchantPrivateKey = PemUtil.loadPrivateKey(
                    new ByteArrayInputStream(prikey.getBytes("utf-8")));
        } catch (Exception e) {
            logger.error("创建异常PrivateKey异常", e);
        }
        return merchantPrivateKey;
    }

//    public static void main(String[] args) {
//        X509Certificate certificate =PemUtil.loadCertificate(new ByteArrayInputStream(FileUtil.readBytes("/Users/zwt/Desktop/1491584492_cert.p12")));
//        System.out.println(new String(certificate.getSignature()));
//    }
}
