package com.nicefish.controller;

import com.nicefish.exception.EmailConflictException;
import com.nicefish.po.POUser;
import com.nicefish.service.UserService;
import com.nicefish.utils.SessionConsts;
import com.nicefish.vo.VOUserLogin;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/access")
public class AccessController extends BaseController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public Object login(@RequestBody POUser user) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		subject.login(new UsernamePasswordToken(user.getEmail(),user.getPassword()));
		user = this.userService.findByEmail(user.getEmail());

		VOUserLogin userLogin = new VOUserLogin();
		BeanUtils.copyProperties(userLogin,user);

		return userLogin;
	}
	
	@RequestMapping(value = "/logout/{userId}", method = RequestMethod.GET)
	public Map<String,Object> logout(@PathVariable String userId,HttpSession session) throws Exception{
		session.removeAttribute(SessionConsts.UserInfo);
		session.removeAttribute(SessionConsts.ImageVertifyCode);
		return this.ajaxSuccessResponse();
    }
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Object register(@RequestBody POUser poUser) throws Exception{
		POUser user = this.userService.findByEmail(poUser.getEmail());
		if(!Objects.isNull(user)){
			throw new EmailConflictException();
		}

		this.userService.insert(poUser);

		VOUserLogin userLogin = new VOUserLogin();
		BeanUtils.copyProperties(userLogin,poUser);
		return userLogin;
    }
}
