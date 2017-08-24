package com.nicefish.web.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码
 * @author Lord
 * @since 2016-3-15
 * @version 1.0
 */
public interface VercodeService {
	
	/**
	 * 获取验证码
	 * @param req 页面的请求
	 * @param resp 服务器的响应
	 * @throws IOException 
	 */
	public void getCode(HttpServletRequest req, HttpServletResponse resp)throws IOException;

}
