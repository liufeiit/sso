package me.sso.ti.dao.impl;

import org.springframework.stereotype.Repository;

import me.sso.ti.dao.StyleDAO;
import me.sso.ti.dataobject.StyleDO;
import me.sso.ti.jpa.DefaultGenericDAO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 上午12:37:06
 */
@Repository(value = "styleDAO")
public class DefaultStyleDAO extends DefaultGenericDAO<StyleDO, Long> implements StyleDAO {
	
}