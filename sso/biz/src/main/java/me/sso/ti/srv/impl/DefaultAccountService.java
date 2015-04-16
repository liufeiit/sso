package me.sso.ti.srv.impl;

import java.util.Date;

import me.sso.ti.auth.response.LoginResponse;
import me.sso.ti.dataobject.UserDO;
import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.ro.UserRequest;
import me.sso.ti.srv.AccountService;
import me.sso.ti.srv.BaseService;
import me.sso.ti.vo.UserVO;

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
	public Result login(UserRequest request) {
		UserDO user = userDAO.queryNativeForObject("SELECT * FROM user WHERE device_id = ?", new Object[] { request.getDeviceId() });
		if (user == null) {
			return Result.newError().with(ResultCode.Error_Login);
		}
		Date now = new Date();
		user.setGmt_modified(now);
		user.setLast_ip(getIp());
		user.setLast_login(now);
		userDAO.merge(user);
		Result login = login(user.getId());
		if (!login.isSuccess()) {
			return login;
		}
		LoginResponse loginResponse = login.getResponse(LoginResponse.class);
		UserVO vo = new UserVO();
		vo.setOpen_id(loginResponse.getOpen_id());
		vo.setAccess_token(loginResponse.getAccess_token());
		return Result.newSuccess().with(ResultCode.Success).with("user", vo);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public Result register(UserRequest request) {
		java.math.BigInteger c = (java.math.BigInteger) userDAO.createNativeQuery("SELECT COUNT(id) FROM user WHERE device_id = ?", new Object[] { request.getDeviceId() }).getSingleResult();
		if (c != null && c.longValue() > 0L) {
			return Result.newError().with(ResultCode.Error_Register_User_Exist);
		}
		UserDO user = new UserDO();
		user.setLast_ip(getIp());
		Date now = new Date();
		user.setGmt_created(now);
		user.setGmt_modified(now);
		user.setLast_login(now);
		user.setDeviceId(request.getDeviceId());
		try {
			userDAO.persist(user);
			Result login = login(user.getId());
			if (!login.isSuccess()) {
				return login;
			}
			LoginResponse loginResponse = login.getResponse(LoginResponse.class);
			UserVO vo = new UserVO();
			vo.setOpen_id(loginResponse.getOpen_id());
			vo.setAccess_token(loginResponse.getAccess_token());
			return Result.newSuccess().with(ResultCode.Success).with("user", vo);
		} catch (Exception e) {
			log.error("Account Register Error.", e);
		}
		return Result.newError().with(ResultCode.Error_Register);
	}
}