package com.nicefish.utils;


public class PasswordValidate implements IValidate {

	@Override
	public Result<String> Validate(String value) {
		return validate(value);
	}
	
	
	/**
	 * 校验密码是否符合格式
	 * 长度为以数字或字母开头的6-16位的数字字母_@&#+-
	 * @param value 密码
	 * @return 
	 */
	public static Result<String> validate(String value){
		Result<String> result=new Result<String>();
		result.setData("");
		if("".equals(value)){
			result.setStatus(-2);
			result.setMsg("密码不能为空");
		}else if(value.matches("[0-9a-zA-Z]([0-9a-zA-Z]|_|@|#|\\+|-){5,15}")){
			result.setStatus(1);
			result.setMsg("校验成功");
		}else{
			result.setStatus(-1);
			result.setMsg("密码格式不正确");
		}		
		return result;
	}

}
