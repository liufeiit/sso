package me.sso.ti.dao.impl;

import org.springframework.stereotype.Repository;

import me.sso.ti.dao.FavoriteDAO;
import me.sso.ti.dataobject.FavoriteDO;
import me.sso.ti.jpa.DefaultGenericDAO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 上午12:35:25
 */
@Repository("favoriteDAO")
public class DefaultFavoriteDAO extends DefaultGenericDAO<FavoriteDO, Long> implements FavoriteDAO {
	
}
