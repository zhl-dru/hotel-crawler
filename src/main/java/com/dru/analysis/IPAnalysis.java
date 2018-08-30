package com.dru.analysis;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.dru.bean.IPPool;
import com.dru.ipPool.IPHttpRequest;

public class IPAnalysis {
	
	/**
	 * @Description:解析抓取到的页面,创建初始IP队列 
	 */
    public static void urlParse(List<IPPool> ipPool) {
        String url = "http://www.xicidaili.com/nn/1";
        String html = IPHttpRequest.getHtml(url);

        
        Document document = Jsoup.parse(html);

        
        Elements trs = document.select("table[id=ip_list]").select("tbody").select("tr");
        getIPMessages(ipPool, trs);
    }

    /**
     * @Description:解析后续获得的页面,创建IP队列
     */
    public static boolean urlParse(String url, String ip, String port,
                                   List<IPPool> ipPool) {
        String html = IPHttpRequest.getHtml(url, ip, port);

        if(html != null) {
            Document document = Jsoup.parse(html);
            Elements trs = document.select("table[id=ip_list]").select("tbody").select("tr");

            getIPMessages(ipPool, trs);

            return true;
        }

        return false;
    }
    
    /**
     * @Description:获取IP信息
     */
    public static void getIPMessages(List<IPPool> ipPool, Elements trs) {
        for (int i = 1; i < trs.size(); i++) {
            IPPool ip = new IPPool();

            String ipAddress = trs.get(i).select("td").get(1).text();
            String ipPort = trs.get(i).select("td").get(2).text();
            String ipType = trs.get(i).select("td").get(5).text();
            String ipSpeed = trs.get(i).select("td").get(6).select("div[class=bar]").
                    attr("title");

            ip.setIPAddress(ipAddress);
            ip.setIPPort(ipPort);
            ip.setIPType(ipType);
            ip.setIPSpeed(ipSpeed);

            ipPool.add(ip);
        }
    }
}
