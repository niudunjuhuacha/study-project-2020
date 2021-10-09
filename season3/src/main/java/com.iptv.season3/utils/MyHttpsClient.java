package com.iptv.season3.utils;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class MyHttpsClient {

    public static MyHttpsClient getInstance() {

        return new MyHttpsClient();

    }

    public CloseableHttpClient createHttpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        SSLContextBuilder builder = SSLContexts.custom();

        builder.loadTrustMaterial(null, new TrustStrategy() {

            @Override

            public boolean isTrusted(X509Certificate[] arg0, String arg1)

                    throws CertificateException {

// TODO Auto-generated method stub

                return true;

            }

        });

        SSLContext sslContext = builder.build();

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(

                sslContext, new X509HostnameVerifier() {

            @Override

            public void verify(String arg0, SSLSocket arg1)

                    throws IOException {

// TODO Auto-generated method stub

            }

            @Override

            public void verify(String arg0, X509Certificate arg1)

                    throws SSLException {

// TODO Auto-generated method stub

            }

            @Override

            public void verify(String arg0, String[] arg1, String[] arg2)

                    throws SSLException {

// TODO Auto-generated method stub

            }

            @Override

            public boolean verify(String hostname, SSLSession session) {

// TODO Auto-generated method stub

                return true;

            }

        });

        Registry socketFactoryRegistry = RegistryBuilder

                .create().register("https", sslsf)

                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(

                socketFactoryRegistry);

        CloseableHttpClient httpclient = HttpClients.custom()

                .setConnectionManager(cm).build();

        return httpclient;

    }

}