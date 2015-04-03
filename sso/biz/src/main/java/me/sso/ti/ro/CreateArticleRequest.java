package me.sso.ti.ro;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年4月1日 下午10:37:05
 */
public class CreateArticleRequest {
	
	private Boolean enter = Boolean.TRUE;

	private Long catId = 1L;

	private String title;

	private String content;

	private MultipartFile avatar;

	private MultipartFile background;
	
	private MultipartFile gzip;

	public Boolean getEnter() {
		return enter;
	}

	public void setEnter(Boolean enter) {
		this.enter = enter;
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

	public MultipartFile getAvatar() {
		return avatar;
	}

	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
	}

	public MultipartFile getBackground() {
		return background;
	}

	public void setBackground(MultipartFile background) {
		this.background = background;
	}

	public MultipartFile getGzip() {
		return gzip;
	}

	public void setGzip(MultipartFile gzip) {
		this.gzip = gzip;
	}
}