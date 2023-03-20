package com.iptv.season3.pa.m3u8;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: liuqi
 * @date: 2022/9/19 16:23
 * @description:
 */
public class MediaFormat {
    private static Set<String> set = new HashSet<>();

    static {
        set.add("mp4");
        set.add("mkv");
        set.add("webm");
        set.add("gif");
        set.add("mov");
        set.add("ogg");
        set.add("flv");
        set.add("avi");
        set.add("3gp");
        set.add("wmv");
        set.add("mpg");
        set.add("vob");
        set.add("swf");
        set.add("m3u8");
    }

    private MediaFormat() {

    }

    public static String getMediaFormat(String url) {
        if (!StringUtils.isUrl(url))
            throw new M3u8Exception(url + "不是一个完整URL链接！");
        url = url.substring(url.lastIndexOf("/") - 1);
        for (String s : set) {
            if (url.contains(s))
                return s;
        }
        throw new M3u8Exception("非视频链接！");
    }

}