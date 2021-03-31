package com.iptv.gupao.chapter3;

import java.util.concurrent.*;

/**
 * @author liuqi
 * @description: 使用Callable实现多线程
 * @create 2021-03-31 17:42
 */
public class CallableDemo implements Callable<String> {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建一个线程池，cache
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 创建一个Callable实现类实例
        CallableDemo callableDemo = new CallableDemo();

        // 线程池调用任务。使用submit可以接收返回值
        Future<String> submit = executorService.submit(callableDemo);

        // 获取返回值，此处阻塞
        String s = submit.get();
        System.out.println("s = " + s);

        // 关闭线程池
        executorService.shutdown();

    }

    @Override
    public String call() throws Exception {

        System.out.println("开始处理事务...");
        Thread.sleep(1000);
        System.out.println("处理事务完成...");

        return "使用多线程的call方法";
    }

}
