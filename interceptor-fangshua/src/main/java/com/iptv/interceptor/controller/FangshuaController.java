package com.iptv.interceptor.controller;

import com.iptv.interceptor.intercept.RequestLimit;
import com.iptv.interceptor.utils.ResUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuqi
 * @description:
 * @create 2020-10-12 10:18
 */
@RestController
@RequestMapping("/fangshua")
public class FangshuaController {

    @RequestLimit(second = 10,maxCount = 2)
    @GetMapping("/dododo")
    public String dododo(){

        return ResUtil.getSucDes("666");
    }

}
