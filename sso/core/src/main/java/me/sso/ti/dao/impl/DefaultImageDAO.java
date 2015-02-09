package me.sso.ti.dao.impl;

import org.springframework.stereotype.Repository;

import me.sso.ti.dao.ImageDAO;
import me.sso.ti.dataobject.ImageDO;
import me.sso.ti.jpa.DefaultGenericDAO;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月10日 上午12:46:56
 */
@Repository(value = "imageDAO")
public class DefaultImageDAO extends DefaultGenericDAO<ImageDO, Long> implements ImageDAO {
	
}