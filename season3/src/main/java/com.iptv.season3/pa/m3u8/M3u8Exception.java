package com.iptv.season3.pa.m3u8;

/**
 * @author: liuqi
 * @date: 2022/9/19 16:18
 * @description:
 */
public class M3u8Exception extends RuntimeException {
    public M3u8Exception() {
        super();
    }

    public M3u8Exception(String message) {
        super(message);
    }

    public M3u8Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
