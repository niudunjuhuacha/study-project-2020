package com.iptv.interceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author liuqi
 * @description:
 * @create 2020-10-12 10:21
 */
@SpringBootApplication
public class InterceptorApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(InterceptorApplication.class, args);
        // 将Context设置到SpringUtil中
//        SpringUtil.setApplicationContext(context);
    }
}
