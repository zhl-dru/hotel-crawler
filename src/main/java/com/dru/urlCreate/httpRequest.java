package com.dru.urlCreate;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.dru.bean.IPPool;
import com.dru.datebase.Redis;

public class httpRequest {
	
	
	/**
	 * @Description: Get方式获取网页源码
	 */
	public String getWebSource(String url,String encoding) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet httpGet = new HttpGet(url);

		//设置请求头
		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; X64) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/63.0.3239.132 Safari/537.36");
	
		String html = null;
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			int httpStatus = response.getStatusLine().getStatusCode();
			if(httpStatus==200) {
				if(encoding == null) {
					encoding = "utf-8";
				}
				html = EntityUtils.toString (response.getEntity(), encoding);
			}else {
				System.out.println("发生在Get请求抓取网页HTML文件时返回的状态码为"+httpStatus);
			}
		} catch (IOException e) {
			System.out.println("发生在Get请求抓取网页HTML文件时的其他错误");
			e.printStackTrace();
		}finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

				
		
		return html;
	}
	
	/**
	 * @Description: Post方式获取网页源码
	 */
	public String getWebSource(String url,List<NameValuePair> list,String encoding) {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost httpPost = new HttpPost(url);
		
		String html = null;
		CloseableHttpResponse response = null;
		try {
			if(encoding ==null) {
				encoding = "utf-8";
			}
			httpPost.setEntity(new UrlEncodedFormEntity(list, encoding));
			response = httpClient.execute(httpPost);
			int httpStatus = response.getStatusLine().getStatusCode();
			if(httpStatus == 200) {
				html = EntityUtils.toString (response.getEntity(), encoding);
			}else {
				System.out.println("发生在Post请求抓取网页HTML文件时返回的状态码为"+httpStatus);
			}
		} catch (IOException e) {
			System.out.println("发生在Post请求抓取网页HTML文件时的其他异常");
			e.printStackTrace();
		}finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
		return html;
	}
	
	/**
	 * @Description: 使用代理IP,Get方式获取网页源码
	 */
	public String getWebSource(String url,String encoding,IPPool ip,Object lock) {
		Redis redis = new Redis();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpHost proxy = new HttpHost(ip.getIPAddress(), Integer.parseInt(ip.getIPPort()));
        RequestConfig config = RequestConfig.custom().setProxy(proxy).
        					   setConnectTimeout(1000).setSocketTimeout(1000).build();
		
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);

		//设置请求头
		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; X64) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/63.0.3239.132 Safari/537.36");
	
		String html = null;
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			int httpStatus = response.getStatusLine().getStatusCode();
			if(httpStatus==200) {
				if(encoding == null) {
					encoding = "utf-8";
				}
				html = EntityUtils.toString (response.getEntity(), encoding);
			}else {
				System.out.println("发生在代理Get请求抓取网页HTML文件时返回的状态码为"+httpStatus);
			}
			ip.initCount();
		} catch (IOException e) {
			html = null;
			
			ip.setUseCount();
			synchronized (lock) {
                redis.setIPToList(ip);
			}
			
		}finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return html;
	}
	
	/**
	 * @Description: 使用代理IP,Post方式获取网页源码
	 */
	public String getWebSource(String url,List<NameValuePair> list,String encoding,IPPool ip,Object lock) {
		
		Redis redis = new Redis();	
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpHost proxy = new HttpHost(ip.getIPAddress(), Integer.parseInt(ip.getIPPort()));
        RequestConfig config = RequestConfig.custom().setProxy(proxy).
        					   setConnectTimeout(1000).setSocketTimeout(1000).build();
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(config);
		
		String html = null;
		CloseableHttpResponse response = null;
		try {
			if(encoding ==null) {
				encoding = "utf-8";
			}
			httpPost.setEntity(new UrlEncodedFormEntity(list, encoding));
			response = httpClient.execute(httpPost);
			int httpStatus = response.getStatusLine().getStatusCode();
			if(httpStatus == 200) {
				html = EntityUtils.toString (response.getEntity(), encoding);
			}else {
				System.out.println("发生在代理Post请求抓取网页HTML文件时返回的状态码为"+httpStatus);
			}
			ip.initCount();
		} catch (IOException e) {
			html = null;
			
			ip.setUseCount();
			synchronized (lock) {
                redis.setIPToList(ip);
			}
			
		}finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
		return html;
	}
}
