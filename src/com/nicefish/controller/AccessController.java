package com.nicefish.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nicefish.po.POUser;
import com.nicefish.service.UserService;
import com.nicefish.utils.ConstSessionName;
import com.nicefish.utils.Result;
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
		session.setAttribute(ConstSessionName.UserInfo, voUserLogin);
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
	public Map<String,Object> register(@RequestBody VOUserRegister voUserRegister) throws Exception{
		//检查用户名或者邮件是否已经存在
		POUser poUser=userService.findByUserName(voUserRegister.getUserName());
		if(null!=poUser){
			return this.ajaxSuccessResponse("已经存在相同的用户名");
		}
		poUser=userService.findByEmail(voUserRegister.getEmail());
		if(null!=poUser){
			return this.ajaxSuccessResponse("此邮箱已经被注册");
		}
		
		poUser=new POUser();
		BeanUtils.copyProperties(poUser, voUserRegister);
		poUser.setUserId(UUID.randomUUID().toString());
		
		//TODO：给用户发送注册验证邮件
		
		userService.insert(poUser);
		return this.ajaxSuccessResponse();
    }
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Result<String> getActive(HttpServletRequest request,Model model) throws Exception{
		Result<String> result = new Result<String>();
		
		//接受激活码
		String code = request.getParameter("code");
		
		//根据激活码查询用户
//		User user = userService.findByCode(code);
//		if(StringUtils.isNotBlank(code)&&StringUtils.isNotEmpty(code)){
//			if(user != null){
//				user.setStatus(1);//1已激活
//				user.setCode("");//清空激活码,只激活一次
//				int flag = userService.update(user);
//				if(flag > 0){
//					result.setStatus(flag);
//					result.setMsg("激活成功，请登录！");
//					return result;
//				}
//				
//			}
//			result.setStatus(-1);
//			result.setMsg("你的激活码有误，请重新激活！");
//		}
		return result;
	}
}
