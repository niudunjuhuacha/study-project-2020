package com.iptv.design.strategy.factory;

import com.iptv.design.strategy.enums.LoginType;
import com.iptv.design.strategy.handler.LoginHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author liuqi
 * @description:
 * @create 2021-05-31 17:19
 */
@Component
public class LoginHandlerFactory implements InitializingBean, ApplicationContextAware {

    // 创建一个map,k存放登录类型,v存放对应的handler
    private static final Map<LoginType, LoginHandler<Serializable>> LOGIN_HANDLER_MAP = new EnumMap<>(LoginType.class);
    // spring上下文
    private ApplicationContext appContext;



    public LoginHandler<Serializable> getHandler(LoginType loginType){
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 将 Spring 容器中所有的 LoginHandler 注册到 LOGIN_HANDLER_MAP
        appContext.getBeansOfType(LoginHandler.class)
                .values()
                .forEach(loginHandler -> LOGIN_HANDLER_MAP.put(loginHandler.getType(), loginHandler));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }
}
