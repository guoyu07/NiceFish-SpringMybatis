package com.nicefish.utils;



public class MobileValidate implements IValidate{

	@Override
	public Result<String> Validate(String value) {
		return validate(value);
	}
	
	
	/**
	 * 校验手机号格式是否正确
	 * 1开头 第二位必须是34578中的一个 长度为11的数字
	 * @param value 手机号
	 * @return 校验结果 -2为空 -1格式不正确
	 *  
	 */
	public static Result<String> validate(String value){
		Result<String> result=new Result<String>();
		result.setData("");
		if("".equals(value)){
			result.setStatus(-2);
			result.setMsg("手机号不能为空");
		}else if(value.matches("[1](3|4|5|7|8)[0-9]{9}")){
			result.setStatus(1);
			result.setMsg("校验成功");
		}else{
			result.setStatus(-1);
			result.setMsg("手机号格式不正确");
		}		
		return result;
	}

}