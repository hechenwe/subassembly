package com.sooncode.subassembly.manythread;

import java.util.concurrent.CountDownLatch;

public class Counter4 {
     
    public volatile static int count = 0;
    static CountDownLatch cdLatch  = new CountDownLatch(1000);
 
    //加上volatile试试，测试可不可以保证原子性（结果不可以）
    public static void inc() {
 
        // 这里延迟1毫秒，使得结果明显
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
        synchronized(Counter4.class){          
            count++;
        }
    }
 
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
         
 
        // 同时启动1000个线程，去进行i++计算，看看实际结果
 
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                CountDownLatch countDownLatch  = Counter4.cdLatch;
                @Override
                public void run() {
                    Counter4.inc();
                    countDownLatch.countDown();
                }
            }).start();
        }
         
        try {
            cdLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         
        // 这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter.count=" + Counter4.count);
        System.out.println(System.currentTimeMillis());
    }
}