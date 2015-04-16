package me.sso.ti.vo;

import java.util.Date;

import me.sso.ti.dataobject.ArticleDO;
import me.sso.ti.utils.SSOStringUtils;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 下午9:34:58
 */
public class ArticleVO extends BaseObject {
	private Long id;
	
	private Long catId;

	private String title;

	private String content;

	private Long avatar;

	private Long background;

	private Long gzip;

	private Byte status;

	private Date gmt_created;
	
	private boolean favorite = false;
	
	public static ArticleVO newInstance(ArticleDO _do, boolean simple) {
		if(_do == null) {
			return null;
		}
		return new ArticleVO().toArticle(_do, simple);
	}
	
	private final ArticleVO toArticle(ArticleDO _do, boolean simple) {
		setAvatar(_do.getAvatar());
		setBackground(_do.getBackground());
		String content = _do.getContent();
		setContent(simple ? SSOStringUtils.substringWrapper(content, 0, 10) : content);
		setGmt_created(_do.getGmt_created());
		setGzip(_do.getGzip());
		setId(_do.getId());
		setCatId(_do.getCatId());
		setStatus(_do.getStatus());
		setTitle(_do.getTitle());
		return this;
	}
	
	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public String getCreated() {
		return formatDate(gmt_created);
	}

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

	public Long getGzip() {
		return gzip;
	}

	public void setGzip(Long gzip) {
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
}