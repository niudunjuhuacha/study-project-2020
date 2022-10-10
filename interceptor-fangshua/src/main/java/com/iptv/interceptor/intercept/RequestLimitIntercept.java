package com.iptv.interceptor.intercept;

import com.iptv.interceptor.utils.IpUtils;
import com.iptv.interceptor.utils.ResUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


/**
 * @author liuqi
 * @description:
 * @create 2020-10-09 16:31
 */

@Component
public class RequestLimitIntercept extends HandlerInterceptorAdapter {



    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /**
         * isAssignableFrom() 判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口
         * isAssignableFrom()方法是判断是否为某个类的父类
         * instanceof关键字是判断是否某个类的子类
         */
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            //HandlerMethod 封装方法定义相关的信息,如类,方法,参数等
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            // 获取方法中是否包含注解
            RequestLimit methodAnnotation = method.getAnnotation(RequestLimit.class);
            //获取 类中是否包含注解，也就是controller 是否有注解
            RequestLimit classAnnotation = method.getDeclaringClass().getAnnotation(RequestLimit.class);
            // 如果 方法上有注解就优先选择方法上的参数，否则类上的参数
            RequestLimit requestLimit = methodAnnotation != null ? methodAnnotation : classAnnotation;
            if (requestLimit != null) {
                if (isLimit(request, requestLimit)) {
                    resonseOut(response, ResUtil.get400ErrDes("休息一下~"));
                    return false;
                }
            }
        }
        return super.preHandle(request, response, handler);
    }

    //判断请求是否受限
    public boolean isLimit(HttpServletRequest request, RequestLimit requestLimit) {
        // 受限的redis 缓存key ,因为这里用浏览器做测试，我就用sessionid 来做唯一key,如果是app ,可以使用 用户ID 之类的唯一标识。
        //还可以使用真实的IP地址
        String limitKey = "limit:" + request.getServletPath() + IpUtils.getIpAddr(request);
        // 从缓存中获取，当前这个请求访问了几次
        String redisCount = redisTemplate.opsForValue().get(limitKey);
        Integer redisCountInt = null;
        if (StringUtils.isNotBlank(redisCount)) {
            redisCountInt = Integer.parseInt(redisCount);
        }
        if (redisCountInt == null) {
            //初始 次数
            redisTemplate.opsForValue().set(limitKey, String.valueOf(1), requestLimit.second(), TimeUnit.SECONDS);
        } else {
            if (redisCountInt.intValue() >= requestLimit.maxCount()) {
                return true;
            }
            // 次数自增
            redisTemplate.opsForValue().increment(limitKey);
        }
        return false;
    }

    /**
     * 回写给客户端
     *
     * @param response
     * @param result
     * @throws IOException
     */
    private void resonseOut(HttpServletResponse response, String result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.append(result);
    }

}
