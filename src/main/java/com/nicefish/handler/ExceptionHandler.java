package com.nicefish.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nicefish.utils.Consts;
import com.nicefish.vo.Response;

/**
 * controller 层统一异常处理
 * @author ChengLong
 * @date 2017年5月26日
 */
@ControllerAdvice
public class ExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);
	
	@org.springframework.web.bind.annotation.ExceptionHandler(
			{Exception.class}
			)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Response handleException(Exception e) {
		log.error("rest接口异常", e);
		Response res = new Response(Consts.FAIL, Consts.SYS_ERR_MSG);
		return res;
	}
}
