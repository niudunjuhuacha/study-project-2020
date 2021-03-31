package com.iptv.gupao.chapter4;

import lombok.Data;

/**
 * @author liuqi
 * @description:
 * @create 2021-03-31 18:22
 */
@Data
public class Request {

    private String name;

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
