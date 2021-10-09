package com.iptv.season3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author liuqi
 * @description:
 * @create 2020-10-12 10:21
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SeasonApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SeasonApplication.class, args);
        // 将Context设置到SpringUtil中
//        SpringUtil.setApplicationContext(context);
    }
}
