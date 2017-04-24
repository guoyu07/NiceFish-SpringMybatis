package com.nicefish.utils;

import javax.servlet.http.HttpSession;

/**
 * 验证码校验
 * @author Lord
 *
 */
public class VercodeValidate {
	
	/**
	 * 验证码校验
	 * @param session Session
	 * @param value 验证码
	 * @return 校验结果 1：成功 -1：验证码为空 -2：验证码错误
	 */
	public static Result<String> validate(HttpSession session,String SessionName,String value){
		Result<String> result=new Result<String>();
		if("".equals(value)){
			result.setStatus(-1);
			result.setMsg("验证码不能为空");
		}else if(!value.equalsIgnoreCase(session.getAttribute(SessionName).toString())){
			result.setStatus(-2);
			result.setMsg("验证码错误");
		}else{
			result.setStatus(1);
			result.setMsg("验证码校验成功");
		}
		return result;
	}
}
