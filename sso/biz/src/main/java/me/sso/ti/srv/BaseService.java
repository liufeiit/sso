package me.sso.ti.srv;

import javax.servlet.http.HttpServletRequest;

import me.ocs.commons.redis.RedisTemplate;
import me.ocs.commons.sequence.SequenceService;
import me.ocs.oauth.security.SecurityService;
import me.ocs.oauth.token.AuthenticationProvider;
import me.ocs.oauth.token.request.LoginRequest;
import me.ocs.oauth.token.request.PrivilegedRequest;
import me.ocs.oauth.token.response.LoginResponse;
import me.ocs.oauth.token.response.PrivilegedResponse;
import me.ocs.oss.mss.Message;
import me.ocs.oss.mss.MessageException;
import me.ocs.oss.mss.MessageNotification;
import me.ocs.oss.mss.MessageService;
import me.sso.ti.dao.ArticleDAO;
import me.sso.ti.dao.CategoryDAO;
import me.sso.ti.dao.FavoriteDAO;
import me.sso.ti.dao.GzipDAO;
import me.sso.ti.dao.ImageDAO;
import me.sso.ti.dao.StyleDAO;
import me.sso.ti.dao.UserDAO;
import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
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
	
	public static final String APP_ID = "w1wLqzQE6fDeFlgh1O5urrj9ilbm";

	protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected HttpServletRequest request;

	@Autowired
	@Qualifier("userDAO")
	protected UserDAO userDAO;

	@Autowired
	@Qualifier("articleDAO")
	protected ArticleDAO articleDAO;

	@Autowired
	@Qualifier("categoryDAO")
	protected CategoryDAO categoryDAO;

	@Autowired
	@Qualifier("favoriteDAO")
	protected FavoriteDAO favoriteDAO;

	@Autowired
	@Qualifier("styleDAO")
	protected StyleDAO styleDAO;

	@Autowired
	@Qualifier("imageDAO")
	protected ImageDAO imageDAO;

	@Autowired
	@Qualifier("gzipDAO")
	protected GzipDAO gzipDAO;

	@Autowired
	@Qualifier("redisTemplate")
	protected RedisTemplate redisTemplate;

	@Autowired
	@Qualifier("securityService")
	protected SecurityService securityService;

	@Autowired
	@Qualifier("redisAuthenticationProvider")
	protected AuthenticationProvider authenticationProvider;

	@Autowired
	@Qualifier("sequenceService")
	protected SequenceService sequenceService;

	@Autowired
	@Qualifier("messageService")
	protected MessageService messageService;
	
	protected MessageNotification send(Message message) {
		try {
			return messageService.send(message);
		} catch (MessageException e) {
			log.error("Send Message Error.", e);
		}
		return null;
	}
	
	protected boolean isFavorite(Long userId, Long articleId) {
		try {
			Object[] args = new Object[] { userId, articleId };
			String sql = "SELECT COUNT(id) FROM favorite WHERE user_id = ? AND article_id = ?";
			java.math.BigInteger c = (java.math.BigInteger) favoriteDAO.createNativeQuery(sql, args).getSingleResult();
			if(c != null && c.longValue() > 0L) {
				return true;
			}
		} catch (Exception e) {
			log.error("Check isFavorite Error.", e);
		}
		return false;
	}
	
	protected Result doPrivileged(me.sso.ti.ro.PrivilegedRequest request) {
		PrivilegedRequest checkRequest = new PrivilegedRequest();
		checkRequest.setApp_id(APP_ID);
		checkRequest.setOpen_id(request.getOpen_id());
		checkRequest.setAccess_token(request.getAccess_token());
		PrivilegedResponse checkResponse = authenticationProvider.doPrivileged(checkRequest);
		if (StringUtils.isBlank(checkResponse.getSecret_id())) {
			return Result.newError().with(ResultCode.Error_Token);
		}
		Long user_id = NumberUtils.toLong(checkResponse.getSecret_id(), 0L);
		if (user_id <= 0L) {
			return Result.newError().with(ResultCode.Error_Token);
		}
		return Result.newSuccess().with(ResultCode.Success).response(user_id);
	}
	
	protected Result doLogin(Long userId) {
		if(userId == null || userId <= 0L) {
			return Result.newError().with(ResultCode.Error_Login);
		}
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setApp_id(APP_ID);
		loginRequest.setSecret_id(String.valueOf(userId));
		LoginResponse loginResponse = authenticationProvider.doLogin(loginRequest);
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