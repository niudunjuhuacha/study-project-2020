package com.iptv.gupao.chapter4;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author liuqi
 * @description:
 * @create 2021-03-31 18:28
 */

public class PrintProcessor extends Thread implements RequestProcessor {


    /**
     *  定义一个阻塞队列
     */
    LinkedBlockingQueue<Request> linkedBlockingQueue = new LinkedBlockingQueue<>();

    private final RequestProcessor nextProcessor;

    /**
     * 构造器,可以使用@Data ->Lombok
     * @param nextProcessor
     */
    public PrintProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }


    @Override
    public void processorRequest(Request request) {

    }
}
