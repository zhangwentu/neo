package com.neo.thread.future;

import cn.hutool.core.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/8/31 17:21
 */
public class FutureMain {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(8);
        final CountDownLatch countDownLatch = new CountDownLatch(20);
        List<Future<String>> results = new ArrayList<>();
        List<String> threadNames = getThreadName();
        threadNames.forEach(threadName -> {
            results.add(es.submit(() -> {
                int sleep = RandomUtil.randomInt(1, 10);
                try{
                    Thread.sleep(sleep * 1000);
                }finally {
                    countDownLatch.countDown();
                }
                return "threadName【" + threadName + "】:" + sleep;
            }));
            System.out.println("threadName【" + threadName + "】加载完成");
        });
        try {
            countDownLatch.await(10,TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        results.forEach(result -> {
            try {
//                result.isDone()
                System.out.println(result.get());
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        });

    }

    private static List<String> getThreadName() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + "");

        }
        return list;
    }


}
