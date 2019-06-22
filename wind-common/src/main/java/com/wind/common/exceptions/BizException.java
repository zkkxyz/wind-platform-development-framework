package com.wind.common.exceptions;

/**
 * 业务异常基类，所有业务异常都必须继承于此异常
 * 
 * @author linxiaoqing
 */
public class BizException extends BaseException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * BizException:类型异常前缀10
	 */
	private final String exCodePrefix = "10";
	
	public BizException() {
		super();
	}

	public BizException(Throwable cause) {
		super(cause);
	}
	
	public BizException(String message) {
		super(message);
	}
	
	public BizException(String msgFormat, Object... args) {
		super(msgFormat, args);
	}	

	public BizException(Throwable cause,String message) {
		super(cause, message);
	}

	public BizException(Throwable cause,String msgFormat, Object... args) {
		super(cause, msgFormat,args);
	}
	
	public BizException(Integer code, String message) {
		super(code, message);
		super.setExCodePrefix(exCodePrefix);
	}
	

	public BizException(Integer code, String msgFormat, Object... args) {		
		super(code, msgFormat, args);
		super.setExCodePrefix(exCodePrefix);
	}
	
	public BizException(Throwable cause,Integer code, String msgFormat, Object... args) {
		super(cause,code,msgFormat,args);
		super.setExCodePrefix(exCodePrefix);
	}	
	
	
}
