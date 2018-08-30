package com.dru.ipPool;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class IPHttpRequest {
	/**
	 * @Description:使用本地IP爬取含有IP的页面
	 */
    public static String getHtml(String url) {
        String entity = null;
        int httpStatus;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 设置超时处理
        RequestConfig config = RequestConfig.custom().setConnectTimeout(3000).
                setSocketTimeout(3000).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);

        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; X64) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/63.0.3239.132 Safari/537.36");

        try {
            
            response = httpClient.execute(httpGet);
   
            httpStatus = response.getStatusLine().getStatusCode();
            if (httpStatus == 200) {
                entity = EntityUtils.toString(response.getEntity(), "utf-8");
            } else {
                System.out.println("本机IP抓取返回状态码：" + httpStatus);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return entity;
    }

    /**
     * @Description:使用代理IP爬取其余含有IP的页面
     */
    public static String getHtml(String url, String ip, String port) {
        String entity = null;
        int httpStatus;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        // 设置代理访问和超时处理
        HttpHost proxy = new HttpHost(ip, Integer.parseInt(port));
        RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(1000).
                setSocketTimeout(1000).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);

        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; X64) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/63.0.3239.132 Safari/537.36");

        try {
            
            response = httpClient.execute(httpGet);

            httpStatus = response.getStatusLine().getStatusCode();
            if (httpStatus == 200) {
                entity = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println("当前线程：" + Thread.currentThread().getName() + ", 使用的代理IP：" +
                        ip + ":" + port + ", 成功抓取：" + url);
            } else {
                System.out.println("当前线程：" + Thread.currentThread().getName() + ", 使用的代理IP：" +
                        ip + ":" + port + ", 抓取：" + url + ", 返回状态码：" + httpStatus);
            }
        } catch (IOException e) {
            entity = null;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return entity;
}
}
