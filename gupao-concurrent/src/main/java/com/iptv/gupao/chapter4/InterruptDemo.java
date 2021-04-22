package com.iptv.gupao.chapter4;


import java.util.concurrent.TimeUnit;

/**
 * @author liuqi
 * @description:
 * @create 2021-04-22 22:14
 */
public class InterruptDemo {

    private static int i;

    public static void main(String[] args) throws InterruptedException {
        // 启动一个线程，让i不停++，直到线程被终止interrupt
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println(i);
        }, "interrupter");
        thread.start();
        // 睡1s，看i能加到多少
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
