package me.sso.ti.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 下午9:48:30
 */
public class SSOStringUtils {
	
	public static String substringWrapper(String string, int start, int length) {
		if(StringUtils.length(string) > length + start) {
			return StringUtils.substring(string, start, length + start) + "...";
		}
		return string;
	}
}