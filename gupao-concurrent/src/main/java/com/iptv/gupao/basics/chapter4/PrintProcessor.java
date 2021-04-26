package com.iptv.gupao.basics.chapter4;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author liuqi
 * @description:
 * @create 2021-03-31 18:28
 */

public class PrintProcessor extends Thread implements RequestProcessor {


    /**
     * 定义一个阻塞队列
     */
    LinkedBlockingQueue<Request> linkedBlockingQueue = new LinkedBlockingQueue<>();

    /**
     * 使用final修饰必须给定初始值
     */
    private final RequestProcessor nextProcessor;

    /**
     * 构造器,可以使用@Data ->Lombok
     *
     * @param nextProcessor
     */
    public PrintProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }


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
                System.out.println("print data = " + request);
                // 使用下一个处理器处理这个request，形成一个链，称为责任链模式
                nextProcessor.processorRequest(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
