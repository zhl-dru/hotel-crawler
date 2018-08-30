package com.dru.analysis;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dru.bean.Hotel;
import com.dru.utils.StringUtil;


public class HotelJsonAnalysis {
	/**
	 * @Description:解析出返回数据中包含酒店信息的部分
	 * @param html
	 * @return
	 */
	public String hotelJson(String html) {
		String hotelJson = null;
		if(html != null) {
			int pt1 = html.indexOf("hotelPositionJSON");
			int pt2 = html.indexOf("biRecord");
			int pt3 = html.indexOf("\"htllist\"");
			int pt4 = html.indexOf("\"spreadhotel\"");
			
			//偶尔出现返回一个计划外的页面，提示按照设置的参数未查询到条目，一般是由于网络问题导致的，此时返回一个标志的值，提示重新发送此请求
			if(pt1 != -1&&pt2 != -1&&pt3 != -1&&pt4 != -1) {
				
				String hotel = html.substring(html.indexOf("hotelPositionJSON") - 1, html.indexOf("biRecord") - 1);
				String price = html.substring(html.indexOf("\"htllist\"") + 11, html.indexOf("\"spreadhotel\"") - 2);
				
				StringBuffer sb = new StringBuffer();
				sb.append("{");
				sb.append(hotel);
				sb.append("\"htllist\":");
				sb.append(price);
				sb.append("}");
				
				hotelJson = sb.toString().replaceAll("\\\\", "");
				
			}else {
				hotelJson = "-1";
			}
		}
		return hotelJson;
	}
	
	/**
	 * @Description:封装酒店参数
	 * @param hotelJson
	 * @return
	 */
	public List<Hotel> hotelJsonToJavaBeanList(String hotelJson) {
		
		JSONObject jsonObj = JSONObject.parseObject(hotelJson);
		List<Hotel> hotelList = JSON.parseArray(jsonObj.getString("hotelPositionJSON"), Hotel.class);
		JSONArray prices = jsonObj.getJSONArray("htllist");
		
		if (prices != null && !prices.isEmpty()) {
			for (int i = 0; i < hotelList.size(); i++) {
				JSONObject priceObj = prices.getJSONObject(i);
				if (priceObj != null && !priceObj.isEmpty()) {
					Hotel hotel = hotelList.get(i);
					String hotelId = priceObj.getString("hotelid");
					double price = 0;
					try {
						price = priceObj.getDoubleValue("amount");
					}catch (Exception e) {
					}
					if(hotel.getId().equals(hotelId)) {
						hotel.setPrice(price);
					}
				}
			}
		}
		return hotelList;
	}


	/**
	 * @Description:获取酒店列表的最大页数
	 * @param html
	 * @return
	 */
	public String maxPage(String html) {
		
		if(html.indexOf("paging")!=-1&&html.indexOf("确定")!=-1) {
			String str = html.substring(html.indexOf("paging"), html.indexOf("确定"));
			String maxPage = StringUtil.getNumbers(str.substring(str.indexOf("data-pagecount="),str.length()));
			
			return maxPage;
		}
		return null;
	}
	

}
