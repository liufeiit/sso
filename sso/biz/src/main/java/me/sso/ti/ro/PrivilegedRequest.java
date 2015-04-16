package me.sso.ti.ro;


/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月7日 上午12:35:55
 */
public class PrivilegedRequest {

	/**
	 * 用于绑定应用系统用户开放ID
	 */
	private String open_id;

	/**
	 * 访问凭证令牌
	 */
	private String access_token;

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
}