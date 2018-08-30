package com.dru.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TimerTask;

import com.dru.analysis.IPAnalysis;
import com.dru.bean.IPPool;
import com.dru.datebase.Redis;
import com.dru.ipPool.CreateIPPool;
import com.dru.ipPool.IPFilter;

public class TimeJob extends TimerTask {
	// IP代理池线程是生产者，此锁用来实现等待/通知机制，实现生产者与消费者模型
    private final Object lock;

    public TimeJob(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Redis redis = new Redis();
        // 创建一个有关任务队列的读写锁
        Object taskLock = new Object();

        // 如果IP代理池中没有ip信息，则IP代理池进行工作
        while (true) {
            while (redis.isEmpty()) {
                synchronized (lock) {
                    System.out.println("当前线程：" + Thread.currentThread().getName() + ", 开始更新IP代理池");
                    // 存放爬取下来的ip信息
                    List<IPPool> ip = new LinkedList<>();
                    // 创建任务队列
                    Queue<String> urls = new LinkedList<>();
                    // 对创建的子线程进行收集
                    List<Thread> threads = new ArrayList<>();

                    // 首先使用本机ip爬取xici代理网第一页
                    IPAnalysis.urlParse(ip);
                    // 对得到的IP进行筛选，将IP速度在三秒以内的并且类型是https的留下，其余删除
                    ip = IPFilter.Filter(ip);

                    for (IPPool ipMessage : ip) {
                        System.out.println(ipMessage.toString());
                    }

                    // 构造种子url(2000条ip)
                    for (int i = 2; i <= 21; i++) {
                        urls.offer("http://www.xicidaili.com/nn/" + i);
                    }

                    // 使用多线程对urls进行解析并过滤,拿到所有目标IP，将所有的IP存储进共享变量中
                    CreateIPPool createIpProxyPool = new CreateIPPool(ip);
                    for (int i = 0; i < 20; i++) {
                        IPGrabThread IPGrabThread = new IPGrabThread(urls, createIpProxyPool, taskLock);
                        Thread thread = new Thread(IPGrabThread);
                        thread.setName("ip-proxy-pool-thread-" + i);
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

                    // 将爬取下来的ip信息写进Redis数据库中(List集合)
                    redis.setIPToList(ip);

                    System.out.println("当前线程：" + Thread.currentThread().getName() + ", IP代理池已经更新完毕");

                    lock.notifyAll();
                }
            }
        }
}
}
