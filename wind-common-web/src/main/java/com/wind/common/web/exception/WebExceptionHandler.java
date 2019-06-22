package com.wind.common.web.exception;


import com.wind.common.entity.BaseResult;
import com.wind.common.exceptions.BizException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Spring异常拦截器.
 * 
 * @author linxiaoqing
 *
 */
@ControllerAdvice
public class WebExceptionHandler {

	private static final Log LOG = LogFactory.getLog(WebExceptionHandler.class);

	/**
	 * 没有权限 异常 后续根据不同的需求定制即可
	 * 
	 */
	@ExceptionHandler({ BizException.class })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Object processBizException(HttpServletRequest request, BizException e) {
		LOG.error("BizException", e);
		return new BaseResult(false, e.getCode(), e.getMessage(), null);
	}

	/**
	 * 总异常
	 */
	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Object processException(Exception e, HttpServletRequest request) {
		LOG.error("Exception", e);
		return new BaseResult(false, 10000, e.getMessage(), null);
	}

}
