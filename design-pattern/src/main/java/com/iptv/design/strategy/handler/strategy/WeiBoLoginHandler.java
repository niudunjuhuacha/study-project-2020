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
 * @create 2021-05-31 17:15
 */
@Slf4j
@Component
public class WeiBoLoginHandler implements LoginHandler<Serializable> {
    @Override
    public LoginType getType() {
        return LoginType.WEIBO;
    }

    @Override
    public LoginResponse handleLogin(LoginRequest request) {
        log.info("微博登录：userId：{}", request.getUserId());
        return LoginResponse.success("微博登录成功", null);
    }
}
