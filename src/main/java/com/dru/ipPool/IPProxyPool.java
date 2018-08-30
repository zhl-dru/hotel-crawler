package com.dru.ipPool;

import com.dru.thread.IPProxyPoolThread;

public class IPProxyPool {
	 public static void startExecute(Object lock) {
	        Thread ipProxyPool = new Thread(new IPProxyPoolThread(lock));
	        ipProxyPool.setName("ip-proxy-pool");
	        ipProxyPool.start();
	}
}
