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
	public Object login(@RequestBody VOUserLogin voUserLogin,HttpSession session) throws Exception{
		POUser userDB=userService.findByEmail(voUserLogin.getEmail());
		if(null==userDB){
			return this.ajaxFailureResponse("用户不存在或者密码错误");
		}
		if(!userDB.getPassword().equals(voUserLogin.getPassword())){
			return this.ajaxFailureResponse("用户不存在或者密码错误");
		}
		session.setAttribute(SessionConsts.UserInfo, userDB);
		return userDB;
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
	public Object register(@RequestBody VOUserRegister voUserRegister,HttpSession session) throws Exception{
		POUser poUser=userService.findByEmail(voUserRegister.getEmail());
		if(null!=poUser){
			return this.ajaxFailureResponse("此邮箱已经被注册");
		}
		
		poUser=userService.findByNickName(voUserRegister.getNickName());
		if(null!=poUser){
			return this.ajaxFailureResponse("此昵称已经被注册");
		}
		//TODO:检查图片验证码
//		Object vCodeSession=session.getAttribute(SessionConsts.ImageVertifyCode);
//		if(null==vCodeSession||!voUserRegister.getvCode().equals(vCodeSession.toString())){
//			return this.ajaxSuccessResponse("验证码错误，请重新获取验证码");
//		}
		
		poUser=new POUser();
		BeanUtils.copyProperties(poUser, voUserRegister);
		poUser.setUserId(UUID.randomUUID().toString());
		
		//TODO：给用户发送注册验证邮件
		int affected=userService.insert(poUser);
		if(affected>0){
			//注册成功自动切换到登录状态
			session.setAttribute(SessionConsts.UserInfo, poUser);
			return poUser;			
		}else{
			return this.ajaxFailureResponse();
		}
    }
}
