package me.sso.ti.ro;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 上午12:42:26
 */
public class RegisterRequest {
	@NotEmpty
	private String name;

	private String fullname;

	private Byte status;// 状态，1:可用，0:不可用

	private Long avatar;// 头像

	@NotEmpty
	private String password;// 密码

	private String email;// 邮箱

	private String mobile;// 手机

	private String contact;// 联系方式

	private String profile;// 简介

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Long getAvatar() {
		return avatar;
	}

	public void setAvatar(Long avatar) {
		this.avatar = avatar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
}