package com.nicefish.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nicefish.po.POUser;
import com.nicefish.service.UserService;
import com.nicefish.utils.SessionConsts;
import com.nicefish.vo.VOUserLogin;
import com.nicefish.vo.VOUserRegister;

@Controller
@RequestMapping("/access")
public class AccessController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> login(@RequestBody VOUserLogin voUserLogin,HttpSession session) throws Exception{
		POUser userDB=userService.findByUserName(voUserLogin.getUserName());
		if(null==userDB){
			return this.ajaxFailureResponse("不存在此用户");
		}
		if(!userDB.getPassword().equals(voUserLogin.getPassword())){
			return this.ajaxFailureResponse("用户名或者密码错误");
		}
		session.setAttribute(SessionConsts.UserInfo, voUserLogin);
		return this.ajaxSuccessResponse();
    }
	
	@RequestMapping(value = "/logout/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> logout(@PathVariable String userId,HttpSession session) throws Exception{
		session.removeAttribute(SessionConsts.UserInfo);
		session.removeAttribute(SessionConsts.ImageVertifyCode);
		return this.ajaxSuccessResponse();
    }
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> register(@RequestBody VOUserRegister voUserRegister,HttpSession session) throws Exception{
		POUser poUser=userService.findByUserName(voUserRegister.getUserName());
		if(null!=poUser){
			return this.ajaxSuccessResponse("已经存在相同的用户名");
		}
		poUser=userService.findByEmail(voUserRegister.getEmail());
		if(null!=poUser){
			return this.ajaxSuccessResponse("此邮箱已经被注册");
		}
		voUserRegister.getvCode();
		
		Object vCodeSession=session.getAttribute(SessionConsts.ImageVertifyCode);
		if(null==vCodeSession||!voUserRegister.getvCode().equals(vCodeSession.toString())){
			return this.ajaxSuccessResponse("验证码错误，请重新获取验证码");
		}
		
		poUser=new POUser();
		BeanUtils.copyProperties(poUser, voUserRegister);
		poUser.setUserId(UUID.randomUUID().toString());
		
		//TODO：给用户发送注册验证邮件
		
		userService.insert(poUser);
		return this.ajaxSuccessResponse();
    }
}
