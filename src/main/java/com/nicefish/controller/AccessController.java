package com.nicefish.controller;

import com.nicefish.exception.EmailConflictException;
import com.nicefish.po.POUser;
import com.nicefish.service.UserService;
import com.nicefish.utils.SessionConsts;
import com.nicefish.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/access")
public class AccessController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/logout/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> logout(@PathVariable String userId,HttpSession session) throws Exception{
		session.removeAttribute(SessionConsts.UserInfo);
		session.removeAttribute(SessionConsts.ImageVertifyCode);
		return this.ajaxSuccessResponse();
    }
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Object register(@RequestBody POUser poUser) throws Exception{
		POUser user = this.userService.findByEmail(poUser.getEmail());
		if(!Objects.isNull(user)){
			throw new EmailConflictException();
		}
		poUser.setUserId(UuidUtils.generate());
		this.userService.insert(poUser);

		return poUser;
    }
}
