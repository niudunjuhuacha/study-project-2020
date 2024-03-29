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
import java.util.TreeMap;

/**
 * @author liuqi
 * @description:
 * @create 2021-10-08 16:23
 */
public class DouyinHttp {

    final static String FILE_NAME = "/Users/liuqi/Documents/D盘文件/data/douyindata/douyinJson.txt";
    final static String FIX = "/Users/liuqi/Documents/D盘文件/data/douyindata/";
    final static String SPLIT = "ZAQ!@WSX";

    // todo 名字序号
    final static Integer FILE_NAME_INDEX = 0;

    /**
     * 他的喜欢
     */
    @Test
    public void favoriteHttp() {
        Map<Integer, String> videoMap = getFavoriteVideoMap();
        videoMap.forEach((id, url) -> {
            String filePath = FIX + DateUtil.today() + "/" + id + ".mp4";
            httpDownload(url, filePath);
//            System.out.println(id + "==" + url);
        });
    }

    /**
     * Ta的作品
     */
    @Test
    // 读取文件的json，然后解析到 其中的视频地址，直接下载
    public void tadezuopinHttp() {
        // 读取本地文件夹下的json文件，返回视频地址List<String>
        Map<String, String> videoMap = getTadeVideoMap();
        videoMap.forEach((id, url) -> {
            String filePath = FIX + DateUtil.today() + "/" + id + ".mp4";
            httpDownload(url, filePath);
        });
    }
    /**
     * 推荐的作品
     */
    @Test
    // 读取文件的json，然后解析到 其中的视频地址，直接下载
    public void douyinHttp() {
        // 读取本地文件夹下的json文件，返回视频地址List<String>
        Map<String, String> videoMap = getVideoMap();
        videoMap.forEach((id, url) -> {
            String filePath = FIX + DateUtil.today() + "/" + id + ".mp4";
            httpDownload(url, filePath);
        });
    }


    private Map<Integer, String> getFavoriteVideoMap() {
        Map<Integer, String> map = new TreeMap<>();

        File file = new File(FILE_NAME);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
                sbf.append(SPLIT);
            }
            reader.close();
            // 获取到文本
            String s = sbf.toString();
            jsonLinesToMap(map, s);

            return map;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;

    }

    /**
     * 多行json全部放到一个map中
     *
     * @param map
     * @param s
     */
    private void jsonLinesToMap(Map<Integer, String> map, String s) {

        // 解析s
        String[] splitJsonArr = s.split(SPLIT);
        int videoId = 0;
        int index = 0;
        for (String splitJson : splitJsonArr) {
            // 解析成json
            JSONObject jsonObject = JSONObject.parseObject(splitJson);
            JSONArray awemeList = jsonObject.getJSONArray("aweme_list");
            for (int i = 0; i < awemeList.size(); i++) {
                // 得到json中有效的videoUrl
                JSONObject awemeInfo = awemeList.getJSONObject(i);
                JSONObject video = awemeInfo.getJSONObject("video");
                JSONObject playAddrLowbr = video.getJSONObject("play_addr");
                JSONArray urlList = playAddrLowbr.getJSONArray("url_list");
                String videoUrl = (String) urlList.get(1);

                videoId = i + FILE_NAME_INDEX + index;
                // 组装list
                map.put(videoId, videoUrl);
                if (i == awemeList.size() - 1) {
                    index = videoId+1;
                }
            }
        }
    }


    private static Map<String, String> getTadeVideoMap() {
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
            JSONArray awemeList = jsonObject.getJSONArray("aweme_list");
            for (int i = 0; i < awemeList.size(); i++) {
                // 得到json中有效的videoUrl
                JSONObject awemeInfo = awemeList.getJSONObject(i);
                JSONObject video = awemeInfo.getJSONObject("video");
                JSONObject playAddrLowbr = video.getJSONObject("play_addr");
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
