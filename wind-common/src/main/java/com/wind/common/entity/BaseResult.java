package com.wind.common.entity;

/**
 * 统一前端返回格式jason
 * @author linxiaoqing
 *
 */
public class BaseResult {
	/** 是否成功 */
	private boolean success;
	/**存在异常时的异常码 */
	private Integer code=0;
	/** 返回信息 */
	private String message;
	/** 返回数据 */
	private Object data;

	public BaseResult() {
	}

	/**
	 * @param success 是否成功 true为是 false 为否
	 */
	public BaseResult(boolean success) {
		this.success = success;
	}

	/**
	 * @param success 是否成功 true为是 false 为否
	 * @param message 信息
	 */
	public BaseResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	/**
	 * @param success 是否成功 true为是 false 为否
	 * @param code 异常码
	 * @param message 信息
	 */
	public BaseResult(boolean success,Integer code, String message) {
		this.success = success;
		this.code = code;
		this.message = message;
	}	

	/**
	 * @param success 是否成功 true为是 false 为否
	 * @param message 信息
	 * @param data 数据
	 */
	public BaseResult(boolean success, String message, Object data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}
	
	/**
	 * @param success 是否成功 true为是 false 为否
	 * @param code 异常码
	 * @param message 信息
	 * @param data 数据
	 */
	public BaseResult(boolean success,Integer code, String message, Object data) {
		this.success = success;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

}
