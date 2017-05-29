package com.nicefish.common.utils;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: IPUtil
 * @version 1.0 
 * @Desc: Ip工具类
 * @author WangShiCong
 * @date 2017-03-11
 * @history v1.0
 */
public class IPUtil {

	public final static Logger LOGGER = Logger.getLogger(IPUtil.class);
	
	/**
	 * 描述：获取IP地址
	 * @author WangShiCong
	 * @date 2017-03-11
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() ==0 || "nuknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() ==0 || "nuknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() ==0 || "nuknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
