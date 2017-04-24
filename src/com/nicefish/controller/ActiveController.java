package com.nicefish.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nicefish.model.User;
import com.nicefish.service.UserService;
import com.nicefish.utils.Result;

@Controller
@RequestMapping("/active")
public class ActiveController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Result<String> getActive(HttpServletRequest request,Model model) throws Exception{
		Result<String> result = new Result<String>();
		
		//接受激活码
		String code = request.getParameter("code");
		
		//根据激活码查询用户
		User user = userService.findByCode(code);
		if(StringUtils.isNotBlank(code)&&StringUtils.isNotEmpty(code)){
			if(user != null){
				user.setStatus(1);//1已激活
				user.setCode("");//清空激活码,只激活一次
				int flag = userService.update(user);
				if(flag > 0){
					result.setStatus(flag);
					result.setMsg("激活成功，请登录！");
					return result;
				}
				
			}
			result.setStatus(-1);
			result.setMsg("你的激活码有误，请重新激活！");
		}
		return result;
	}
}
