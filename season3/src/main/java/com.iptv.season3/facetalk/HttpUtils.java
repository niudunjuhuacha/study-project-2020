/**
 *
 */
package com.iptv.season3.facetalk;

import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.*;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.*;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.HttpMessageParserFactory;
import org.apache.http.io.HttpMessageWriterFactory;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.LineParser;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author xzc
 */
public class HttpUtils {

    private static Logger log = LoggerFactory.getLogger(HttpUtils.class);

    public final static int DEFAULT_SOCKET_TIMEOUT = 40000; // socket连接时间最长为40s，防止web
    // nginx超时
    public final static int DEFAULT_CONNECT_TIMEOUT = 10000; // 建立请求最长时间
    public final static int DEFAULT_TIMEOUT = 40000;//
    public final static String DEFAULT_CHARSET = "UTF-8";

    /**
     * get请求
     *
     * @param url
     * @param params
     * @param headers
     * @param charset 为空时默认指定为utf-8
     * @return
     */
    public static String doGet(String url, Map<String, String> params, Map<String, String> headers, String charset) {
        CloseableHttpClient hc = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        try {
            String paramUrl = assembleUrl(url, params, charset);
            HttpGet hg = new HttpGet(paramUrl);
            hg.setConfig(getDefaultRequestConfig());

            // 请求中写入头
            if (null != headers && headers.size() > 0) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    hg.setHeader(entry.getKey(), entry.getValue());
                }
            }

            response = hc.execute(hg);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);

        } catch (Exception e) {

            log.error("{}", e);

        } finally {

            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != hc) {
                try {
                    hc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return StringUtils.EMPTY;
    }

    /**
     * 指定url，请求参数，请求头、参数编码进行post请求
     *
     * @param url
     * @param params
     * @param headers
     * @param paramCharset 未指定时，默认为utf-8
     * @return
     */
    public static String doPost(String url, Map<String, String> params, Map<String, String> headers,
                                String paramCharset) {
        CloseableHttpClient hc = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        try {
            HttpPost hp = new HttpPost(url);
            hp.setConfig(getDefaultRequestConfig());

            // 请求中写入头
            if (null != headers && headers.size() > 0) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    hp.setHeader(entry.getKey(), entry.getValue());
                }
            }

            // 转换参数
            List<NameValuePair> nvPairs = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nvPairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            paramCharset = StringUtils.isNotBlank(paramCharset) ? paramCharset : StandardCharsets.UTF_8.name();
            hp.setEntity(new UrlEncodedFormEntity(nvPairs, paramCharset));

            response = hc.execute(hp);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);

        } catch (Exception e) {

            log.error("{}", e);

        } finally {

            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != hc) {
                try {
                    hc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return StringUtils.EMPTY;
    }

    /**
     * 对于无法用map传入的post参数列表，直接封装name-value映射传入
     *
     * @param url
     * @param nvPairs
     * @param headers
     * @param charset
     * @return
     */
    public static String doPost(String url, List<NameValuePair> nvPairs, Map<String, String> headers, String charset) {
        CloseableHttpClient hc = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        try {
            HttpPost hp = new HttpPost(url);
            hp.setConfig(getDefaultRequestConfig());

            // 请求中写入头
            if (null != headers && headers.size() > 0) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    hp.setHeader(entry.getKey(), entry.getValue());
                }
            }

            // 转换参数
            // List<NameValuePair> nvPairs = new ArrayList<NameValuePair>();
            // for( Map.Entry<String, String> entry:params.entrySet() ) {
            // nvPairs.add(new
            // BasicNameValuePair(entry.getKey(),entry.getValue()));
            // }

            charset = StringUtils.isNotBlank(charset) ? charset : StandardCharsets.UTF_8.name();
            hp.setEntity(new UrlEncodedFormEntity(nvPairs, charset));

            response = hc.execute(hp);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);

        } catch (Exception e) {

            log.error("{}", e);

        } finally {

            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != hc) {
                try {
                    hc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return StringUtils.EMPTY;
    }

    public static String doPost(String url, Map<String, String> params) {
        CloseableHttpClient hc = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        try {
            HttpPost hp = new HttpPost(url);
            hp.setConfig(getDefaultRequestConfig());

            // 转换参数
            List<NameValuePair> nvPairs = new ArrayList<NameValuePair>();
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    nvPairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            hp.setEntity(new UrlEncodedFormEntity(nvPairs));// 以表单形式加入参数

            response = hc.execute(hp);
            HttpEntity entity = response.getEntity();
            // EntityUtils.consume(entity);
            return EntityUtils.toString(entity);

        } catch (Exception e) {

            log.error("{}", e);

        } finally {

            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != hc) {
                try {
                    hc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return StringUtils.EMPTY;
    }

    public static String doGet(String url) throws IOException {
        // Use custom message parser / writer to customize the way HTTP
        // messages are parsed from and written out to the data stream.
        HttpMessageParserFactory<HttpResponse> responseParserFactory = new DefaultHttpResponseParserFactory() {

            @Override
            public HttpMessageParser<HttpResponse> create(SessionInputBuffer buffer, MessageConstraints constraints) {
                LineParser lineParser = new BasicLineParser() {

                    @Override
                    public Header parseHeader(final CharArrayBuffer buffer) {
                        try {
                            return super.parseHeader(buffer);
                        } catch (ParseException ex) {
                            return new BasicHeader(buffer.toString(), null);
                        }
                    }

                };
                return new DefaultHttpResponseParser(buffer, lineParser, DefaultHttpResponseFactory.INSTANCE,
                        constraints) {

                    @Override
                    protected boolean reject(final CharArrayBuffer line, int count) {
                        // try to ignore all garbage preceding a status line
                        // infinitely
                        return false;
                    }

                };
            }

        };
        HttpMessageWriterFactory<HttpRequest> requestWriterFactory = new DefaultHttpRequestWriterFactory();

        // Use a custom connection factory to customize the process of
        // initialization of outgoing HTTP connections. Beside standard
        // connection
        // configuration parameters HTTP connection factory can define message
        // parser / writer routines to be employed by individual connections.
        HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory = new ManagedHttpClientConnectionFactory(
                requestWriterFactory, responseParserFactory);

        // Client HTTP connection objects when fully initialized can be bound to
        // an arbitrary network socket. The process of network socket
        // initialization,
        // its connection to a remote address and binding to a local one is
        // controlled
        // by a connection socket factory.

        // SSL context for secure connections can be created either based on
        // system or application specific properties.
        SSLContext sslcontext = SSLContexts.createSystemDefault();
        // Use custom hostname verifier to customize SSL hostname verification.
        // X509HostnameVerifier hostnameVerifier = new
        // BrowserCompatHostnameVerifier();

        // Create a registry of custom connection socket factories for supported
        // protocol schemes.
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                // .register("https", new SSLConnectionSocketFactory(sslcontext,
                // hostnameVerifier))
                .build();

        // Use custom DNS resolver to override the system DNS resolution.
        DnsResolver dnsResolver = new SystemDefaultDnsResolver() {

            @Override
            public InetAddress[] resolve(final String host) throws UnknownHostException {
                if (host.equalsIgnoreCase("myhost")) {
                    return new InetAddress[]{InetAddress.getByAddress(new byte[]{127, 0, 0, 1})};
                } else {
                    return super.resolve(host);
                }
            }

        };

        // Create a connection manager with custom configuration.
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry,
                connFactory, dnsResolver);

        // Create socket configuration
        SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
        // Configure the connection manager to use socket configuration either
        // by default or for a specific host.
        connManager.setDefaultSocketConfig(socketConfig);
        connManager.setSocketConfig(new HttpHost("somehost", 80), socketConfig);

        // Create message constraints
        MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount(200)
                .setMaxLineLength(2000).build();
        // Create connection configuration
        ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8)
                .setMessageConstraints(messageConstraints).build();
        // Configure the connection manager to use connection configuration
        // either
        // by default or for a specific host.
        connManager.setDefaultConnectionConfig(connectionConfig);
        connManager.setConnectionConfig(new HttpHost("somehost", 80), ConnectionConfig.DEFAULT);

        // Configure total max or per route limits for persistent connections
        // that can be kept in the pool or leased by the connection manager.
        connManager.setMaxTotal(100);
        connManager.setDefaultMaxPerRoute(10);
        connManager.setMaxPerRoute(new HttpRoute(new HttpHost("somehost", 80)), 20);

        // Use custom cookie store if necessary.
        CookieStore cookieStore = new BasicCookieStore();
        // Use custom credentials provider if necessary.
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        // Create global request configuration
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                // .setCookieSpec(CookieSpecs.BEST_MATCH)
                .setExpectContinueEnabled(true)
                // .setStaleConnectionCheckEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();

        // Create an HttpClient with the given custom dependencies and
        // configuration.
        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(connManager)
                .setDefaultCookieStore(cookieStore).setDefaultCredentialsProvider(credentialsProvider)
                .setDefaultRequestConfig(defaultRequestConfig).build();

        try {
            HttpGet httpget = new HttpGet(url);
            // Request configuration can be overridden at the request level.
            // They will take precedence over the one set at the client level.
            RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).setSocketTimeout(5000)
                    .setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();
            httpget.setConfig(requestConfig);

            // Execution context can be customized locally.
            HttpClientContext context = HttpClientContext.create();
            // Contextual attributes set the local context level will take
            // precedence over those set at the client level.
            context.setCookieStore(cookieStore);
            context.setCredentialsProvider(credentialsProvider);

            System.out.println("executing request " + httpget.getURI());
            CloseableHttpResponse response = httpclient.execute(httpget, context);
            try {
                HttpEntity entity = response.getEntity();

                // Once the request has been executed the local context can
                // be used to examine updated state and various objects affected
                // by the request execution.

                // Last executed request
                context.getRequest();
                // Execution route
                context.getHttpRoute();
                // Target auth state
                context.getTargetAuthState();
                // Proxy auth state
                context.getTargetAuthState();
                // Cookie origin
                context.getCookieOrigin();
                // Cookie spec used
                context.getCookieSpec();
                // User security token
                context.getUserToken();

                return EntityUtils.toString(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    /**
     * 根据url接口和参数列表封装get url
     *
     * @param url
     * @param params
     * @return
     */
    public static String assembleUrl(String url, Map<String, String> params, String charset) {
        if (!url.endsWith("?")) {
            url += "?";
        }
        charset = StringUtils.isNotBlank(charset) ? charset : StandardCharsets.UTF_8.name();
        if (params != null) {
            for (String key : params.keySet()) {
                try {
                    if (params.get(key) == null || params.get(key).length() == 0) {
                        url += key + "=" + params.get(key) + "&";
                    } else {
                        url += key + "=" + URLEncoder.encode(params.get(key), charset) + "&";
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return url;
    }

    /**
     * 请求默认时间限制配置
     *
     * @return
     */
    private static RequestConfig getDefaultRequestConfig() {
        return RequestConfig.custom().setSocketTimeout(DEFAULT_SOCKET_TIMEOUT)
                .setConnectTimeout(DEFAULT_CONNECT_TIMEOUT).setConnectionRequestTimeout(DEFAULT_TIMEOUT)
                // .setProxy(new HttpHost("10.7.8.63",8888,"http"))
                .build();
    }

    /**
     * @param @param  url
     * @param @param  headers
     * @param @param  body
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: doJsonPost
     * @author liuheming
     * @date 2018年4月24日 下午4:32:27
     * @Description: 以json发送请求
     */
    public static String doJsonPost(String url, Map<String, String> headers, String body) {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        String result = "";
        try {
            // 请求中写入头
            if (null != headers && headers.size() > 0) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    post.setHeader(entry.getKey(), entry.getValue());
                }
            }
            StringEntity s = new StringEntity(body.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");// 发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            // if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(res.getEntity());// 返回json格式：
            // }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static InputStream getHttpFile(String url) throws Exception {
        InputStream is = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            is = httpEntity.getContent();
        } catch (Exception e) {
            throw e;
        } finally {
            return is;
        }
    }
}
