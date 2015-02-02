package me.sso.ti.dao.impl;

import me.sso.ti.dao.UserDAO;
import me.sso.ti.dataobject.UserDO;
import me.sso.ti.jpa.DefaultGenericDAO;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月19日 上午12:32:18
 */
@Repository(value = "userDAO")
public class DefaultUserDAO extends DefaultGenericDAO<UserDO, Long> implements UserDAO {

}