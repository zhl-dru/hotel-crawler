package com.dru.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.dru.bean.HotelCity;

public class AnalysisJob {
	public static void analysisJob(Object lock, Queue<HotelCity> cities) {
        
        List<Thread> threads = new ArrayList<>();


        // 创建20个线程，用于解析任务队列
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new HotelAnalysisThread(lock, cities));
            thread.setName("cities" + i);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}
}
