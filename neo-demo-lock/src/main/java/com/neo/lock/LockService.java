package com.neo.lock;

import lombok.extern.slf4j.Slf4j;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/6/3 13:29
 */
@Slf4j
public class LockService {

    public static void doA(){
        log.info("this is function A");
    }

    public static void doB(){
        log.info("this is function B");
    }
}
