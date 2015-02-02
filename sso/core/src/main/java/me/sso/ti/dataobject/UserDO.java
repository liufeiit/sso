package me.sso.ti.dataobject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 用户信息。
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月17日 下午11:55:39
 */
@Entity
@Table(name = "user", catalog = "sso")
public class UserDO implements Serializable {

	private static final long serialVersionUID = 829998693975891020L;

	public static final byte Available = 1;
	public static final byte Unavailable = 0;
	public static final byte Strike_out = -1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name", nullable = false, length = 64, unique = true)
	private String name;// 设计师/企业名称

	@Column(name = "fullname", length = 64)
	private String fullname;// 真实姓名

	@Column(name = "status", nullable = false)
	private Byte status;// 状态，1:可用，0:不可用，-1:删除

	@Column(name = "avatar", length = 255)
	private String avatar;// 头像

	@Column(name = "password", nullable = false, length = 64)
	private String password;// 密码

	@Column(name = "salt", nullable = false, length = 64)
	private String salt;// 密码salt

	@Column(name = "email", length = 64, unique = true)
	private String email;// 邮箱

	@Column(name = "mobile", length = 64, unique = true)
	private String mobile;// 手机

	@Column(name = "contact", length = 64)
	private String contact;// 联系方式

	@Column(name = "category_id")
	private Long category_id;// 特长

	@Column(name = "profile", length = 255)
	private String profile;// 简介

	@Column(name = "is_email_verified", nullable = false)
	private Boolean is_email_verified = false;// 邮箱是否验证

	@Column(name = "is_mobile_verified", nullable = false)
	private Boolean is_mobile_verified = false;// 手机是否验证

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login")
	private Date last_login;// 最后一次登录的时间

	@Column(name = "last_ip")
	private Long last_ip;// 最后一次登录的IP

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "gmt_created", nullable = false)
	private Date gmt_created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "gmt_modified", nullable = false)
	private Date gmt_modified;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Boolean getIs_email_verified() {
		return is_email_verified;
	}

	public void setIs_email_verified(Boolean is_email_verified) {
		this.is_email_verified = is_email_verified;
	}

	public Boolean getIs_mobile_verified() {
		return is_mobile_verified;
	}

	public void setIs_mobile_verified(Boolean is_mobile_verified) {
		this.is_mobile_verified = is_mobile_verified;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public Long getLast_ip() {
		return last_ip;
	}

	public void setLast_ip(Long last_ip) {
		this.last_ip = last_ip;
	}

	public Date getGmt_created() {
		return gmt_created;
	}

	public void setGmt_created(Date gmt_created) {
		this.gmt_created = gmt_created;
	}

	public Date getGmt_modified() {
		return gmt_modified;
	}

	public void setGmt_modified(Date gmt_modified) {
		this.gmt_modified = gmt_modified;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
		result = prime * result + ((category_id == null) ? 0 : category_id.hashCode());
		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((gmt_created == null) ? 0 : gmt_created.hashCode());
		result = prime * result + ((gmt_modified == null) ? 0 : gmt_modified.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((is_email_verified == null) ? 0 : is_email_verified.hashCode());
		result = prime * result + ((is_mobile_verified == null) ? 0 : is_mobile_verified.hashCode());
		result = prime * result + ((last_ip == null) ? 0 : last_ip.hashCode());
		result = prime * result + ((last_login == null) ? 0 : last_login.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDO other = (UserDO) obj;
		if (avatar == null) {
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
			return false;
		if (category_id == null) {
			if (other.category_id != null)
				return false;
		} else if (!category_id.equals(other.category_id))
			return false;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.equals(other.contact))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (gmt_created == null) {
			if (other.gmt_created != null)
				return false;
		} else if (!gmt_created.equals(other.gmt_created))
			return false;
		if (gmt_modified == null) {
			if (other.gmt_modified != null)
				return false;
		} else if (!gmt_modified.equals(other.gmt_modified))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (is_email_verified == null) {
			if (other.is_email_verified != null)
				return false;
		} else if (!is_email_verified.equals(other.is_email_verified))
			return false;
		if (is_mobile_verified == null) {
			if (other.is_mobile_verified != null)
				return false;
		} else if (!is_mobile_verified.equals(other.is_mobile_verified))
			return false;
		if (last_ip == null) {
			if (other.last_ip != null)
				return false;
		} else if (!last_ip.equals(other.last_ip))
			return false;
		if (last_login == null) {
			if (other.last_login != null)
				return false;
		} else if (!last_login.equals(other.last_login))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}