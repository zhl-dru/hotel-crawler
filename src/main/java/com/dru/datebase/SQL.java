package com.dru.datebase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Queue;

import com.dru.analysis.HotelAnalysis;
import com.dru.bean.Hotel;
import com.dru.bean.HotelCity;
import com.dru.utils.DBUtil;

public class SQL {
	private static Connection connection = DBUtil.getConnection();
	
	/**
	 * @Description:存储用于查找酒店的城市索引信息
	 */
	public void saveHotelCity(Queue<HotelCity> cities) {
		 
		 String sql = "insert into city_hotel (cityId,cityName,headPinyin,cityPinyin) values(?,?,?,?)";
		 try {
			 PreparedStatement statement = connection.prepareStatement(sql);
			 for(HotelCity hotelCity:cities) {
					
					statement.setString(1, hotelCity.getCityId());
					statement.setString(2, hotelCity.getCityName());
					statement.setString(3, hotelCity.getCityPinyin());
					statement.setString(4, hotelCity.getHeadPinyin());
					
					statement.execute();
			}
		 } catch (SQLException e) {
			 
			 e.printStackTrace();
			 System.out.println("存储酒店城市列表时的数据库异常");
		 }
	}
	
	/**
	 * @Description:存储酒店的基本信息
	 */
	public void saveHotel(List<Hotel> hotelList) {
		
		HotelAnalysis ha = new HotelAnalysis();
		
		String sql = "insert into hotel (id,name,lat,lon,url,img,address,score,dpscore,dpcount,star,stardesc,shortName,isSingleRec,price) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement statement = null;
			
		try {
			statement = connection.prepareStatement(sql);
					
				for(Hotel hotel:hotelList) {
	
					try {

						statement.setString(1, hotel.getId());
						statement.setString(2, hotel.getName());
						statement.setDouble(3, hotel.getLat());
						statement.setDouble(4, hotel.getLon());
						statement.setString(5, hotel.getUrl());
						statement.setString(6, hotel.getImg());
						statement.setString(7, hotel.getAddress());
						statement.setDouble(8, hotel.getScore());
						statement.setInt(9, hotel.getDpscore());
						statement.setInt(10, hotel.getDpcount());
						statement.setString(11, hotel.getStar());
						statement.setString(12, hotel.getStardesc());
						statement.setString(13, hotel.getShortName());
						statement.setBoolean(14, hotel.isSingleRec());
						statement.setDouble(15, hotel.getPrice());
						
						statement.execute();
						
					}catch (Exception e) {
							e.getMessage();
							continue;
					}
				}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("存储酒店信息列表时的数据库异常");
		}
	}
	
	
}
