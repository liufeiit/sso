package me.sso.ti.srv.impl;

import java.util.Date;

import me.sso.ti.auth.AuthService;
import me.sso.ti.auth.request.LoginRequest;
import me.sso.ti.auth.response.LoginResponse;
import me.sso.ti.dataobject.UserDO;
import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.ro.RegisterRequest;
import me.sso.ti.srv.AccountService;
import me.sso.ti.srv.BaseService;
import me.sso.ti.utils.CryptoUtils;
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
@Service(value = "accountService")
public class DefaultAccountService extends BaseService implements AccountService {
	
	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public Result login(String name, String password) {
		if (StringUtils.isBlank(name)) {
			return Result.newError().with(ResultCode.Error_Permission);
		}
		if (StringUtils.isBlank(password)) {
			return Result.newError().with(ResultCode.Error_Permission);
		}
		UserDO user = userDAO.queryNamedForObject("User.getUserByName", new Object[] { name });
		if (user == null) {
			return Result.newError().with(ResultCode.Error_Permission);
		}
		if (!StringUtils.equals(CryptoUtils.encrypt(password), user.getPassword())) {
			return Result.newError().with(ResultCode.Error_Permission);
		}
		Date now = new Date();
		user.setGmt_modified(now);
		user.setLast_ip(getIp());
		user.setLast_login(now);
		userDAO.merge(user);
		// 安全登录
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setApp_id(AuthService.App_Id);
		loginRequest.setSecret_id(String.valueOf(user.getId()));
		LoginResponse loginResponse = AuthService.login(loginRequest);
		UserVO vo = UserVO.newInstance(user);
		vo.setApp_id(loginResponse.getApp_id());
		vo.setOpen_id(loginResponse.getOpen_id());
		vo.setAccess_token(loginResponse.getAccess_token());
		vo.setExpires_in(loginResponse.getExpires_in());
		if (StringUtils.isEmpty(vo.getAccess_token())) {
			return Result.newError().with(ResultCode.Error_Login);
		}
		return Result.newSuccess().with(ResultCode.Success).with("user", vo);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public Result register(RegisterRequest request) {
		String name = request.getName();
		String password = request.getPassword();
		String mobile = request.getMobile();
		String avatar = request.getAvatar();
		if (StringUtils.isBlank(name)) {
			return Result.newError().with(ResultCode.Error_Register_Name);
		}
		if (StringUtils.isBlank(password)) {
			return Result.newError().with(ResultCode.Error_Register_Passwd);
		}
		Long uc = userDAO.createNamedQuery("User.existUserByName", Long.class, new Object[] { name }).getSingleResult();
		if (uc != null && uc > 0L) {
			return Result.newError().with(ResultCode.Error_Register_User_Exist);
		}
		UserDO user = new UserDO();
		user.setAvatar(avatar);
		user.setName(name);
		user.setMobile(mobile);
		user.setPassword(CryptoUtils.encrypt(password));
		user.setStatus(UserDO.Available);
		user.setLast_ip(getIp());
		Date now = new Date();
		user.setGmt_created(now);
		user.setGmt_modified(now);
		user.setLast_login(now);
		try {
			userDAO.persist(user);
			// 安全登录
			LoginRequest loginRequest = new LoginRequest();
			loginRequest.setApp_id(AuthService.App_Id);
			loginRequest.setSecret_id(String.valueOf(user.getId()));
			LoginResponse loginResponse = AuthService.login(loginRequest);
			UserVO vo = UserVO.newInstance(user);
			vo.setApp_id(loginResponse.getApp_id());
			vo.setOpen_id(loginResponse.getOpen_id());
			vo.setAccess_token(loginResponse.getAccess_token());
			vo.setExpires_in(loginResponse.getExpires_in());

			return Result.newSuccess().with(ResultCode.Success).with("user", vo);
		} catch (Exception e) {
			log.error("Account Register Error.", e);
		}
		return Result.newError().with(ResultCode.Error_Register);
	}
}