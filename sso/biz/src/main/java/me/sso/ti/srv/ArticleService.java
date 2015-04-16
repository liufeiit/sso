package me.sso.ti.srv;

import me.sso.ti.result.Result;
import me.sso.ti.ro.ArticleDetailRequest;
import me.sso.ti.ro.ArticleSearchRequest;
import me.sso.ti.ro.CreateArticleRequest;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月9日 下午1:37:10
 */
public interface ArticleService {

	Result search(ArticleSearchRequest request);
	
	Result getArticle(Long id, ArticleDetailRequest request);
	
	Result createArticle(CreateArticleRequest request);
}