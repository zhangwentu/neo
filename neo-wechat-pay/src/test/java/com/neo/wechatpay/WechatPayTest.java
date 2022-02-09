package com.neo.wechatpay;

import com.alibaba.fastjson.JSON;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayBillResult;
import com.neo.wechatpay.dto.TradeBillParams;
import com.neo.wechatpay.service.WechatPayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

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
        params.setBillDate("20211209");
        params.setBillType("ALL");
        WxPayBillResult result = wechatPayService.getTradeBill(params);
        log.info(JSON.toJSONString(result));
//        log.info(JSON.toJSONString(wechatPayService.getTradeBillUrl(params)));

        log.info(JSON.toJSONString(result.getBillInfoList().stream()
                .filter(bill -> bill.getOutTradeNo().equals("DD716389462685659"))
                .collect(Collectors.toList())));
    }

    @Test
    public void testCreateOrderV3() {
        WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request();
        request.setAppid("wxc465dfb895d7e038");
        request.setMchid("1620168887");
        request.setDescription("测试商品测试测试");
        request.setOutTradeNo("11111111");
//        request.setTimeExpire("2022-05-20T13:29:35+08:00");
//        request.setAttach("附加数据");
        request.setNotifyUrl("https://gym.fanbei.vip/hermes/open");
        WxPayUnifiedOrderV3Request.Amount amount = new WxPayUnifiedOrderV3Request.Amount();
        amount.setCurrency("CNY");
        amount.setTotal(1);
        request.setAmount(amount);
        WxPayUnifiedOrderV3Request.Payer payer = new WxPayUnifiedOrderV3Request.Payer();
        payer.setOpenid("1212121");
        request.setPayer(payer);
        System.out.println(JSON.toJSONString(wechatPayService.creatOrderV3(request)));
    }
}
