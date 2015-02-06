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

	private Long id;

	private String name;

	private String fullname;

	private Byte status;// 状态，1:可用，0:不可用

	private String avatar;// 头像

	private String email;// 邮箱

	private String mobile;// 手机

	private String contact;// 联系方式

	private String profile;// 简介

	private Date last_login;// 最后一次登录的时间

	private String last_ip;// 最后一次登录的IP

	private Date gmt_created;

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
		setId(_do.getId());
		setLast_ip(WebUtils.lngToIp(_do.getLast_ip()));
		setLast_login(_do.getLast_login());
		setMobile(_do.getMobile());
		setName(_do.getName());
		setProfile(_do.getProfile());
		setStatus(_do.getStatus());
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
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