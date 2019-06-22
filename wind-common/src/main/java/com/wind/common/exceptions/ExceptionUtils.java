package com.wind.common.exceptions;

/**
 * 统一抛异常接口
 * @author linxiaoqing
 *
 */
public class ExceptionUtils {
	
	public static void throwEx(BaseException ex){		
		BaseException.throwEx(ex);
	}
	
}
