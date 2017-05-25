package com.nicefish.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nicefish.utils.Consts;

/**
 * rest 响应数据包装对象
 * @author ChengLong
 * @date 2017年5月26日
 * @param <T>
 */
@JsonInclude(value = Include.NON_NULL)
public class Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;
	
	private String msg;
	
	Object data;

	public Response() {
		super();
	}

	public Response(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public Response(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
	public void success() {
		this.code = Consts.SUCCESS;
		this.msg = Consts.SUCCESS_MSG;
	}
	
	public void successWithData(Object data) {
		success();
		this.data = data;
	}
	
	public void fail() {
		this.code = Consts.FAIL;
		this.msg = Consts.FAIL_MSG;
	}
	
	public void failWithMsg(String msg) {
		this.code = Consts.FAIL;
		this.msg = msg;
	}
}
