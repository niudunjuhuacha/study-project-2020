package com.iptv.season3.pa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author liuqi
 * @description:
 * @create 2021-10-08 13:19
 */
public class PaPicTest {

    public static void main(String[] args) throws IOException {
        String url = "https://mp.weixin.qq.com/s/GIQoMCbZ_C48ta733UowVQ";
        Document document = Jsoup.parse(new URL(url), 100000);
        Element element = document.getElementById("js_content");
        Elements imgs = element.getElementsByTag("img");
        int id = 0;
        for (Element img : imgs) {
            String src = img.attr("data-src");

            URL target = new URL(src);
            URLConnection urlConnection = target.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            id++;
            OutputStream outputStream = new FileOutputStream("D:\\data\\weixinPic\\5\\" + id + ".jpg");
            int temp =0;
            while ((temp=inputStream.read())!=-1){
                outputStream.write(temp);
            }
            System.out.println(id + ".jpg下载完毕");
            outputStream.close();
            inputStream.close();
        }

    }

}
