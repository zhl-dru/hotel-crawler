package com.dru.analysis;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.dru.bean.Hotel;
import com.dru.bean.HotelCity;
import com.dru.bean.IPPool;
import com.dru.urlCreate.httpRequest;

public class HotelAnalysis {
	
	/**
	 * @Description:拼接post请求参数
	 * @param:
	 */
	public static List<NameValuePair> postParameterSpell(HotelCity city,String page){
		
		List<NameValuePair> postParameter = new ArrayList() {};
		
		postParameter.add(new BasicNameValuePair("cityName",city.getCityName()));
		postParameter.add(new BasicNameValuePair("cityId",city.getCityId()));
		postParameter.add(new BasicNameValuePair("cityPY",city.getCityPinyin()));
		postParameter.add(new BasicNameValuePair("page",page));
		
		return postParameter;
	}
	
	/**
	 * @Description:遍历城市的酒店列表
	 */
	public static List<Hotel> hotelList(HotelCity city,IPPool ip,Object lock) {
		
		httpRequest shr = new httpRequest();
		HotelJsonAnalysis hja = new HotelJsonAnalysis();
		
		String url = "http://hotels.ctrip.com/Domestic/Tool/AjaxHotelList.aspx";
		String maxPage = hja.maxPage(shr.getWebSource(url, postParameterSpell(city,"1"), null));
		
		List<Hotel> hotelList = new ArrayList();
		List<Hotel> interimList = new ArrayList();
		
		if(maxPage != null) {
			
			pageFilter:for(int i=1;i<=Integer.valueOf(maxPage);i++) {
				String html = null;
				String hotelJson = null;
				
				html = shr.getWebSource(url, postParameterSpell(city,String.valueOf(i)), null, ip, lock);
				if(html != null) {
					hotelJson = hja.hotelJson(html);
					if(hotelJson == "-1") {
						
						for(int count = 1;count < 3;count++) {
							
							html = shr.getWebSource(url, postParameterSpell(city,String.valueOf(i)), null);
							if(html != null) {
								hotelJson = hja.hotelJson(html);
								if(hotelJson == "-1") {
									System.out.println("发生在抓取"+city.getCityName()+"第"+i+"页时的最大页数不符合");
									break pageFilter;
								}else 
									break;
							}else {
								break;
							}
							
						}
					}
				}else{
					return null;
				}
				
				
				if((interimList = hja.hotelJsonToJavaBeanList(hotelJson))!=null) {
					
					hotelList.addAll(interimList);
				}
			}
		}
		
		
		return hotelList;	
	
		
	}
}
