package me.sso.ti.ro;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月16日 下午5:24:37
 */
public class PrivilegedPageRequest extends PrivilegedRequest {

	private Integer page = 1;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
}