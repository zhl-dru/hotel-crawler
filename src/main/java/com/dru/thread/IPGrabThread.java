package com.dru.thread;

import java.util.Queue;

import com.dru.ipPool.CreateIPPool;

public class IPGrabThread implements Runnable {

	// 所有线程共享任务队列
    private Queue<String> urls;
    private CreateIPPool createIpProxyPool;
    private Object taskLock;

    public IPGrabThread(Queue<String> urls, CreateIPPool createIpProxyPool, Object taskLock) {
        this.urls = urls;
        this.createIpProxyPool = createIpProxyPool;
        this.taskLock = taskLock;
    }

    @Override
    public void run() {
        createIpProxyPool.saveIP(urls, taskLock);
}
}
