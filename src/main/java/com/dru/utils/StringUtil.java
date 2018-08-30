package com.dru.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	/**
	 * @Description:从字符串中获得数字
	 */
	public static String getNumbers(String str){
		if (str != null) {   
			Pattern pattern = Pattern.compile("[^0-9]");   
			Matcher matcher = pattern.matcher(str);   
			return matcher.replaceAll("").trim();
		}
		return "";
		}
}
