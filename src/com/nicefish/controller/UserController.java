package com.nicefish.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nicefish.po.POUser;
import com.nicefish.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getUserInfo/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Object getUserInfo(@PathVariable String userId,HttpSession session) throws Exception{
		POUser userDB=userService.findById(userId);
		return userDB;
    }
}
