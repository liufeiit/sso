package me.sso.ti.dao.impl;

import me.sso.ti.dao.EntityManagerSupportGenericDAO;
import me.sso.ti.dao.ImageDAO;
import me.sso.ti.dataobject.ImageDO;

import org.springframework.stereotype.Repository;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年2月10日 上午12:46:56
 */
@Repository("imageDAO")
public class DefaultImageDAO extends EntityManagerSupportGenericDAO<ImageDO, Long> implements ImageDAO {
	
}