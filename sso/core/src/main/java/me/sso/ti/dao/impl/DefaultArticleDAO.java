package me.sso.ti.dao.impl;

import org.springframework.stereotype.Repository;

import me.sso.ti.dao.ArticleDAO;
import me.sso.ti.dataobject.ArticleDO;
import me.sso.ti.jpa.DefaultGenericDAO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 上午12:30:51
 */
@Repository(value = "articleDAO")
public class DefaultArticleDAO extends DefaultGenericDAO<ArticleDO, Long> implements ArticleDAO {
	
}
