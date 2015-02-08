package me.sso.ti.srv;

import me.sso.ti.result.Result;
import me.sso.ti.ro.RegisterRequest;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月27日 下午4:29:28
 */
public interface AccountService {

	Result login(String name, String password);

	Result register(RegisterRequest request);

}