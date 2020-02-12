package com.neo.customer;

import com.neo.customer.service.IFeignService;
import com.neo.customer.service.RibbonRestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void handle() {
        String res = iFeignService.hello("test");
        log.info(res);
    }

    @Test
    public void ribboTest() {
        log.info(ribbonRestService.test("1"));
        log.info(ribbonRestService.test("2"));
        log.info(ribbonRestService.test("3"));
        log.info(ribbonRestService.test("4"));
        log.info(ribbonRestService.test("5"));
        log.info(ribbonRestService.test("6"));

    }

}
