package me.sso.ti.ro;

import me.sso.ti.comms.PageQuery;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月16日 下午5:28:54
 */
public class NonPrivilegedPageQueryRequest extends NonPrivilegedPageRequest {

	private PageQuery query;

	public NonPrivilegedPageQueryRequest query(int page) {
		query = new PageQuery(page);
		return this;
	}

	public NonPrivilegedPageQueryRequest query(int page_size, int page) {
		query = new PageQuery(page_size, page);
		return this;
	}

	public PageQuery getQuery() {
		return query;
	}

	public void setQuery(PageQuery query) {
		this.query = query;
	}
	
	public NonPrivilegedPageQueryRequest setQueryString(String queryString) {
		query.setQueryString(queryString);
		return this;
	}
}