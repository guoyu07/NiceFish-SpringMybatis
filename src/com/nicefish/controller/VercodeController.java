package com.nicefish.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nicefish.service.VercodeService;

/**
 * 验证码
 * @author Lord
 * @since 2016-3-15
 * @version 1.0
 */
@Controller
@RequestMapping("/Code")
public class VercodeController {
	
	@Autowired
	private VercodeService vercodeService;

	/**
	 * 获取验证码
	 * @param request 页面的请求
	 * @param response	服务器的响应
	 */
	@RequestMapping(value = "",method=RequestMethod.GET)
	public void doGetCode(HttpServletRequest request, HttpServletResponse response){
		try 
		{
			vercodeService.getCode(request, response);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
