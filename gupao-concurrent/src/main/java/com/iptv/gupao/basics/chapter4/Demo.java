package com.iptv.gupao.basics.chapter4;

/**
 * @author liuqi
 * @description: 责任链模式，zookeeper也使用过
 * @create 2021-04-01 18:37
 */
public class Demo {

    PrintProcessor printProcessor;

    /**
     * 构造类的时候进行处理器的初始化操作
     */
    public Demo() {
        // 创建save处理器实例，并启动
        SaveProcessor saveProcessor = new SaveProcessor();
        saveProcessor.start();

        // 创建print处理器实例，将save处理器放进构造方法中初始化，并启动
        printProcessor = new PrintProcessor(saveProcessor);
        printProcessor.start();
    }


    public void doTest(Request request){
        printProcessor.processorRequest(request);
    }


    public static void main(String[] args) {
        Request request = new Request();
        request.setName("liuqi");
        // 使用Demo中定义的责任链，去执行request
        new Demo().doTest(request);
    }

}
