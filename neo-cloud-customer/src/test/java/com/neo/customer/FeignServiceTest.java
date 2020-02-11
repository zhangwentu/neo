package com.neo.customer;

import com.neo.customer.service.IFeignService;
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

    @Test
    public void handle() {
        String res = iFeignService.hello("test");
        log.info(res);
    }

}
