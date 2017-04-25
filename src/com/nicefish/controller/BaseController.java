package com.nicefish.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

public class BaseController {
	protected static final Gson gson=new Gson();
	
	protected String writeJSON(String key,Object value){
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put(key, value);
		return gson.toJson(result);
	}
	
	protected Map<String,Object> ajaxSuccessResponse(){
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success",true);
		return result;
	}
	
	protected Map<String,Object> ajaxSuccessResponse(String msg){
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success",true);
		if(StringUtils.isNotBlank(msg)&&StringUtils.isNotEmpty(msg)){
			result.put("msg",msg);
		}
		return result;
	}
	
	protected Map<String,Object> ajaxFailureResponse(){
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success",false);
		return result;
	}
	
	protected Map<String,Object> ajaxFailureResponse(String msg){
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success",false);
		result.put("msg", msg);
		return result;
	}
}
