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

    /**
     * 除了通过 Thread.interrupted 方法对线程中断标识进行复位以外，还有一种被
     * 动复位的场景，就是对抛出 InterruptedException 异常的方法，在
     * InterruptedException 抛出之前，JVM 会先把线程的中断标识位清除，然后才
     * 会抛出 InterruptedException，这个时候如果调用 isInterrupted 方法，将会
     * 返回 false
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main1(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    //抛出该异常，会将复位标识设置为 false
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();//设置复位标识为 true
        TimeUnit.SECONDS.sleep(1);
        System.out.println(thread.isInterrupted());//false
    }


    /**
     * 除了通过 interrupt 标识为去中断线程以外，我们还可以通过下面这种方式，
     * 定义一个 volatile 修饰的成员变量，来控制线程的终止。这实际上是应用了
     * volatile 能够实现多线程之间共享变量的可见性这一特点来实现的。
     */
    private volatile static boolean stop = false;

    public static void main2(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
        });
        thread.start();
        System.out.println("begin start thread");
        Thread.sleep(1000);
        stop = true;
    }
}
