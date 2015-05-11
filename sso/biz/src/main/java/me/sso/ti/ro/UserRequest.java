package me.sso.ti.ro;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月3日 下午4:24:12
 */
public class UserRequest {

	private String deviceId;
	
	private String token;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}