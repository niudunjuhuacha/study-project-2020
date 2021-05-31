package com.iptv.design.strategy.handler.strategy;

import com.iptv.design.strategy.enums.LoginType;
import com.iptv.design.strategy.handler.LoginHandler;
import com.iptv.design.strategy.util.LoginRequest;
import com.iptv.design.strategy.util.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author liuqi
 * @description:
 * @create 2021-05-31 17:13
 */
@Slf4j
@Component
public class QQLoginHandler implements LoginHandler<Serializable> {

    /**
     * 获取登录类型
     * @return
     */
    @Override
    public LoginType getType() {
        return LoginType.QQ;
    }

    @Override
    public LoginResponse handleLogin(LoginRequest request) {
        log.info("QQ登录：userId：{}", request.getUserId());
        return LoginResponse.success("QQ登录成功", null);
    }
}
