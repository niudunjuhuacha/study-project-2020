package com.iptv.season3.pa;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author liuqi
 * @description:
 * @create 2021-10-09 11:54
 */
public class HttpDownload {

    public static boolean httpDownload(String httpUrl, String saveFile) {
        // 1.下载网络文件
        int byteRead;
        URL url;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return false;
        }

        try {
            //2.获取链接
            URLConnection conn = url.openConnection();
            //3.输入流
            InputStream inStream = conn.getInputStream();
            //3.写入文件
            FileOutputStream fs = new FileOutputStream(saveFile);

            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            fs.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Test
    public void httpDownload() {
        httpDownload("http://v3-z.douyinvod.com/3bfa066ce4ee6ff265cf41e8578d20ac/61611fec/video/tos/cn/tos-cn-ve-15/f8618738867048888d435362425dac05/?a=1128&br=1126&bt=1126&btag=3&cd=0%7C0%7C0&ch=0&cr=0&cs=0&cv=1&dr=0&ds=3&er=&ft=OR_LrKZZI0rE1lhzWTh94ictRsWd.oGfc68&l=&lr=&mime_type=video_mp4&net=0&pl=0&qs=0&rc=ajVxNTU6Zm9yNjMzNGkzM0ApODczNmhpOWQzNzo2ODYzOGcpZmY1c15yNG80aGAtLWQtL3NzL2I1Li1fLWBfMjBhMDJjYzpjOg%3D%3D&vl=&vr=","d:/data/douyinData/test001/2.mp4");
    }

}
