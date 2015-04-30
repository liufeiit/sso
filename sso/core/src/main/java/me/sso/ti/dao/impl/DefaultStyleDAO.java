package me.sso.ti.dao.impl;

import me.sso.ti.dao.EntityManagerSupportGenericDAO;
import me.sso.ti.dao.StyleDAO;
import me.sso.ti.dataobject.StyleDO;

import org.springframework.stereotype.Repository;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年2月9日 上午12:37:06
 */
@Repository("styleDAO")
public class DefaultStyleDAO extends EntityManagerSupportGenericDAO<StyleDO, Long> implements StyleDAO {
	
}