package me.sso.ti.srv.impl;

import java.util.Date;

import me.ocs.oauth.token.response.LoginResponse;
import me.ocs.oss.mss.message.IosMessage;
import me.sso.ti.comms.SequenceType;
import me.sso.ti.dataobject.UserDO;
import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.ro.PushTokenRequest;
import me.sso.ti.ro.UserRequest;
import me.sso.ti.srv.AccountService;
import me.sso.ti.srv.BaseService;
import me.sso.ti.vo.UserVO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 账户相关的业务。
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 上午12:38:47
 */
@Service("accountService")
public class DefaultAccountService extends BaseService implements AccountService {

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public Result token(PushTokenRequest request) {
		Result privileged = doPrivileged(request);
		if(!privileged.isSuccess()) {
			return privileged;
		}
		Long userId = privileged.getResponse(Long.class);
		UserDO user = userDAO.get(userId);
		user.setToken(request.getToken());
		Date now = new Date();
		user.setGmt_modified(now);
		user.setLast_ip(getIp());
		user.setLast_login(now);
		userDAO.merge(user);
		if(StringUtils.isNotBlank(user.getToken())) {
			IosMessage iosMessage = new IosMessage();
			iosMessage.setTitle("系统消息");
			iosMessage.setBody("欢迎来到鞋子物语!开启你的浪漫之旅!");
			iosMessage.setTarget(user.getToken());
			send(iosMessage);
		}
		return Result.newSuccess().with(ResultCode.Success);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public Result login(UserRequest request) {
		UserDO user = null;
		try {
			user = userDAO.queryNativeForObject("SELECT * FROM user WHERE device_id = ?", new Object[] { request.getDeviceId() });
		} catch (Exception e) {
			log.error("Query User Error.", e);
		}
		if (user == null) {
			return Result.newError().with(ResultCode.Error_Login);
		}
		try {
			Date now = new Date();
			user.setGmt_modified(now);
			user.setLast_ip(getIp());
			user.setLast_login(now);
			userDAO.merge(user);
			Result login = doLogin(user.getId());
			if (!login.isSuccess()) {
				return login;
			}
			LoginResponse loginResponse = login.getResponse(LoginResponse.class);
			UserVO vo = new UserVO();
			vo.setGuid(user.getGuid());
			vo.setOpen_id(loginResponse.getOpen_id());
			vo.setAccess_token(loginResponse.getAccess_token());
			if(StringUtils.isNotBlank(user.getToken())) {
				IosMessage iosMessage = new IosMessage();
				iosMessage.setTitle("系统消息");
				iosMessage.setBody("欢迎来到鞋子物语!开启你的浪漫之旅!");
				iosMessage.setTarget(user.getToken());
				send(iosMessage);
			}
			return Result.newSuccess().with(ResultCode.Success).with("user", vo);
		} catch (Exception e) {
			log.error("User Login Error.", e);
		}
		return Result.newError().with(ResultCode.Error_Login);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public Result register(UserRequest request) {
		java.math.BigInteger c = (java.math.BigInteger) userDAO.createNativeQuery("SELECT COUNT(id) FROM user WHERE device_id = ?", new Object[] { request.getDeviceId() }).getSingleResult();
		if (c != null && c.longValue() > 0L) {
			return Result.newError().with(ResultCode.Error_Register_User_Exist);
		}
		UserDO user = new UserDO();
		user.setGuid(sequenceService.nextValueAsStringWithCreate(SequenceType.USER_GUID_SEQUENCE, 25, 1L));
		user.setLast_ip(getIp());
		Date now = new Date();
		user.setGmt_created(now);
		user.setGmt_modified(now);
		user.setLast_login(now);
		user.setDeviceId(request.getDeviceId());
		user.setToken(request.getToken());
		try {
			userDAO.persist(user);
			Result login = doLogin(user.getId());
			if (!login.isSuccess()) {
				return login;
			}
			LoginResponse loginResponse = login.getResponse(LoginResponse.class);
			UserVO vo = new UserVO();
			vo.setGuid(user.getGuid());
			vo.setOpen_id(loginResponse.getOpen_id());
			vo.setAccess_token(loginResponse.getAccess_token());
			if(StringUtils.isNotBlank(user.getToken())) {
				IosMessage iosMessage = new IosMessage();
				iosMessage.setTitle("系统消息");
				iosMessage.setBody("欢迎来到鞋子物语!开启你的浪漫之旅!");
				iosMessage.setTarget(user.getToken());
				send(iosMessage);
			}
			return Result.newSuccess().with(ResultCode.Success).with("user", vo);
		} catch (Exception e) {
			log.error("Account Register Error.", e);
		}
		return Result.newError().with(ResultCode.Error_Register);
	}
}