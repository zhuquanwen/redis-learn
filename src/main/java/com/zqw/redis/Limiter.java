package com.zqw.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * //TODO
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2018/9/15 19:06
 * @since jdk1.8
 */
public class Limiter {
    public static void main(String[] args) {
        JedisPool jedisPool = JedisPoolUtils.getInstance();
        Jedis jedis = jedisPool.getResource();
        try {
            String lua = "local num = redis.call('incr', KEYS[1])\n" +
                    "if tonumber(num) == 1 then\n" +
                    "\tredis.call('expire', KEYS[1], ARGV[1])\n" +
                    "\treturn 1\n" +
                    "elseif tonumber(num) > tonumber(ARGV[2]) then\n" +
                    "\treturn 0\n" +
                    "else \n" +
                    "\treturn 1\n" +
                    "end\n";
            Object result = jedis.evalsha(jedis.scriptLoad(lua), Arrays.asList("localhost"), Arrays.asList("10", "2"));
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis != null){
                try {
                    jedis.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
