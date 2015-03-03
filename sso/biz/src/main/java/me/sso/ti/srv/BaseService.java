package me.sso.ti.srv;

import javax.servlet.http.HttpServletRequest;

import me.sso.ti.auth.AuthService;
import me.sso.ti.auth.request.CheckRequest;
import me.sso.ti.auth.request.LoginRequest;
import me.sso.ti.auth.response.CheckResponse;
import me.sso.ti.auth.response.LoginResponse;
import me.sso.ti.dao.ArticleDAO;
import me.sso.ti.dao.CategoryDAO;
import me.sso.ti.dao.FavoriteDAO;
import me.sso.ti.dao.GzipDAO;
import me.sso.ti.dao.ImageDAO;
import me.sso.ti.dao.StyleDAO;
import me.sso.ti.dao.UserDAO;
import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.ro.PrivilegedRequest;
import me.sso.ti.utils.WebUtils;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
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

	@Autowired
	@Qualifier(value = "imageDAO")
	protected ImageDAO imageDAO;

	@Autowired
	@Qualifier(value = "gzipDAO")
	protected GzipDAO gzipDAO;
	
	protected Result doPrivileged(PrivilegedRequest request) {
		CheckRequest checkRequest = new CheckRequest();
		checkRequest.setApp_id(AuthService.App_Id);
		checkRequest.setOpen_id(request.getOpen_id());
		checkRequest.setAccess_token(request.getAccess_token());
		CheckResponse checkResponse = AuthService.check(checkRequest);
		if (StringUtils.isBlank(checkResponse.getSecret_id())) {
			return Result.newError().with(ResultCode.Error_Token);
		}
		Long user_id = NumberUtils.toLong(checkResponse.getSecret_id(), 0L);
		if (user_id <= 0L) {
			return Result.newError().with(ResultCode.Error_Token);
		}
		return Result.newSuccess().with(ResultCode.Success).response(user_id);
	}
	
	protected Result login(Long userId) {
		if(userId == null || userId <= 0L) {
			return Result.newError().with(ResultCode.Error_Login);
		}
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setApp_id(AuthService.App_Id);
		loginRequest.setSecret_id(String.valueOf(userId));
		LoginResponse loginResponse = AuthService.login(loginRequest);
		if (loginResponse == null || StringUtils.isEmpty(loginResponse.getAccess_token())) {
			return Result.newError().with(ResultCode.Error_Login);
		}
		return Result.newSuccess().with(ResultCode.Success).response(loginResponse);
	}
	
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