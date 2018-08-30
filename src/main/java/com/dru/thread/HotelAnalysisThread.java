package com.dru.thread;

import java.util.List;
import java.util.Queue;

import com.dru.analysis.HotelAnalysis;
import com.dru.bean.Hotel;
import com.dru.bean.HotelCity;
import com.dru.bean.IPPool;
import com.dru.datebase.Redis;
import com.dru.datebase.SQL;

public class HotelAnalysisThread implements Runnable {
	
	private final Object lock;                      // 用于与 ip-proxy-pool 进行协作的锁
   
    private Queue<HotelCity> cities;         // 任务队列
	
   

    // 关于MySQL数据库的锁
    private static final Object mySQLLock = new Object();

    public HotelAnalysisThread(Object lock, Queue<HotelCity> cities) {
        this.lock = lock;
        
        this.cities = cities;
        
    }

    @Override
    public void run() {
        Redis redis = new Redis();
        SQL sql = new SQL();

        IPPool ip = null;
        HotelCity city;
        boolean flag = true;

        while (true) {
            if (flag) {
                synchronized (lock) {
                    while (redis.isEmpty()) {
                        try {
                            System.out.println("当前线程：" + Thread.currentThread().getName() + ", " +
                                    "发现ip-proxy-pool已空, 开始进行等待... ...");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    ip = redis.getIPByList();
                }
            }

            
            	
            	if (ip.getUseCount() >= 30) {
            		System.out.println("当前线程：" + Thread.currentThread().getName() + ", 发现此ip：" +
            				ip.getIPAddress() + ":" + ip.getIPPort() + ", 已经连续30次不能使用, 进行舍弃");
            		continue;
            	}
            	synchronized (cities) {
            		if (!cities.isEmpty()) {
            			city = cities.poll();
            		} else {
            			System.out.println("当前线程：" + Thread.currentThread().getName() + ", 发现任务队列已空");
            			break;
            		}
            	}
            	
            	List<Hotel> hotelList = HotelAnalysis.hotelList(city, ip, lock);
            	if(hotelList != null) {
            		
            		
            		synchronized (mySQLLock) {
            			System.out.println("当前线程：" + Thread.currentThread().getName() + ", " +
            					"准备将cities写进MySQL数据库, cities-size：" + cities.size());
            			sql.saveHotel(hotelList);
            		}
            		
            		
            		flag = false;
            	} else {
            		synchronized (cities) {
            			cities.offer(city);
            		}
            		flag = true;
            	}

        }
    }
}

