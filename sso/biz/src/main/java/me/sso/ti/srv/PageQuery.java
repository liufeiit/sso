package me.sso.ti.srv;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月9日 下午1:45:57
 */
public class PageQuery {

	private final static int DEFAULT_PAGE_SIZE = 10;

	private final int index;
	private final int size;

	public PageQuery(int page) {
		this(page, DEFAULT_PAGE_SIZE);
	}

	public PageQuery(int page, int size) {
		super();
		page = page < 1 ? 1 : page;
		index = (page - 1) * size;
		this.size = size;
	}

	public int getIndex() {
		return index;
	}

	public int getSize() {
		return size;
	}
}