package com.iptv.design.strategy.handler.strategy;

import com.iptv.design.strategy.enums.LoginType;
import com.iptv.design.strategy.handler.LoginHandler;
import com.iptv.design.strategy.util.LoginRequest;
import com.iptv.design.strategy.util.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author liuqi
 * @description:
 * @create 2021-05-31 16:49
 */
@Slf4j
@Component
public class WeChatLoginHandler implements LoginHandler<String> {

    /**
     * 获取登录类型
     *
     * @return
     */
    @Override
    public LoginType getType() {
        return LoginType.WE_CHAT;
    }

    @Override
    public LoginResponse handleLogin(LoginRequest request) {
        log.info("微信登录：userId：{}", request.getUserId());
        String weChatName = getWeChatName(request);
        return LoginResponse.success("微信登录成功", weChatName);
    }

    private String getWeChatName(LoginRequest request) {
        return "牛顿菊菊茶";
    }
}
