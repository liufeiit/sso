package me.sso.ti.srv;

import me.sso.ti.result.Result;
import me.sso.ti.ro.FavoriteRequest;
import me.sso.ti.ro.PageRequest;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 下午10:25:20
 */
public interface FavoriteService {

	Result doFavorite(FavoriteRequest request);
	
	Result doCancel(FavoriteRequest request);
	
	Result favList(PageRequest request);
}