package com.iptv.season3.pa.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.iptv.season3.facetalk.HttpUtils;
import com.iptv.season3.utils.MyHttpsClient;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuqi
 * @description:
 * @create 2021-10-09 8:51
 */
@RestController
@RequestMapping("/douyin/video")
public class DouyinController {

    @GetMapping("/get")
    @CrossOrigin
    public String getDouyinVideos() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, URISyntaxException, IOException {
        String url = "https://www.douyin.com/aweme/v1/web/search/item/?device_platform=webapp&aid=6383&channel=channel_pc_web&search_channel=aweme_video_web&search_source=pc_detail_load_more&sort_type=0&publish_time=0&is_filter_search=0&query_correct_type=1&keyword=%E7%BE%8E%E5%A5%B3%E8%A7%86%E9%A2%91%E8%B7%B3%E8%88%9E%E5%90%88%E9%9B%86&search_id=202110081657080102120570761D0C3D6E&offset=33&count=30&version_code=170400&version_name=17.4.0&cookie_enabled=true&screen_width=1920&screen_height=1080&browser_language=zh-CN&browser_platform=Win32&browser_name=Mozilla&browser_version=5.0+(Windows+NT+10.0%3B+Win64%3B+x64)+AppleWebKit%2F537.36+(KHTML,+like+Gecko)+Chrome%2F94.0.4606.61+Safari%2F537.36&browser_online=true&msToken=MnlpHoVy2gqUGdMi8Jc60x1nTllZLKAdqD3-9Hnn8ctVusMbgPN0oZzIZfskkksKDSktrpck_jZSkSN9QIZ1TLWtHb5vl37LSdci7u_Dgskm0xBQvmQdaXqd5Q==&X-Bogus=DFSzsdVODrvANcK8Sz3pqF9WX7rx&_signature=_02B4Z6wo00001AvMt-AAAIDAi85NoY0zJEgLyLNAAGOZZ6zucyXBDeiY5xKJrS9BhDalkeqM-w446bK2fFMz5toCcvfDtPPJpCiLUVbnx.Cq6OMf6X7rH0XaM6BdY2.-oHMD.9VxywX0slni85";
        Map<String, String> headerMap = new HashMap();
        headerMap.put("Host", "<calculated when request is sent>");
        headerMap.put("Accept", "*/*");
        headerMap.put("Accept-Encoding", "gzip, deflate, br");
        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.61 Safari/537.36");
        headerMap.put("Connection", "keep-alive");
        headerMap.put("content-type", "application/json");
        headerMap.put("accept-language", "zh-CN,zh;q=0.9");
        headerMap.put("cookie", "s_v_web_id=verify_30a547f7fe5519d712522fd119f4f4a4; _tea_utm_cache_2018=undefined; ttwid=1%7CdLlQgCEL2nBMVa5cqadbcB5fd7Wvvly5Z572mdOEC1w%7C1633679856%7C08c4472d53b2d575ea2d11fc1cff8d006b82c605c8146333d419d893426f3eef; _tea_utm_cache_6383=undefined; MONITOR_WEB_ID=2f3db634-f96e-4598-bf19-71b3cf77f30e; passport_csrf_token_default=eb868f763c7b39a2e1f078bc8e3920be; passport_csrf_token=eb868f763c7b39a2e1f078bc8e3920be; ttcid=485d58e22caf4b90969d22d0d1f0f13929; odin_tt=4a45f263fc7b9ce36f8156d306f82b5eccc6ab9b9cb14ee7a18792a0ccb18eae50730ba1d06d9f3d1be1d3bf06d78d0c60cc9f3809b3b0453a2c0abd5ceb5a58; douyin.com; FOLLOW_YELLOW_POINT_USER=; FOLLOW_YELLOW_POINT_STATUE_INFO=0%2F1633683475323; __ac_nonce=06160037b00946de65571; __ac_signature=_02B4Z6wo00f01lAyesAAAIDC0DCAg.rvtoJQFn5AAPV7sIzgbJdkfvEOHSf4M4CvQC3uxwPtd5TpfCZ9wkm3XWKRS7aVX6UjtoFc3od1Nn-XeqDJPl3XF5Z-ZbfTpvsS7R-jT158qwNKlysede; tt_scid=2TpWBWHKjm9EmhxtlGwVAIGlCEa4wDuJOjCWPn6b3PzEuojKKsyaRMs35Es1GJNsa3a7; msToken=MnlpHoVy2gqUGdMi8Jc60x1nTllZLKAdqD3-9Hnn8ctVusMbgPN0oZzIZfskkksKDSktrpck_jZSkSN9QIZ1TLWtHb5vl37LSdci7u_Dgskm0xBQvmQdaXqd5Q==; msToken=u3WGLKF67M5c1JHYeDw5_5bLp7ZJ7OQgeZTpaXs8HnUjBQYsxkBST8v-GQiek4f-MYnCYLkYsvEN4Tvi1xSesAhpPcuzninVQSZP2lehCHbGm6DW-Pz3vPjacaw=");
        headerMap.put("referer", "https://www.douyin.com/video/7002808289218006310?extra_params=%7B%22search_id%22%3A%22202110081657080102120570761D0C3D6E%22%2C%22search_result_id%22%3A%227002808289218006310%22%2C%22search_type%22%3A%22video%22%2C%22search_keyword%22%3A%22%E7%BE%8E%E5%A5%B3%E8%A7%86%E9%A2%91%E8%B7%B3%E8%88%9E%E5%90%88%E9%9B%86%22%7D&modeFrom=searchResult&previous_page=search_result&search_cursor=22&search_keyword=%E7%BE%8E%E5%A5%B3%E8%A7%86%E9%A2%91%E8%B7%B3%E8%88%9E%E5%90%88%E9%9B%86");

        /*headerMap.put("sec-ch-ua", "\"Chromium\";v=\"94\", \"Google Chrome\";v=\"94\", \";Not A Brand\";v=\"99\"");
        headerMap.put("sec-ch-ua-mobile", "?0");
        headerMap.put("sec-ch-ua-platform", "\"Windows\"");
        headerMap.put("sec-fetch-dest", "empty");
        headerMap.put("sec-fetch-mode", "cors");
        headerMap.put("sec-fetch-site", "same-origin");*/
//        headerMap.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.61 Safari/537.36");
//        HttpResponse execute = HttpRequest.get(url).addHeaders(header).execute();

//        String s = HttpUtils.doGet(url, null, headerMap, null);

        // 定义请求的参数
        URI uri = new URIBuilder(url).build();

        // 创建http GET请求
        HttpGet httpGet = new HttpGet(uri);

        // 创建header
        headerMap.forEach((k, v) -> httpGet.addHeader(k,v));

        CloseableHttpClient httpclient = MyHttpsClient.getInstance().createHttpClient();

        CloseableHttpResponse response = null;
        try {
            // 执行http get请求
            response = httpclient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 403) {
                return "403";
            }
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("内容长度："+content.length());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
            httpclient.close();
        }


        return "";
    }

}
