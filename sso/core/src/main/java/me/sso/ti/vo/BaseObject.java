package me.sso.ti.vo;

import java.util.Date;

import me.sso.ti.utils.CalendarUtils;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月3日 下午5:21:59
 */
public class BaseObject {
	
	public String formatDate(Date date) {
		return CalendarUtils.formatDate(date, CalendarUtils.S_YYYY_MM_DD_HH_MM_SS);
	}
}