package com.iptv.gupao.basics.chapter4;

import java.util.concurrent.TimeUnit;
/**
 * @author liuqi
 * @description: Thread.interrupted()
 * 线程中断后可以使用Thread.interrupted()进行复位
 * 将Thread.currentThread().isInterrupted()设置为false
 * @create 2021-04-22 22:44
 */
public class ThreadInterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread threadInterrupt = new Thread(() -> {
            while (true) {
                boolean in = Thread.currentThread().isInterrupted();
                if (in) {
                    System.out.println("before:" + in);
                    // 复位
                    Thread.interrupted();
                    // 复位后重新获取当前线程是否被中断
                    try {
                        System.out.println("开始睡2s");
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("after:" + Thread.currentThread().isInterrupted());
                }
            }
        }, "threadInterrupt");
        threadInterrupt.start();
        System.out.println("开始睡1s");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("睡眠结束");
        // 中断
        threadInterrupt.interrupt();
    }

}
