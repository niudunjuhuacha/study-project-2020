package com.iptv.gupao.threadpoll.chapter4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liuqi
 * @description:
 * @create 2021-04-15 17:00
 */
public class ThreadPoolDemo1 {

    public static void main(String[] args) {
//        Executors.newFixedThreadPool(1) ;

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
                3,
                3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }


}
