package com.wind.common.exceptions;

/**
 * 网络异常
 * @author linxiaoqing
 *
 */
public class NetException extends BaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * NetException:类型异常前缀60
	 */
	private final String exCodePrefix = "60";
	
	public NetException() {
		super();
	}

	public NetException(Throwable cause) {
		super(cause);
	}
	
	public NetException(String message) {
		super(message);
	}
	
	public NetException(String msgFormat, Object... args) {
		super(msgFormat, args);
	}	

	public NetException(Throwable cause,String message) {
		super(cause, message);
	}

	public NetException(Throwable cause,String msgFormat, Object... args) {
		super(cause, msgFormat,args);
	}
	
	public NetException(Integer code, String message) {
		super(code, message);
		super.setExCodePrefix(exCodePrefix);
	}
	

	public NetException(Integer code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
		super.setExCodePrefix(exCodePrefix);
	}
	
	public NetException(Throwable cause,Integer code, String msgFormat, Object... args) {
		super(cause,code,msgFormat,args);
		super.setExCodePrefix(exCodePrefix);
	}	
	
}
