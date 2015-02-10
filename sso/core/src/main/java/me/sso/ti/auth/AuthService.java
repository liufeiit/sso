package me.sso.ti.auth;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;

import me.sso.ti.auth.request.CheckRequest;
import me.sso.ti.auth.request.LoginRequest;
import me.sso.ti.auth.response.CheckResponse;
import me.sso.ti.auth.response.LoginResponse;
import me.sso.ti.utils.XmlUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月5日 下午4:01:36
 */
public class AuthService {

	public static final String App_Id = "w1wLqzQE6fDeFlgh1O5urrj9ilbm";

	private static final Log log = LogFactory.getLog(AuthService.class);

	private static Class<?> AUTH_SERVICE_ADAPTER_CLASS;

	private static Object AUTH_SERVICE_ADAPTER;

	private static Method LOGIN_METHOD;

	private static Method CHECK_METHOD;

	static {
		if (AUTH_SERVICE_ADAPTER_CLASS == null) {
			AUTH_SERVICE_ADAPTER_CLASS = AccessController.doPrivileged(new PrivilegedAction<Class<?>>() {
				public Class<?> run() {
					try {
						return Class.forName("me.jetty.ti.auth.RedisAuthorizationServiceAdapter");
					} catch (Exception e) {
						log.error("Can't Found Auth Adapter Class.", e);
					}
					return null;
				}
			});
		}
		if (AUTH_SERVICE_ADAPTER == null) {
			AUTH_SERVICE_ADAPTER = AccessController.doPrivileged(new PrivilegedAction<Object>() {
				@Override
				public Object run() {
					try {
						return AUTH_SERVICE_ADAPTER_CLASS.newInstance();
					} catch (Exception e) {
						log.error("Can't Access Auth Adapter Class.", e);
					}
					return null;
				}
			});
		}
		if (LOGIN_METHOD == null) {
			LOGIN_METHOD = AccessController.doPrivileged(new PrivilegedAction<Method>() {
				@Override
				public Method run() {
					try {
						Method login = AUTH_SERVICE_ADAPTER_CLASS.getDeclaredMethod("login", new Class[] { String.class });
						login.setAccessible(true);
						return login;
					} catch (Exception e) {
						log.error("Can't Found Auth login Method.", e);
					}
					return null;
				}
			});
		}
		if (CHECK_METHOD == null) {
			CHECK_METHOD = AccessController.doPrivileged(new PrivilegedAction<Method>() {
				@Override
				public Method run() {
					try {
						Method check = AUTH_SERVICE_ADAPTER_CLASS.getDeclaredMethod("check", new Class[] { String.class });
						check.setAccessible(true);
						return check;
					} catch (Exception e) {
						log.error("Can't Found Auth check Method.", e);
					}
					return null;
				}
			});
		}
	}

	public static LoginResponse login(LoginRequest request) {
		try {
			String reqXml = XmlUtils.toXML(request, Alias.Login_Request);
			String responseXML = (String) LOGIN_METHOD.invoke(AUTH_SERVICE_ADAPTER, new Object[] { reqXml });
			if (StringUtils.isEmpty(responseXML)) {
				return LoginResponse.DEFAULT_RESPONSE;
			}
			return XmlUtils.toObj(LoginResponse.class, responseXML, Alias.Login_Response);
		} catch (Exception e) {
			log.error("Can't invoke Auth Login Method.", e);
		}
		return LoginResponse.DEFAULT_RESPONSE;
	}

	public static CheckResponse check(CheckRequest request) {
		try {
			String reqXml = XmlUtils.toXML(request, Alias.Check_Request);
			String responseXML = (String) CHECK_METHOD.invoke(AUTH_SERVICE_ADAPTER, new Object[] { reqXml });
			if (StringUtils.isEmpty(responseXML)) {
				return CheckResponse.DEFAULT_RESPONSE;
			}
			return XmlUtils.toObj(CheckResponse.class, responseXML, Alias.Check_Response);
		} catch (Exception e) {
			log.error("Can't invoke Auth Check Method.", e);
		}
		return CheckResponse.DEFAULT_RESPONSE;
	}
}