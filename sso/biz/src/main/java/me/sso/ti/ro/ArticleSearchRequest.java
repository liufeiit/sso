package me.sso.ti.ro;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月9日 下午1:40:02
 */
public class ArticleSearchRequest {
	
	private Long id;

	private String title;
	
	private Integer page;

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

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
}