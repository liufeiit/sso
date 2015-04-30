package me.sso.ti.dao.impl;

import me.sso.ti.dao.CategoryDAO;
import me.sso.ti.dao.EntityManagerSupportGenericDAO;
import me.sso.ti.dataobject.CategoryDO;

import org.springframework.stereotype.Repository;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年2月9日 上午12:33:22
 */
@Repository("categoryDAO")
public class DefaultCategoryDAO extends EntityManagerSupportGenericDAO<CategoryDO, Long> implements CategoryDAO {
	
}
