package me.sso.ti.vo;

import java.util.Date;

import me.sso.ti.dataobject.UserDO;
import me.sso.ti.utils.WebUtils;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月27日 下午7:21:05
 */
public class UserVO {

	private String name;

	private String fullname;

	private Byte status;// 状态，1:可用，0:不可用

	private Long avatar;// 头像

	private String email;// 邮箱

	private String mobile;// 手机

	private String contact;// 联系方式

	private String profile;// 简介

	private Date last_login;// 最后一次登录的时间

	private String last_ip;// 最后一次登录的IP

	private Date gmt_created;
	
	/**
	 * 应用ID
	 */
	private String app_id;

	/**
	 * 用于绑定应用系统用户开放ID
	 */
	private String open_id;

	/**
	 * 访问凭证令牌
	 */
	private String access_token;

	/**
	 * 凭证有效时间，单位：秒
	 */
	private Integer expires_in;

	public static UserVO newInstance(UserDO _do) {
		if(_do == null) {
			return null;
		}
		return new UserVO().toUser(_do);
	}
	
	private final UserVO toUser(UserDO _do) {
		setAvatar(_do.getAvatar());
		setContact(_do.getContact());
		setEmail(_do.getEmail());
		setFullname(_do.getFullname());
		setGmt_created(_do.getGmt_created());
		setLast_ip(WebUtils.lngToIp(_do.getLast_ip()));
		setLast_login(_do.getLast_login());
		setMobile(_do.getMobile());
		setName(_do.getName());
		setProfile(_do.getProfile());
		setStatus(_do.getStatus());
		return this;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

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

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}

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

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public String getLast_ip() {
		return last_ip;
	}

	public void setLast_ip(String last_ip) {
		this.last_ip = last_ip;
	}

	public Date getGmt_created() {
		return gmt_created;
	}

	public void setGmt_created(Date gmt_created) {
		this.gmt_created = gmt_created;
	}
}