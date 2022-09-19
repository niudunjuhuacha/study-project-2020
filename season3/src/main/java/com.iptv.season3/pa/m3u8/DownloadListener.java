package com.iptv.season3.pa.m3u8;

/**
 * @author: liuqi
 * @date: 2022/9/19 16:16
 * @description:
 */
public interface DownloadListener {

    void start();

    void process(String downloadUrl, int finished, int sum, float percent);

    void speed(String speedPerSecond);

    void end();
}
