package com.wind.common.utils;


import  com.wind.common.utils.bean. BeanUtils;
import  com.wind.common.utils.cache.redis. RedisUtils;
import  com.wind.common.utils.check. CheckUtils;
import  com.wind.common.utils.collection. CollectionUtils;
import  com.wind.common.utils.date. DateUtils;
import  com.wind.common.utils.file. FileUtils;

import com.wind.common.utils.httpclient.HttpUtils;
import  com.wind.common.utils.json. JsonUtils;
import  com.wind.common.utils.math. AmountUtils;
import  com.wind.common.utils.math. NumberUtils;
import  com.wind.common.utils.object. ObjectUtils;
import  com.wind.common.utils.oss. OssUtils;
import  com.wind.common.utils.properties. ResourceUtils;
import  com.wind.common.utils.string. StringUtils;
import  com.wind.common.utils.string. UUIDUitls;
import  com.wind.common.utils.system. SystemUtils;

@SuppressWarnings("unused")
public class Wind {
	
	
	public final static StringUtils string = new StringUtils();
	
	public final static DateUtils date = new DateUtils();
	
	public final static BeanUtils bean = new BeanUtils();
	
	public final static RedisUtils cache = new  RedisUtils();
	
	public final static HttpUtils simpleHttp = new  HttpUtils();
	
	public final static  FileUtils file = new  FileUtils();
	
	public final static  JsonUtils json = new  JsonUtils();
	
	public final static  ObjectUtils object = new  ObjectUtils();
	
	public final static  CheckUtils check = new  CheckUtils();
	
	public final static  CollectionUtils collection = new  CollectionUtils();

	public final static  OssUtils oss = new  OssUtils();

	public final static  SystemUtils system = new  SystemUtils();
	
	public final static  NumberUtils number = new  NumberUtils();
	
	public final static  AmountUtils amount = new  AmountUtils();
	
	public final static  UUIDUitls UUID = new  UUIDUitls();
	
	public final static  ResourceUtils properties = new  ResourceUtils();
		
	
	public static void main(String[] args) {
		
		
	}
	
}
