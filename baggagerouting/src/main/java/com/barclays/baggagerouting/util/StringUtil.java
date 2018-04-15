package com.barclays.baggagerouting.util;

public class StringUtil {

	/**
	 * Check is msg is null or empty
	 * @param msg
	 * @return
	 */
	public static boolean isNotEmpty(String msg) {
		if(msg != null && msg.trim().length()>0)
			return true;
		else 
			return false;
	}
}
