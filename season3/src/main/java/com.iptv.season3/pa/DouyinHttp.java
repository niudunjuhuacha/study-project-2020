package com.iptv.season3.pa;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuqi
 * @description:
 * @create 2021-10-08 16:23
 */
public class DouyinHttp {

    final static String FILE_NAME = "d:/data/douyinData/test001/douyinJson.txt";

    // todo 名字序号
    final static Integer FILE_NAME_INDEX = 0;


    @Test
    // 读取文件的json，然后解析到 其中的视频地址，直接下周
    public void douyinHttp() {
        // 读取本地文件夹下的json文件，返回视频地址List<String>
        Map<String, String> videoMap = getVideoMap();
        videoMap.forEach((id, url) -> {
            String filePath = "d:/data/douyinData/test001/" + DateUtil.today() + "/" + id + ".mp4";
            httpDownload(url, filePath);
        });
/*        String videoUrl = "http://v3-z.douyinvod.com/3bfa066ce4ee6ff265cf41e8578d20ac/61611fec/video/tos/cn/tos-cn-ve-15/f8618738867048888d435362425dac05/?a=1128&br=1126&bt=1126&btag=3&cd=0%7C0%7C0&ch=0&cr=0&cs=0&cv=1&dr=0&ds=3&er=&ft=OR_LrKZZI0rE1lhzWTh94ictRsWd.oGfc68&l=&lr=&mime_type=video_mp4&net=0&pl=0&qs=0&rc=ajVxNTU6Zm9yNjMzNGkzM0ApODczNmhpOWQzNzo2ODYzOGcpZmY1c15yNG80aGAtLWQtL3NzL2I1Li1fLWBfMjBhMDJjYzpjOg%3D%3D&vl=&vr=";
        String videoId = "111";
        String filePath = "d:/data/douyinData/test001/" + DateUtil.today() + "/" + videoId + ".mp4";

        httpDownload(videoUrl, filePath);*/
//        downloadVideo(videoUrl);

    }

    private static Map<String, String> getVideoMap() {
        Map<String, String> map = new HashMap<>();

        File file = new File(FILE_NAME);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            // 获取到文本
            String s = sbf.toString();

            // 解析成json
            JSONObject jsonObject = JSONObject.parseObject(s);
            JSONArray data = jsonObject.getJSONArray("data");
            for (int i = 0; i < data.size(); i++) {
                // 得到json中有效的videoUrl
                JSONObject dataJSONObject = data.getJSONObject(i);
                JSONObject awemeInfo = dataJSONObject.getJSONObject("aweme_info");
                JSONObject video = awemeInfo.getJSONObject("video");
                JSONObject playAddrLowbr = video.getJSONObject("play_addr_lowbr");
                JSONArray urlList = playAddrLowbr.getJSONArray("url_list");
                String videoUrl = (String) urlList.get(1);

                String videoId = i + FILE_NAME_INDEX + "";
                // 组装list
                map.put(videoId, videoUrl);
            }
            return map;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return map;
    }

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
            //注:如果保存文件夹不存在,那么则创建该文件夹
            File fileSavePath = new File(saveFile);
            File fileParent = fileSavePath.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            //3.写入文件
            FileOutputStream fs = new FileOutputStream(saveFile);

            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            fs.close();
            System.out.println("\n-----视频保存路径-----\n" + fileSavePath.getAbsolutePath());

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
