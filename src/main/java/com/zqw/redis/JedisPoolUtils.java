package com.zqw.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * //TODO
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2018/9/15 19:09
 * @since jdk1.8
 */
public class JedisPoolUtils {
    private JedisPoolUtils(){}
    private static JedisPool jedisPool;

    public static JedisPool getInstance(){
        if(jedisPool == null){
            synchronized (JedisPoolUtils.class){
                if(jedisPool == null){
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    jedisPoolConfig.setMaxTotal(20);
                    jedisPoolConfig.setMaxIdle(10);
                    jedisPool = new JedisPool(jedisPoolConfig, "192.168.72.128", 6379);
                }
            }
        }
        return jedisPool;
    }

    public static void main(String[] args) {
        System.out.println(111);
    }

}
