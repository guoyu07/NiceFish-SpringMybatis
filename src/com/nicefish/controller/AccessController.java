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
import com.nicefish.util.consts.ConstSessionName;

@Controller
@RequestMapping("/access")
public class AccessController extends BaseController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> login(@RequestBody User user,HttpSession session) throws Exception{
		User userDB=userService.findById(user.getUpId());
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
		if(this.userService.createUser(user)){
			return this.ajaxSuccessResponse();
		}
		return this.ajaxFailureResponse();
    }
}
