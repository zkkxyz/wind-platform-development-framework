package com.wind.common.exceptions;

/**
 * 持久化异常：
 * @author linxiaoqing
 *
 */
public class DaoException extends BaseException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * DaoException:类型异常前缀20
	 */
	private final String exCodePrefix = "20";

	public DaoException() {
		super();
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
	
	public DaoException(String message) {
		super(message);
	}
	
	public DaoException(String msgFormat, Object... args) {
		super(msgFormat, args);
	}	

	public DaoException(Throwable cause,String message) {
		super(cause, message);
	}

	public DaoException(Throwable cause,String msgFormat, Object... args) {
		super(cause, msgFormat,args);
	}
	
	public DaoException(Integer code, String message) {
		super(code, message);
		super.setExCodePrefix(exCodePrefix);
	}
	

	public DaoException(Integer code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
		super.setExCodePrefix(exCodePrefix);
	}
	
	public DaoException(Throwable cause,Integer code, String msgFormat, Object... args) {
		super(cause,code,msgFormat,args);
		super.setExCodePrefix(exCodePrefix);
	}	
	
	
}
