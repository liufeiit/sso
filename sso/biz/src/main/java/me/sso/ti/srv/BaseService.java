package me.sso.ti.srv;

import javax.servlet.http.HttpServletRequest;

import me.sso.ti.dao.ArticleDAO;
import me.sso.ti.dao.CategoryDAO;
import me.sso.ti.dao.FavoriteDAO;
import me.sso.ti.dao.StyleDAO;
import me.sso.ti.dao.UserDAO;
import me.sso.ti.utils.WebUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月27日 下午4:21:29
 */
public class BaseService implements InitializingBean {

	protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected HttpServletRequest request;

	@Autowired
	@Qualifier(value = "userDAO")
	protected UserDAO userDAO;

	@Autowired
	@Qualifier(value = "articleDAO")
	protected ArticleDAO articleDAO;

	@Autowired
	@Qualifier(value = "categoryDAO")
	protected CategoryDAO categoryDAO;

	@Autowired
	@Qualifier(value = "favoriteDAO")
	protected FavoriteDAO favoriteDAO;

	@Autowired
	@Qualifier(value = "styleDAO")
	protected StyleDAO styleDAO;
	
	protected final long getIp() {
        return WebUtils.ipToLng(WebUtils.getIpAddr(request));
    }
	
	@Override
	public final void afterPropertiesSet() throws Exception {
		init();
	}
	
	protected void init() throws Exception {
		
	}
}