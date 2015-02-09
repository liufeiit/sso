package me.sso.ti.srv;

import me.sso.ti.result.Result;
import me.sso.ti.ro.ArticleSearchRequest;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月9日 下午1:37:10
 */
public interface ArticleService {

	Result search(ArticleSearchRequest request);
	
	Result getArticle(Long id);
}