package com.iptv.gupao.basics.chapter4;

import java.util.concurrent.TimeUnit;

/**
 * @author liuqi
 * @description: 模拟线程的各种状态，
 * • 打开终端或者命令提示符，键入“jps”，（JDK1.5 提供的一个显示当前所有 java
 * 进程 pid 的命令），可以获得相应进程的 pid
 * • 根据上一步骤获得的 pid，继续输入 jstack pid（jstack 是 java 虚拟机自带的
 * 一种堆栈跟踪工具。jstack 用于打印出给定的 java 进程 ID 或 core file 或远程
 * 调试服务的 Java 堆栈信息）
 * @create 2021-04-14 14:23
 */
public class ThreadStatusDemo {

    public static void main(String[] args) {

        // timeWaiting
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "timeWaiting").start();

        // wait
        new Thread(() -> {
            while (true) {
                synchronized (ThreadStatusDemo.class) {
                    try {
                        ThreadStatusDemo.class.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "wait").start();

        // 会随机有一个线程Block，线程执行顺序不确定
        new Thread(new BlockDemo(), "BlockDemo-0").start();
        new Thread(new BlockDemo(), "BlockDemo-1").start();

    }

    static class BlockDemo extends Thread{
        /**
         * 让线程一直睡着
         */
        @Override
        public void run(){
            synchronized (BlockDemo.class){
                while (true){
                    try {
                    	TimeUnit.SECONDS.sleep(100);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }


}
