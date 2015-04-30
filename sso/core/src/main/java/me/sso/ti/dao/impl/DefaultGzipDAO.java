package me.sso.ti.dao.impl;

import me.sso.ti.dao.EntityManagerSupportGenericDAO;
import me.sso.ti.dao.GzipDAO;
import me.sso.ti.dataobject.GzipDO;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月10日 上午10:52:00
 */
@Repository("gzipDAO")
public class DefaultGzipDAO extends EntityManagerSupportGenericDAO<GzipDO, Long> implements GzipDAO {
	
}
