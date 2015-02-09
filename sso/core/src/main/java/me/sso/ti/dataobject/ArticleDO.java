package me.sso.ti.dataobject;

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
 * 文章
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月6日 下午2:38:29
 */
@Entity
@Table(name = "article", catalog = "sso")
public class ArticleDO extends BaseDO {

	private static final long serialVersionUID = 1L;

	/** 待审核 **/
	public static final byte PendingAudit = 1;
	/** 审核通过 **/
	public static final byte AuditPass = 2;
	/** 已发布 **/
	public static final byte Published = 3;
	/** 审核未通过 **/
	public static final byte AuditUnPass = 4;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "cat_id", nullable = false)
	private Long catId;

	@Column(name = "title", unique = true, nullable = false, length = 256)
	private String title;

	@Column(name = "content", length = 2048)
	private String content;

	@Column(name = "avatar")
	private Long avatar;

	@Column(name = "background")
	private Long background;

	@Column(name = "gzip", length = 255)
	private String gzip;

	@Column(name = "status", nullable = false)
	private Byte status;

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

	public Long getCatId() {
		return catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getAvatar() {
		return avatar;
	}

	public void setAvatar(Long avatar) {
		this.avatar = avatar;
	}

	public Long getBackground() {
		return background;
	}

	public void setBackground(Long background) {
		this.background = background;
	}

	public String getGzip() {
		return gzip;
	}

	public void setGzip(String gzip) {
		this.gzip = gzip;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
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
		result = prime * result + ((background == null) ? 0 : background.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((gmt_created == null) ? 0 : gmt_created.hashCode());
		result = prime * result + ((gmt_modified == null) ? 0 : gmt_modified.hashCode());
		result = prime * result + ((gzip == null) ? 0 : gzip.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		ArticleDO other = (ArticleDO) obj;
		if (avatar == null) {
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
			return false;
		if (background == null) {
			if (other.background != null)
				return false;
		} else if (!background.equals(other.background))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
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
		if (gzip == null) {
			if (other.gzip != null)
				return false;
		} else if (!gzip.equals(other.gzip))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}