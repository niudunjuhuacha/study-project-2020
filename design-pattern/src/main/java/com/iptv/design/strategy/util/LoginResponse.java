package com.iptv.design.strategy.util;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuqi
 * @description:
 * @create 2021-05-31 16:27
 */
@Data
@NoArgsConstructor
public class LoginResponse {

    private String msg;
    private Object data;

    public LoginResponse(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    public static LoginResponse success(String msg, Object data){
        return new LoginResponse(msg,data);
    }

}
