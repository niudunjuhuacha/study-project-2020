package com.iptv.season3.pa.m3u8;

/**
 * @author: liuqi
 * @date: 2022/9/19 16:15
 * @description:
 */
public class M3u8Download {

    public static void MergeM3u8ToMp4(String M3U8URL,String dir,String fileName) {

        M3u8DownloadFactory.M3u8Download m3u8Download = M3u8DownloadFactory.getInstance(M3U8URL);
        //设置生成目录
        m3u8Download.setDir(dir);
        //设置视频名称
        m3u8Download.setFileName(fileName);
        //设置线程数
        m3u8Download.setThreadCount(100);
        //设置重试次数
        m3u8Download.setRetryCount(100);
        //设置连接超时时间（单位：毫秒）
        m3u8Download.setTimeoutMillisecond(10000L);
        /*
        设置日志级别
        可选值：NONE INFO DEBUG ERROR
        */
        m3u8Download.setLogLevel(Constant.INFO);
        //设置监听器间隔（单位：毫秒）
        m3u8Download.setInterval(500L);
        //添加额外请求头
      /*  Map<String, Object> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "text/html;charset=utf-8");
        m3u8Download.addRequestHeaderMap(headersMap);*/
        //添加监听器
        m3u8Download.addListener(new DownloadListener() {
            @Override
            public void start() {
                System.out.println("开始下载！");
            }

            @Override
            public void process(String downloadUrl, int finished, int sum, float percent) {
                System.out.println("下载网址：" + downloadUrl + "\t已下载" + finished + "个\t一共" + sum + "个\t已完成" + percent + "%");
            }

            @Override
            public void speed(String speedPerSecond) {
                System.out.println("下载速度：" + speedPerSecond);
            }

            @Override
            public void end() {
                System.out.println("下载完毕");
            }
        });
        //开始下载
        m3u8Download.start();
    }

    public static void main(String[] args) {
        MergeM3u8ToMp4("https://v3-ot01.kwaicdn.com/bs3/video-hls/5214042521844701156_hlsb.m3u8?ftt=U&pkey=AAXTwkbsNr1ITfctcJ_f0fg1N7sdOf9lFah4nMwYblQnDgdK3juj6R6uiWSLdLKrs1Ovfv2vFWQqPaWuCVSiBLIwPFGvXBXmPpLX3iOPrV5b-iwARFpkxaP3qQmJjCSuWmU"
                ,"/Users/liuqi/Documents/D盘文件/data","liuqitestmp4");
    }
}
