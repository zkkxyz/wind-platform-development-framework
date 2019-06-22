package com.wind.common.exceptions;

/**
 * 字节流读取时异常
 * @author linxiaoqing
 *
 */
public class IOException extends BaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * IOException:类型异常前缀50
	 */
	private final String exCodePrefix = "50";
	
	public IOException() {
		super();
	}

	public IOException(Throwable cause) {
		super(cause);
	}
	
	public IOException(String message) {
		super(message);
	}
	
	public IOException(String msgFormat, Object... args) {
		super(msgFormat, args);
	}	

	public IOException(Throwable cause,String message) {
		super(cause, message);
	}

	public IOException(Throwable cause,String msgFormat, Object... args) {
		super(cause, msgFormat,args);
	}
	
	public IOException(Integer code, String message) {
		super(code, message);
		super.setExCodePrefix(exCodePrefix);	
	}
	

	public IOException(Integer code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
		super.setExCodePrefix(exCodePrefix);	
	}
	
	public IOException(Throwable cause,Integer code, String msgFormat, Object... args) {
		super(cause,code,msgFormat,args);
		super.setExCodePrefix(exCodePrefix);	
	}	
	
	
}
