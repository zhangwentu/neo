package com.neo.wechatpay.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Binary Wang
 */
@Configuration
@ConditionalOnClass(WxPayService.class)
public class WxPayConfiguration {
    @Value("${wechat.appId}")
    private String wechatAppId;
    @Value("${wechat.mchId}")
    private String wechatMchId;
    @Value("${wechat.apiV3Key}")
    private String privateKey;
    @Value("${wechat.serialNo}")
    private String serialNo;

    @Bean
    @ConditionalOnMissingBean
    public WxPayService wxService() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(StringUtils.trimToNull(wechatAppId));
        payConfig.setMchId(StringUtils.trimToNull(wechatMchId));
        payConfig.setMchKey(StringUtils.trimToNull(privateKey));
//        payConfig.setKeyPath(StringUtils.trimToNull(""));

        // 可以指定是否使用沙箱环境
        payConfig.setUseSandboxEnv(false);

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }

}
