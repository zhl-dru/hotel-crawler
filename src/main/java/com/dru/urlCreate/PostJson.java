package com.dru.urlCreate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class PostJson {
	
	/*
	__VIEWSTATEGENERATOR: DB1FBB6D
	cityName: %25E6%25BE%25B3%25E9%2597%25A8
	StartTime: 2018-08-14
	DepTime: 2018-08-15
	RoomGuestCount: 1%2C1%2C0
	txtkeyword: 
	Resource: 
	Room: 
	Paymentterm: 
	BRev: 
	Minstate: 
	PromoteType: 
	PromoteDate: 
	operationtype: NEWHOTELORDER
	PromoteStartDate: 
	PromoteEndDate: 
	OrderID: 
	RoomNum: 
	IsOnlyAirHotel: F
	cityId: 59
	cityPY: macau
	cityCode: 1853
	cityLat: 22.1907410064
	cityLng: 113.5449054207
	positionArea: 
	positionId: 
	hotelposition: 
	keyword: 
	hotelId: 
	htlPageView: 0
	hotelType: F
	hasPKGHotel: F
	requestTravelMoney: F
	isusergiftcard: F
	useFG: F
	HotelEquipment: 
	priceRange: -2
	hotelBrandId: 
	promotion: F
	prepay: F
	IsCanReserve: F
	OrderBy: 99
	OrderType: 
	k1: 
	k2: 
	CorpPayType: 
	viewType: 
	checkIn: 2018-08-14
	checkOut: 2018-08-15
	DealSale: 
	ulogin: 
	hidTestLat: 0%257C0
	AllHotelIds: 436290%252C419823%252C699384%252C8074538%252C694632%252C3386088%252C419374%252C396332%252C533866%252C436478%252C425795%252C346382%252C344977%252C5605870%252C2572033%252C14794556%252C344983%252C345755%252C2038055%252C371377%252C345809%252C2649166%252C419325%252C455509%252C344991
	psid: 
	isfromlist: T
	ubt_price_key: htl_search_result_promotion
	showwindow: 
	defaultcoupon: 
	isHuaZhu: False
	hotelPriceLow: 
	htlFrom: hotellist
	unBookHotelTraceCode: 
	showTipFlg: 
	hotelIds: 436290_1_1%2C419823_2_1%2C699384_3_1%2C8074538_4_1%2C694632_5_1%2C3386088_6_1%2C419374_7_1%2C396332_8_1%2C533866_9_1%2C436478_10_1%2C425795_11_1%2C346382_12_1%2C344977_13_1%2C5605870_14_1%2C2572033_15_1%2C14794556_16_1%2C344983_17_1%2C345755_18_1%2C2038055_19_1%2C371377_20_1%2C345809_21_1%2C2649166_22_1%2C419325_23_1%2C455509_24_1%2C344991_25_1
	markType: 0
	zone: 
	location: 
	type: 
	brand: 
	group: 
	feature: 
	equip: 
	bed: 
	breakfast: 
	other: 
	star: 
	sl: 
	s: 
	l: 
	price: 
	a: 0
	keywordLat: 
	keywordLon: 
	contrast: 0
	PaymentType: 
	CtripService: 
	promotionf: 
	page: 2
	contyped: 0
	productcode: 
	*/
	
	public String postHotelJson(String city,String page,String url) throws ParseException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost httpPost = new HttpPost(url);
		
		
		List<NameValuePair> postParameter = new ArrayList() {};
		
//		postParameter.add(new BasicNameValuePair("__VIEWSTATEGENERATOR","DB1FBB6D"));
		postParameter.add(new BasicNameValuePair("cityName",city));
//		postParameter.add(new BasicNameValuePair("StartTime","2018-08-14"));
//		postParameter.add(new BasicNameValuePair("DepTime","2018-08-15"));
		//postParameter.add(new BasicNameValuePair("RoomGuestCount","1%2C1%2C0"));
		//postParameter.add(new BasicNameValuePair("txtkeyword",""));
		//postParameter.add(new BasicNameValuePair("Resource",""));
		//postParameter.add(new BasicNameValuePair("Room",""));
		//postParameter.add(new BasicNameValuePair("Paymentterm",""));
		//postParameter.add(new BasicNameValuePair("BRev",""));
		//postParameter.add(new BasicNameValuePair("Minstate",""));
		//postParameter.add(new BasicNameValuePair("PromoteType",""));
		//postParameter.add(new BasicNameValuePair("PromoteDate",""));
//		postParameter.add(new BasicNameValuePair("operationtype","NEWHOTELORDER"));
		//postParameter.add(new BasicNameValuePair("PromoteStartDate",""));
		//postParameter.add(new BasicNameValuePair("PromoteEndDate",""));
		//postParameter.add(new BasicNameValuePair("OrderID",""));
		//postParameter.add(new BasicNameValuePair("RoomNum",""));
//		postParameter.add(new BasicNameValuePair("IsOnlyAirHotel","F"));
		postParameter.add(new BasicNameValuePair("cityId","1"));
		postParameter.add(new BasicNameValuePair("cityPY","Biejing"));
//		postParameter.add(new BasicNameValuePair("cityCode","1853"));
//		postParameter.add(new BasicNameValuePair("cityLat","22.1907410064"));
//		postParameter.add(new BasicNameValuePair("cityLng","113.5449054207"));
		//postParameter.add(new BasicNameValuePair("positionArea",""));
		//postParameter.add(new BasicNameValuePair("positionId",""));
		//postParameter.add(new BasicNameValuePair("hotelposition",""));
		//postParameter.add(new BasicNameValuePair("keyword",""));
		//postParameter.add(new BasicNameValuePair("hotelId",""));
//		postParameter.add(new BasicNameValuePair("htlPageView","0"));
//		postParameter.add(new BasicNameValuePair("hotelType","F"));
//		postParameter.add(new BasicNameValuePair("hasPKGHotel","F"));
//		postParameter.add(new BasicNameValuePair("requestTravelMoney","F"));
//		postParameter.add(new BasicNameValuePair("isusergiftcard","F"));
//		postParameter.add(new BasicNameValuePair("useFG","F"));
//		postParameter.add(new BasicNameValuePair("HotelEquipment",""));
//		postParameter.add(new BasicNameValuePair("priceRange","-2"));
		//postParameter.add(new BasicNameValuePair("hotelBrandId",""));
//		postParameter.add(new BasicNameValuePair("promotion","F"));
//		postParameter.add(new BasicNameValuePair("prepay","F"));
//		postParameter.add(new BasicNameValuePair("IsCanReserve","F"));
//		postParameter.add(new BasicNameValuePair("OrderBy","99"));
		//postParameter.add(new BasicNameValuePair("OrderType",""));
		//postParameter.add(new BasicNameValuePair("k1",""));
		//postParameter.add(new BasicNameValuePair("k2",""));
		//postParameter.add(new BasicNameValuePair("CorpPayType",""));
		//postParameter.add(new BasicNameValuePair("viewType",""));
//		postParameter.add(new BasicNameValuePair("checkIn","2018-08-14"));
//		postParameter.add(new BasicNameValuePair("checkOut","2018-08-15"));
		//postParameter.add(new BasicNameValuePair("DealSale",""));
		//postParameter.add(new BasicNameValuePair("ulogin",""));
//		postParameter.add(new BasicNameValuePair("hidTestLat","0%257C0"));
//		postParameter.add(new BasicNameValuePair("AllHotelIds","436290%252C419823%252C699384%252C8074538%252C694632%252C3386088%252C419374%252C396332%252C533866%252C436478%252C425795%252C346382%252C344977%252C5605870%252C2572033%252C14794556%252C344983%252C345755%252C2038055%252C371377%252C345809%252C2649166%252C419325%252C455509%252C344991"));
		//postParameter.add(new BasicNameValuePair("psid",""));
//		postParameter.add(new BasicNameValuePair("isfromlist","T"));
//		postParameter.add(new BasicNameValuePair("ubt_price_key","htl_search_result_promotion"));
		//postParameter.add(new BasicNameValuePair("showwindow",""));
		//postParameter.add(new BasicNameValuePair("defaultcoupon",""));
//		postParameter.add(new BasicNameValuePair("isHuaZhu","False"));
		//postParameter.add(new BasicNameValuePair("hotelPriceLow",""));
//		postParameter.add(new BasicNameValuePair("htlFrom","hotellist"));
		//postParameter.add(new BasicNameValuePair("unBookHotelTraceCode",""));
		//postParameter.add(new BasicNameValuePair("showTipFlg",""));
//		postParameter.add(new BasicNameValuePair("hotelIds","436290_1_1%2C419823_2_1%2C699384_3_1%2C8074538_4_1%2C694632_5_1%2C3386088_6_1%2C419374_7_1%2C396332_8_1%2C533866_9_1%2C436478_10_1%2C425795_11_1%2C346382_12_1%2C344977_13_1%2C5605870_14_1%2C2572033_15_1%2C14794556_16_1%2C344983_17_1%2C345755_18_1%2C2038055_19_1%2C371377_20_1%2C345809_21_1%2C2649166_22_1%2C419325_23_1%2C455509_24_1%2C344991_25_1"));
//		postParameter.add(new BasicNameValuePair("markType","0"));
		//postParameter.add(new BasicNameValuePair("zone",""));
		//postParameter.add(new BasicNameValuePair("location",""));
		//postParameter.add(new BasicNameValuePair("type",""));
		//postParameter.add(new BasicNameValuePair("brand",""));
		//postParameter.add(new BasicNameValuePair("group",""));
		//postParameter.add(new BasicNameValuePair("feature",""));
		//postParameter.add(new BasicNameValuePair("equip",""));
		//postParameter.add(new BasicNameValuePair("bed",""));
		//postParameter.add(new BasicNameValuePair("breakfast",""));
		//postParameter.add(new BasicNameValuePair("other",""));
		//postParameter.add(new BasicNameValuePair("star",""));
		//postParameter.add(new BasicNameValuePair("sl",""));
		//postParameter.add(new BasicNameValuePair("s",""));
		//postParameter.add(new BasicNameValuePair("l",""));
		//postParameter.add(new BasicNameValuePair("price",""));
//		postParameter.add(new BasicNameValuePair("a","0"));
		//postParameter.add(new BasicNameValuePair("keywordLat",""));
		//postParameter.add(new BasicNameValuePair("keywordLon",""));
//		postParameter.add(new BasicNameValuePair("contrast","0"));
		//postParameter.add(new BasicNameValuePair("PaymentType",""));
		//postParameter.add(new BasicNameValuePair("CtripService",""));
		//postParameter.add(new BasicNameValuePair("promotionf",""));
		postParameter.add(new BasicNameValuePair("page",page));
//		postParameter.add(new BasicNameValuePair("contyped","0"));
		//postParameter.add(new BasicNameValuePair("productcode",""));
		
		
		String hotelJson = null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(postParameter, "UTF8"));
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode() == 200) {
				System.out.println("成功");
				hotelJson = EntityUtils.toString (httpResponse.getEntity(), "utf-8");
				System.out.println(hotelJson);
			}else if(httpResponse.getStatusLine().getStatusCode() == 400) {
				hotelJson = null;
				System.out.println(400);
			}else if(httpResponse.getStatusLine().getStatusCode() == 500) {
				hotelJson = null;
				System.out.println(500);
			}else {
				System.out.println("其他错误");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return hotelJson;
		
	}
	
	
}
