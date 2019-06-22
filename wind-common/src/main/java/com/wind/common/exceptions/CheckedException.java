package com.wind.common.exceptions;

/**
 * 校验异常：数据校验异常
 * @author linxiaoqing
 *
 */
public class CheckedException extends BaseException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * CheckedException:类型异常前缀30
	 */
	private final String exCodePrefix = "30";

	public CheckedException() {
		super();
	}

	public CheckedException(Throwable cause) {
		super(cause);
	}
	
	public CheckedException(String message) {
		super(message);
	}
	
	public CheckedException(String msgFormat, Object... args) {
		super(msgFormat, args);
	}	

	public CheckedException(Throwable cause,String message) {
		super(cause, message);
	}

	public CheckedException(Throwable cause,String msgFormat, Object... args) {
		super(cause, msgFormat,args);
	}
	
	public CheckedException(Integer code, String message) {
		super(code, message);
		super.setExCodePrefix(exCodePrefix);		
	}
	

	public CheckedException(Integer code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
		super.setExCodePrefix(exCodePrefix);		
	}
	
	public CheckedException(Throwable cause,Integer code, String msgFormat, Object... args) {
		super(cause,code,msgFormat,args);
		super.setExCodePrefix(exCodePrefix);		
	}	
	
}
