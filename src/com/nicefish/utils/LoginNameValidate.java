package com.nicefish.utils;


/**
 * 登录名验证
 * @author Lord
 *
 */
public class LoginNameValidate implements  IValidate{

	@Override
	public Result<String> Validate(String value) {
		return validate(value);
	}
	
	
	/**
	 * 校验登录名格式
	 * 字母开头 只能包含字母数字及_的长度为6-16位的字符串
	 * @param value 登录名
	 * @return 校验结果 -2 为空 -1格式错误
	 */
	public static Result<String> validate(String value){
		Result<String> result=new Result<String>();
		result.setData("");
		if("".equals(value)){
			result.setStatus(-2);
			result.setMsg("登录名不能为空");
		}else if(value.matches("[a-zA-Z](_|[a-zA-Z0-9]){5,15}")){
			result.setStatus(1);
			result.setMsg("校验成功");
		}else{
			result.setStatus(-1);
			result.setMsg("登录名格式不正确");
		}		
		return result;
	}
}
