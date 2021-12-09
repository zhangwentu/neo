package com.neo.wechatpay;

import com.alibaba.fastjson.JSON;
import com.github.binarywang.wxpay.bean.result.WxPayBillResult;
import com.neo.wechatpay.dto.TradeBillParams;
import com.neo.wechatpay.service.WechatPayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author neo
 * @date 2021/12/9 13:05
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WechatPayApplication.class)
public class WechatPayTest {

    @Autowired
    private WechatPayService wechatPayService;

    @Test
    public void handle() {
        TradeBillParams params = new TradeBillParams();
        params.setBillDate("20211208");
        params.setBillType("ALL");
        WxPayBillResult result = wechatPayService.getTradeBill(params);
        log.info(JSON.toJSONString(result));
//        log.info(JSON.toJSONString(wechatPayService.getTradeBillUrl(params)));

    }
}
