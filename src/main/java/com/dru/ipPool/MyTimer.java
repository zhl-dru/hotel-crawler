package com.dru.ipPool;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import com.dru.thread.TimeJob;

public class MyTimer {
	
	public static void startIPProxyPool(Object lock) {
        TimeJob job = new TimeJob(lock);
        Timer timer = new Timer();

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        // 设置定时任务，从现在开始，每24小时执行一次
        timer.schedule(job, date, 24*60*60*1000);
}
}
