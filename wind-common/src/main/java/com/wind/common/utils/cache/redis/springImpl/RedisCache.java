package com.wind.common.utils.cache.redis.springImpl;


import com.wind.common.exceptions.BizException;
import com.wind.common.utils.cache.redis.RedisUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * 
 * ClassName: RedisCache <br/>
 * Function: 根据SPring API 自定义一个缓存类 ，实现Redis 缓存。<br/>
 * date: 2014-7-28 上午11:10:52 <br/>
 * 
 */

@Component
@SuppressWarnings("unchecked")
public class RedisCache implements Cache {
	/** 缓存的命名属性 **/
	private String name;

	/** 缓存的时间单位秒 **/
	private int cacheSeconds = 3600;// 默认1小时

	public RedisUtils cache = new  RedisUtils();

	public RedisCache() {
	}
	
	/**
	 * 清空所有的缓存
	 */
	public void clear() {
		 RedisUtils.flushAll();
	}

	@Override
	public void evict(Object key) {
		 RedisUtils.del(key);
	}

	/**
	 * 根据Key值获得缓存数据
	 */
	public ValueWrapper get(Object key) {
		ValueWrapper result = null;
		Object thevalue =  RedisUtils.get(key);
		if (thevalue != null) {
			result = new SimpleValueWrapper(thevalue);
		}
		return result;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getNativeCache() {
		return cache;
	}

	@Override
	public <T> T get(Object key, Class<T> type) {
		if (key == null)
			return null;

		Object obj = BeanUtils.instantiateClass(type);

		Object value =  RedisUtils.get(key);
		if (value == null)
			return null;

		BeanUtils.copyProperties(value, obj);
		return (T) obj;
	}

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		Object value =  RedisUtils.get(key);
		if (value != null)
			return (T) value;
		T t = null;
		try {
			t = valueLoader.call();
			if(t!=null)
			 RedisUtils.save(key, t);

		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(e,"valueLoader获取缓存失败:"+e.getMessage());
		}
		return t;
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		put(key, value);
		return new SimpleValueWrapper(value);
	}

	/** 添加缓存 */
	public void put(Object key, Object value) {
		 RedisUtils.save(key, value, cacheSeconds);// 缓存时间
	}

	public RedisCache(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
	
}
