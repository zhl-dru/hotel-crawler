package com.dru.thread;

import com.dru.ipPool.MyTimer;

public class IPProxyPoolThread implements Runnable {

	private final Object lock;

    public IPProxyPoolThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        MyTimer.startIPProxyPool(lock);
}

}
