package me.sso.ti.ro;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月9日 下午1:40:02
 */
public class ArticleSearchRequest extends PrivilegedPageQueryRequest {
	
	private Long id;

	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public ArticleSearchRequest setQueryString() {
		StringBuilder sb = new StringBuilder();
		boolean appended = false;
		if (id != null) {
			sb.append("id").append("=").append(id);
			appended = true;
		}
		if (title != null) {
			if (appended) {
				sb.append("&");
			}
			sb.append("title").append("=").append(title);
			appended = true;
		}
		getQuery().setQueryString(sb.toString());
		return this;
	}
}