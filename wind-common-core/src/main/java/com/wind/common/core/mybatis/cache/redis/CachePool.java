package com.wind.common.core.mybatis.cache.redis;

import com.wind.common.utils.cache.redis. RedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * redis 初始化与连接池实现
 * 由于需结合Mybatis实现 不与Spring redis注解实现混用。
 * 与Spring redis注解实现 各独立实现各自功能。
 * @author complone
 *
 */

public class CachePool {

	JedisPool pool;  
     private static final CachePool cachePool = new CachePool();  
     //private  RedisUtils readisUtils = new RedisUtils();
     
     /**单例模式*/
     public static CachePool getInstance(){  
             return cachePool;  
     }  
     
     /**初始化*/
     private CachePool() {  
              pool =  RedisUtils.getJedisPool();
     }  
     
     public  Jedis getJedis(){  
             Jedis jedis = null;  
             boolean borrowOrOprSuccess = true;  
             try {  
                     jedis = pool.getResource();  
             } catch (JedisConnectionException e) {  
                     borrowOrOprSuccess = false;  
                     if (jedis != null)  
                             pool.returnBrokenResource(jedis);  
             } finally {  
                     if (borrowOrOprSuccess)  
                             pool.returnResource(jedis);  
             }  
             jedis = pool.getResource();  
             return jedis;  
     }  
       
     public JedisPool getJedisPool(){  
             return this.pool;  
     }  
}
