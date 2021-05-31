package com.iptv.design.strategy.controller;

import com.iptv.design.strategy.enums.LoginType;
import com.iptv.design.strategy.service.LoginService;
import com.iptv.design.strategy.util.LoginRequest;
import com.iptv.design.strategy.util.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuqi
 * @description:
 * @create 2021-05-31 17:37
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public LoginResponse login(@RequestParam LoginType loginType, @RequestParam Long userId){
        LoginRequest request = new LoginRequest();
        request.setLoginType(loginType);
        request.setUserId(userId);
        return loginService.login(request);
    }

}
