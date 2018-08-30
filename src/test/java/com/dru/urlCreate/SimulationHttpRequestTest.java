package com.dru.urlCreate;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

public class SimulationHttpRequestTest {

	@Test
	public void testGetWebSourceStringString() {
		httpRequest shr = new httpRequest();
		String html = shr.getWebSource("http://hotels.ctrip.com/domestic-city-hotel.html", null);
		System.out.println(html);
	}

	@Test
	public void testGetWebSourceStringListOfNameValuePairString() {
		httpRequest shr = new httpRequest();
		
		List<NameValuePair> postParameter = new ArrayList() {};
		postParameter.add(new BasicNameValuePair("cityName","ÑÀ¿ËÊ¯"));
		postParameter.add(new BasicNameValuePair("cityId","1136"));
		postParameter.add(new BasicNameValuePair("cityPY","yakeshi"));
		postParameter.add(new BasicNameValuePair("page","1000"));
		
		String json = shr.getWebSource("http://hotels.ctrip.com/Domestic/Tool/AjaxHotelList.aspx", postParameter, null);
		System.out.println(json);
	}

}
