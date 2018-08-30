﻿package com.dru.utils;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDBUtil {
 	private static final String addr;
    private static final int port;
    private static final String passwd;

    // 加载配置文件
    private static ResourceBundle rb = ResourceBundle.getBundle("redis-config");

    // 初始化连接
    static {
        addr = rb.getString("jedis.addr");
        port = Integer.parseInt(rb.getString("jedis.port"));
        passwd = rb.getString("jedis.passwd");

        try {
            // 先进行redis数据的参数配置
            JedisPoolConfig config = new JedisPoolConfig();
            // 链接耗尽时是否阻塞，false时抛出异常，默认是true，阻塞超时之后抛出异常
            config.setBlockWhenExhausted(true);
            // 逐出策略类名，当连接超过最大空闲时间或最大空闲数抛出异常
            config.setEvictionPolicyClassName("org.apache.commons.pool2." +
                    "impl.DefaultEvictionPolicy");
            // 是否启用pool的jmx管理功能，默认是true
            config.setJmxEnabled(true);
            // 最大空闲数，默认为8，一个pool最多有多少空闲的Jedis实例
            config.setMaxIdle(60);
            // 最大连接数
            config.setMaxTotal(100);
            // 当引入一个Jedis实例时，最大的等待时间，如果超过等待时间，抛出异常
            config.setMaxWaitMillis(1000*10);
            // 获得一个jedis实例的时候是否检查连接可用性（ping()）
            config.setTestOnBorrow(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // 获取Jedis实例
    public synchronized static Jedis getJedis() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis(addr, port);
        // 权限认证
        jedis.auth(passwd);

        return jedis;
    }

    // 释放Jedis资源
    public static void close(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
