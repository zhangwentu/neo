package com.neo.small.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/5/7 15:03
 */
public class JVMDemo {

    public static ExecutorService threadPool =Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        threadPool.execute(()->{
            setValue();
            countDownLatch.countDown();
        });
        threadPool.execute(()->{
            setValue();
            countDownLatch.countDown();

        });
        threadPool.execute(()->{
            setValue();
            countDownLatch.countDown();

        });
        threadPool.execute(()->{
            setValue();
            countDownLatch.countDown();

        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setValue();
    }

    private static void setValue(){
        List<String> memeryB = new ArrayList<>();
        int count = 0;

        while (count++ < 10000) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            memeryB.add("NO MATCH");
            System.out.println("NO MATCH :" + count);
        }
    }
}
