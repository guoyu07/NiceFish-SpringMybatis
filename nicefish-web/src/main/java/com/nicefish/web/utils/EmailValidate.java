package com.nicefish.web.utils;



public class EmailValidate implements IValidate {

	public Result<String> Validate(String value) {
		return validate(value);
	}
	
	
	/**
	 * 校验邮箱是否符合格式
	 * @param value 邮箱
	 * @return 校验结果
	 */
	public static Result<String> validate(String value){
		Result<String> result=new Result<String>();
		result.setData("");
		if("".equals(value)){
			result.setStatus(-1);
			result.setMsg("邮箱不能为空");
		}else if(value.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")){
			result.setStatus(1);
			result.setMsg("邮箱验证成功");
		}else{
			result.setStatus(-1);
			result.setMsg("邮箱格式不正确");
		}
		return result;
	}
}
