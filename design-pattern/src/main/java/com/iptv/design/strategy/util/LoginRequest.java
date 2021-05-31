package com.iptv.design.strategy.util;

import com.iptv.design.strategy.enums.LoginType;
import lombok.Data;

/**
 * @author liuqi
 * @description:
 * @create 2021-05-31 16:26
 */
@Data
public class LoginRequest {

    private LoginType loginType;

    private Long userId;

}
