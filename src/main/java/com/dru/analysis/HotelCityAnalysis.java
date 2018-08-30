package com.dru.analysis;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dru.bean.HotelCity;
import com.dru.utils.StringUtil;


public class HotelCityAnalysis {
	/**
	 * @Description:从携程酒店索引页获取所有城市的属性
	 */
	public Queue<HotelCity> hotelCityExtract() {
		Queue<HotelCity> cities = new LinkedList<>();
		String url = "http://hotels.ctrip.com/domestic-city-hotel.html";
		Document html = null;
		try {
			html = Jsoup.connect(url).timeout(10000).get();
		} catch (IOException e) {
			System.out.println("在抓取酒店索引页时出现异常");
			e.printStackTrace();
		}
		
		Elements allCityElements = html.getElementsByClass("pinyin_filter_detail");
		Element pinyinFilter = allCityElements.first();
		Elements pinyins = pinyinFilter.getElementsByTag("dt");
		Elements hotels = pinyinFilter.getElementsByTag("dd");
		
		for(int i = 0;i < pinyins.size();i++) {
			Element headPinyin = pinyins.get(i);
			Element hotelsLink = hotels.get(i);
			Elements links = hotelsLink.children();
			for(Element link:links) {
				String pinyinAndCityId = link.attr("href").replace("/hotel/", "");
				String pinyin = pinyinAndCityId.replace(StringUtil.getNumbers(link.attr("href")), "");
				HotelCity city = new HotelCity();
				city.setCityId(StringUtil.getNumbers(link.attr("href")));
				city.setCityName(link.html());
				city.setHeadPinyin(headPinyin.html());
				city.setCityPinyin(pinyin);
				cities.add(city);
			}
		}
		
		return cities;
	}
}
