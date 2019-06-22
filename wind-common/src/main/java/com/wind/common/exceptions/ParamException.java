package com.wind.common.exceptions;

/**
 * 参数异常：参数不合法，不符合规范，类型不匹配等
 * @author linxiaoqing
 *
 */
public class ParamException extends BaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * ParamException:类型异常前缀40
	 */
	private final String exCodePrefix = "40";
	
	public ParamException() {
		super();
	}

	public ParamException(Throwable cause) {
		super(cause);
	}
	
	public ParamException(String message) {
		super(message);
	}
	
	public ParamException(String msgFormat, Object... args) {
		super(msgFormat, args);
	}	

	public ParamException(Throwable cause,String message) {
		super(cause, message);
	}

	public ParamException(Throwable cause,String msgFormat, Object... args) {
		super(cause, msgFormat,args);
	}
	
	public ParamException(Integer code, String message) {
		super(code, message);
		super.setExCodePrefix(exCodePrefix);
	}
	

	public ParamException(Integer code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
		super.setExCodePrefix(exCodePrefix);
	}
	
	public ParamException(Throwable cause,Integer code, String msgFormat, Object... args) {
		super(cause,code,msgFormat,args);
		super.setExCodePrefix(exCodePrefix);
	}	
	
}
