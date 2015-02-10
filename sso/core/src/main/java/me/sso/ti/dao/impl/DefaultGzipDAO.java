package me.sso.ti.dao.impl;

import org.springframework.stereotype.Repository;

import me.sso.ti.dao.GzipDAO;
import me.sso.ti.dataobject.GzipDO;
import me.sso.ti.jpa.DefaultGenericDAO;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月10日 上午10:52:00
 */
@Repository(value = "gzipDAO")
public class DefaultGzipDAO extends DefaultGenericDAO<GzipDO, Long> implements GzipDAO {
	
}
