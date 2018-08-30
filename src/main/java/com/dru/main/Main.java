package com.dru.main;

import java.util.Queue;

import com.dru.analysis.HotelCityAnalysis;
import com.dru.bean.HotelCity;
import com.dru.ipPool.IPProxyPool;
import com.dru.thread.AnalysisJob;

public class Main {
	public static void main(String[] args) {
		// 创建等待/通知机制所需的对象锁
		Object lock = new Object();
		
		// 创建一个 ippool 线程，执行IP代理池
        IPProxyPool.startExecute(lock);
        
        HotelCityAnalysis hca = new HotelCityAnalysis();
		Queue<HotelCity> cities = hca.hotelCityExtract();
        AnalysisJob.analysisJob(lock, cities);
	}
}
