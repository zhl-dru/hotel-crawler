package com.dru.datebase;

import java.util.Queue;

import org.junit.Test;

import com.dru.analysis.HotelCityAnalysis;
import com.dru.bean.HotelCity;

public class SQLTest {

	@Test
	public void testSaveHotelCity() {
		HotelCityAnalysis hca = new HotelCityAnalysis();
		Queue<HotelCity> cities = hca.hotelCityExtract();
		SQL sql = new SQL();
		//sql.saveHotelCity(cities);
	}
	
	@Test
	public void testSaveHotel() {
		HotelCityAnalysis hca = new HotelCityAnalysis();
		//Queue<HotelCity> cities = hca.hotelCityExtract();
		SQL sql = new SQL();
		//sql.saveHotel(cities);
		
	}

}
