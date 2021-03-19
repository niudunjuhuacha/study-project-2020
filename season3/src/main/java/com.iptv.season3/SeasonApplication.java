package com.iptv.season3;

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
public class SeasonApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SeasonApplication.class, args);
        // 将Context设置到SpringUtil中
//        SpringUtil.setApplicationContext(context);
    }
}
