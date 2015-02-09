package me.sso.ti.ro;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 下午10:19:29
 */
public class FavoriteRequest extends BaseRequest {
	@NotEmpty
	private Long article_id;// 被收藏的文章ID

	public Long getArticle_id() {
		return article_id;
	}

	public void setArticle_id(Long article_id) {
		this.article_id = article_id;
	}
}