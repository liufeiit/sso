package me.sso.ti.result;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月27日 下午4:39:29
 */
public enum ResultCode {
	Success								(200L, 		"success"),
	Err_Exception						(500L, 		"System Business Error."),
	;
	public final long code;
	public final String description;
	
	private ResultCode(long code, String description) {
		this.code = code;
		this.description = description;
	}
}