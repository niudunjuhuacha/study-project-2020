package com.iptv.design.strategy.service;

import com.iptv.design.strategy.enums.LoginType;
import com.iptv.design.strategy.util.LoginRequest;
import com.iptv.design.strategy.util.LoginResponse;
import org.springframework.stereotype.Service;

/**
 * @author liuqi
 * @description: 登录接口
 * @create 2021-05-31 17:31
 */
public interface LoginService {

    LoginResponse login(LoginRequest request);

}
