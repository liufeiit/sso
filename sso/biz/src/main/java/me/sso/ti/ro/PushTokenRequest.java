package me.sso.ti.ro;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月30日 下午2:43:09
 */
public class PushTokenRequest extends PrivilegedRequest {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}