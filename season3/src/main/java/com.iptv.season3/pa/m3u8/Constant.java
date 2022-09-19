package com.iptv.season3.pa.m3u8;

/**
 * @author: liuqi
 * @date: 2022/9/19 16:16
 * @description:
 */
public class Constant {

    //文件分隔符，在window中为\\，在linux中为/
    public static final String FILESEPARATOR = System.getProperty("file.separator");

    //因子
    public static final float FACTOR = 1.15F;

    //默认文件每次读取字节数
    public static final int BYTE_COUNT = 40960;

    //日志级别 控制台不输出
    public static final int NONE = 0X453500;

    //日志级别 控制台输出所有信息
    public static final int INFO = 0X453501;

    //日志级别 控制台输出调试和错误信息
    public static final int DEBUG = 0X453502;

    //日志级别 控制台只输出错误信息
    public static final int ERROR = 0X453503;

}
