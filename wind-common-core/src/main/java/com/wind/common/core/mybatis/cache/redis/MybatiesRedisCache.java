package com.wind.common.core.mybatis.cache.redis;

import org.apache.ibatis.cache.decorators.LoggingCache;

/**
 * @author complone
 *
 */
public class MybatiesRedisCache  extends LoggingCache  {

	public MybatiesRedisCache(String id) {  
        super(new RedisCache(id));  
	}
}
