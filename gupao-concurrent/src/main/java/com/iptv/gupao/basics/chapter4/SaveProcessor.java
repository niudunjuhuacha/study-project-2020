package com.iptv.gupao.basics.chapter4;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author liuqi
 * @description:
 * @create 2021-04-01 18:34
 */
public class SaveProcessor extends Thread implements RequestProcessor {

    /**
     * 定义一个阻塞队列
     */
    LinkedBlockingQueue<Request> linkedBlockingQueue = new LinkedBlockingQueue<>();


    @Override
    public void processorRequest(Request request) {
        linkedBlockingQueue.add(request);
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 从队列里取一个request，阻塞队列的使用方法，可见互联网相关技术脑图，7.BlockingQueue(接口)的核心方法
                Request request = linkedBlockingQueue.take();
                System.out.println("save data = " + request);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
