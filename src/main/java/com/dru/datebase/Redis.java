package com.dru.datebase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.dru.bean.IPPool;
import com.dru.utils.RedisDBUtil;
import com.dru.utils.SerializeUtil;

import redis.clients.jedis.Jedis;

public class Redis {
	private final Jedis jedis = RedisDBUtil.getJedis();
    // 创建一个读写锁
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * @Description:将当IP信息保存到Redis列表内
     */
    public void setIPToList(IPPool ip) {
        
        byte[] bytes = SerializeUtil.serialize(ip);

        readWriteLock.writeLock().lock();
        jedis.rpush("ip-proxy-pool".getBytes(), bytes);
        readWriteLock.writeLock().unlock();
    }

    /**
     * @Description:将多个IP信息保存到Redis列表内
     */
    public void setIPToList(List<IPPool> ips) {
        for (IPPool ip : ips) {
            
            byte[] bytes = SerializeUtil.serialize(ip);

            readWriteLock.writeLock().lock();
            jedis.rpush("ip-proxy-pool".getBytes(), bytes);
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * @Description:将Redis中保存的对象进行反序列化
     */
    public IPPool getIPByList() {
        readWriteLock.writeLock().lock();
        Object o = SerializeUtil.unserialize(jedis.lpop("ip-proxy-pool".getBytes()));
        readWriteLock.writeLock().unlock();

        return (IPPool) o;
    }

    /**
     * @Description:判断IP代理池是否为空
     */
    public boolean isEmpty() {
        readWriteLock.readLock().lock();
        Long flag = jedis.llen("ip-proxy-pool".getBytes());
        readWriteLock.readLock().unlock();

        return flag <= 0;
    }

    // 释放Redis资源
    public void close() {
        RedisDBUtil.close(jedis);
    }
}
