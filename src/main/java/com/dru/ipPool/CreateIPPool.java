package com.dru.ipPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.dru.analysis.IPAnalysis;
import com.dru.bean.IPPool;
import com.dru.ipPool.IPFilter;

public class CreateIPPool {
	
    private List<IPPool> ip;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public CreateIPPool(List<IPPool> ip) {
        this.ip = ip;
    }

    /**
     * @Description:筛选IP队列,创建IP池
     */
    public void saveIP(Queue<String> urls, Object taskLock) {
        int rand = 0;
        readWriteLock.writeLock().lock();
        String ipAddress = ip.get(rand).getIPAddress();
        String ipPort = ip.get(rand).getIPPort();
        readWriteLock.writeLock().unlock();

        while (true) {
           
            List<IPPool> wfmIp = new ArrayList<>();
            String url;

            // 任务队列是共享变量，对其的读写必须进行正确的同步
            synchronized (taskLock) {
                if (urls.isEmpty()) {
                    System.out.println("当前线程：" + Thread.currentThread().getName() + ", 发现任务队列已空");
                    break;
                }
                url = urls.poll();
            }

            boolean success = IPAnalysis.urlParse(url, ipAddress, ipPort, ip);
            // 如果当前IP不可用则切换下一个IP对本页进行重新抓取
            if (!success) {
                
                readWriteLock.writeLock().lock();
                rand = (int) (Math.random() * ip.size());
                ipAddress = ip.get(rand).getIPAddress();
                ipPort = ip.get(rand).getIPPort();
                readWriteLock.writeLock().unlock();

                synchronized (taskLock) {
                    urls.offer(url);
                }
                continue;
            }
            
            wfmIp = IPFilter.Filter(wfmIp);

            readWriteLock.writeLock().lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + ", 已进入合并区, " +
                    "待合并大小 waiting for merger：" + wfmIp.size());
            ip.addAll(wfmIp);
            System.out.println("当前线程：" + Thread.currentThread().getName() + ", 已成功合并, " +
                    "合并后ip大小：" + ip.size());
            readWriteLock.writeLock().unlock();
        }
    }
}
