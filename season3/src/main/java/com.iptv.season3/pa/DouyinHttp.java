package com.iptv.season3.pa;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author liuqi
 * @description:
 * @create 2021-10-08 16:23
 */
public class DouyinHttp {

    public static void main(String[] args) {
        String s = "https://www.douyin.com/aweme/v1/play/?video_id=v0200fg10000c3ko7lrc77u2a2vn82ig&line=0&file_id=5f0f35533d73450bac60fbe031084e52&sign=865a05330df87490ec08888beb99d42f&is_play_url=1&source=PackSourceEnum_SEARCH";
        downloadVideo(s);
    }

    private static void downloadVideo(String videoAddress) {
        int byteRead;
        try {
            URL url = new URL(videoAddress);
            //获取链接
            URLConnection conn = url.openConnection();
            //输入流
            InputStream inStream = conn.getInputStream();
            //封装一个保存文件的路径对象
            File fileSavePath = new File("d:/data/douyin/test001/1.mp4");
            //注:如果保存文件夹不存在,那么则创建该文件夹
            File fileParent = fileSavePath.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            //写入文件
            FileOutputStream fs = new FileOutputStream(fileSavePath);
            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            fs.close();
            System.out.println("\n-----视频保存路径-----\n"+fileSavePath.getAbsolutePath());
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}
