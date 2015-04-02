package me.sso.ti.request;

import me.sso.ti.ro.QuerySQLRequest;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月31日 下午3:59:18
 */
public class SQLQueryRequest implements QueryRequest {

	private Long id;

	/** SQL的名称 */
	private String name;
	
	private Integer page;
	
	public QuerySQLRequest buildRequest() {
		QuerySQLRequest request = new QuerySQLRequest();
		request.setId(id);
		request.setName(name);
		request.query(page == null ? 1 : page);
		request.setQueryString(getQuery());
		return request;
	}

	@Override
	public String getQuery() {
		StringBuilder sb = new StringBuilder();
		boolean appended = false;
		if (id != null) {
			sb.append("id").append("=").append(id);
			appended = true;
		}
		if (name != null) {
			if (appended) {
				sb.append("&");
			}
			sb.append("name").append("=").append(name);
			appended = true;
		}
		return sb.toString();
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

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
}