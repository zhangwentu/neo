package com.neo.customer;

import com.neo.customer.service.IFeignService;
import com.neo.customer.service.RibbonRestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/2/11 17:14
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerServerApplication.class)
public class FeignServiceTest {

    @Autowired
    private IFeignService iFeignService;
    @Autowired
    private RibbonRestService ribbonRestService;
    @Autowired
    RestTemplate restTemplate;

    @Test
    public void handle() {
        String res = iFeignService.hello("test");
        log.info(res);
    }

    @Test
    public void ribbonTest() {
        log.info(ribbonRestService.test("1"));
        log.info(ribbonRestService.test("2"));
        log.info(ribbonRestService.test("3"));
        log.info(ribbonRestService.test("4"));
        log.info(ribbonRestService.test("5"));
        log.info(ribbonRestService.test("6"));

    }
    @Test
    public void testZuul() {
        String name = "zuul";
        String res = restTemplate.getForObject("http://zuul-server/test?name=" + name, String.class);
        log.info("网关本地路径转发访问:"+res);
        res = restTemplate.getForObject("http://zuul-server/producer-server/hello?name=" + name, String.class);
        log.info("根据serverId转发访问:"+res);
        res = restTemplate.getForObject("http://zuul-server/demo-producer-server/hello?name=" + name, String.class);
        log.info("自定义路径转发访问:"+res);
    }

}
