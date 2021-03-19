package com.iptv.interceptor.config;

import com.iptv.interceptor.intercept.RequestLimitIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author liuqi
 * @description:
 * @create 2020-10-12 11:56
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private RequestLimitIntercept interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
}
