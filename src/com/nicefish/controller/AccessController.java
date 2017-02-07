package com.nicefish.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nicefish.model.User;
import com.nicefish.service.UserService;
import com.nicefish.util.base.UUIDUtil;
import com.nicefish.util.code.MailUtils;
import com.nicefish.util.consts.ConstSessionName;

@Controller
@RequestMapping("/access")
public class AccessController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> login(@RequestBody User user,HttpSession session) throws Exception{
		User userDB=userService.loginName(user.getUserName());
		if(null==userDB){
			return this.ajaxFailureResponse("用户名或者密码错误");
		}
		if(!userDB.getPassword().equals(user.getPassword())){
			return this.ajaxFailureResponse("用户名或者密码错误");
		}
		session.setAttribute(ConstSessionName.UserInfo, userDB);
		return this.ajaxSuccessResponse();
    }
	
	@RequestMapping(value = "/logout/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> logout(@PathVariable String userId,HttpSession session) throws Exception{
		session.removeAttribute(ConstSessionName.UserInfo);
		return this.ajaxSuccessResponse();
    }
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> register(@RequestBody User user) throws Exception{
		user.setUserId(UUIDUtil.generate());
		user.setCode(UUIDUtil.generate()+UUIDUtil.generate());
		int flag = userService.insert(user);
		if(flag > 0){
			MailUtils.sendMail(user.getEmail(),user.getCode());
			return this.ajaxSuccessResponse();
		}
		return this.ajaxFailureResponse();
    }
}
