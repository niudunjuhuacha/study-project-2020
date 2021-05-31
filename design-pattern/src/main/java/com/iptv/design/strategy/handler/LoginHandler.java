package com.iptv.design.strategy.handler;

import com.iptv.design.strategy.enums.LoginType;
import com.iptv.design.strategy.util.LoginRequest;
import com.iptv.design.strategy.util.LoginResponse;

import java.io.Serializable;

/**
 * @author liuqi
 * @description: 登录策略接口
 *              1.获取策略类型的方法
 *              2.处理策略逻辑的方法
 * @create 2021-05-31 16:20
 */
public interface LoginHandler<T extends Serializable> {


    /**
     * 1.获取策略类型的方法
     * @return
     */
    LoginType getType();


    /**
     * 2.处理策略逻辑的方法
     * @param request
     * @return
     */
    LoginResponse handleLogin(LoginRequest request);
}
