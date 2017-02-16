package com.nicefish.util.base;

import java.util.UUID;

public class UUIDUtil {

	/**
	 * uuid
	 * @author Lord
	 * @return
	 */
	public static String generate(){
		String uuidString = UUID.randomUUID().toString();
		String idString = uuidString.replace("-","");
		return idString;
	}
}
