package me.sso.ti.srv;

import me.sso.ti.result.Result;
import me.sso.ti.ro.PushTokenRequest;
import me.sso.ti.ro.UserRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月27日 下午4:29:28
 */
public interface AccountService {

	Result login(UserRequest request);

	Result register(UserRequest request);

	Result token(PushTokenRequest request);
}