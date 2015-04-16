package me.sso.ti.ro;

import me.sso.ti.comms.PageQuery;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月30日 上午10:25:48
 */
public class PrivilegedPageQueryRequest extends PrivilegedPageRequest {

	private PageQuery query;

	public PrivilegedPageQueryRequest query(int page) {
		query = new PageQuery(page);
		return this;
	}

	public PrivilegedPageQueryRequest query(int page_size, int page) {
		query = new PageQuery(page_size, page);
		return this;
	}

	public PageQuery getQuery() {
		return query;
	}

	public void setQuery(PageQuery query) {
		this.query = query;
	}
	
	public PrivilegedPageQueryRequest setQueryString(String queryString) {
		query.setQueryString(queryString);
		return this;
	}
}