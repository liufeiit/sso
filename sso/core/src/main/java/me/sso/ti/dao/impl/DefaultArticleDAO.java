package me.sso.ti.dao.impl;

import me.sso.ti.dao.ArticleDAO;
import me.sso.ti.dao.EntityManagerSupportGenericDAO;
import me.sso.ti.dataobject.ArticleDO;

import org.springframework.stereotype.Repository;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年2月9日 上午12:30:51
 */
@Repository("articleDAO")
public class DefaultArticleDAO extends EntityManagerSupportGenericDAO<ArticleDO, Long> implements ArticleDAO {
	
}
