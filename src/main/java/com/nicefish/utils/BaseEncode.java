package com.nicefish.utils;

import java.io.UnsupportedEncodingException;

public class BaseEncode {
	/**
	 * 处理文字乱码
	 */
	public static String encoding(String key){
		String keyValue = null;
		if (key!=null&&!key.equals("")) {
			try {
				keyValue = new String(key.getBytes("ISO-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return keyValue;
	}
}
