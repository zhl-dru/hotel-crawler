package com.dru.analysis;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import com.dru.urlCreate.httpRequest;

public class HotelJsonAnalysisTest {

	@Test
	public void testMaxPage() {
		HotelJsonAnalysis hja = new HotelJsonAnalysis();
		httpRequest shr = new httpRequest();
		
		List<NameValuePair> postParameter = new ArrayList() {};
		postParameter.add(new BasicNameValuePair("cityName","牙克石"));
		postParameter.add(new BasicNameValuePair("cityId","1136"));
		postParameter.add(new BasicNameValuePair("cityPY","yakeshi"));
		postParameter.add(new BasicNameValuePair("page","2"));
		
		String json = shr.getWebSource("http://hotels.ctrip.com/Domestic/Tool/AjaxHotelList.aspx", postParameter, null);
		System.out.println(json);
		String page = hja.maxPage(json);
		System.out.println(page);
	}
	
	@Test
	public void testhotelJson() {
		HotelJsonAnalysis hja = new HotelJsonAnalysis();
		httpRequest shr = new httpRequest();
		
		List<NameValuePair> postParameter = new ArrayList() {};
		postParameter.add(new BasicNameValuePair("cityName","阿拉山口"));
		postParameter.add(new BasicNameValuePair("cityId","1327"));
		postParameter.add(new BasicNameValuePair("cityPY","alashankou"));
		postParameter.add(new BasicNameValuePair("page","2"));
		
		String json = shr.getWebSource("http://hotels.ctrip.com/Domestic/Tool/AjaxHotelList.aspx", postParameter, null);
		String hotelJson = hja.hotelJson(json);
		System.out.println(hotelJson);
		System.out.println(json);
	}
	
	
}
