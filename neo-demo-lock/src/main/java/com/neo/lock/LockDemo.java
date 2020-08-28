package com.neo.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/6/3 13:28
 */
@Slf4j
public class LockDemo {

    public static ExecutorService threadPool =Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        threadPool.execute(()->doLockA(lock));
        threadPool.execute(()->doLockB(lock));

    }

    private static void doLockA(Lock lock){
        lock.lock();
        try{
            LockService.doA();
            log.info("A方法要睡一会");
            Thread.sleep(5000);
            LockService.doA();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }
    private static void doLockB(Lock lock){
//        lock.lock();
        try{
            LockService.doB();
            LockService.doB();
        }finally{
//            lock.unlock();
        }
    }
}
