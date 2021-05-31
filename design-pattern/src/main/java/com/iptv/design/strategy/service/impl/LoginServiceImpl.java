package com.iptv.design.strategy.service.impl;

import com.iptv.design.strategy.enums.LoginType;
import com.iptv.design.strategy.factory.LoginHandlerFactory;
import com.iptv.design.strategy.handler.LoginHandler;
import com.iptv.design.strategy.service.LoginService;
import com.iptv.design.strategy.util.LoginRequest;
import com.iptv.design.strategy.util.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author liuqi
 * @description: 登录接口实现类
 *              传入对应的request，实现不同策略处理器处理事件
 *
 * ***********Factory 只负责获取 Handler***********
 * ***********Handler 只负责处理具体的登录***********
 * ***********Service 只负责逻辑编排，从而达到功能上的低耦合高内聚。***********
 *
 * @create 2021-05-31 17:32
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginHandlerFactory loginHandlerFactory;

    @Override
    public LoginResponse login(LoginRequest request) {
        // 拿到前端传来的登录类型
        LoginType loginType = request.getLoginType();

        // 从容器中使用工厂模式拿到对应的bean
        LoginHandler<Serializable> handler = loginHandlerFactory.getHandler(loginType);

        return handler.handleLogin(request);
    }


}
