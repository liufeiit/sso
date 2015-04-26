package me.sso.ti.auth;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;

import me.sso.ti.auth.request.PrivilegedRequest;
import me.sso.ti.auth.request.LoginRequest;
import me.sso.ti.auth.response.PrivilegedResponse;
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

	private static Method Login_METHOD;

	private static Method Privileged_METHOD;

	static {
		if (AUTH_SERVICE_ADAPTER_CLASS == null) {
			AUTH_SERVICE_ADAPTER_CLASS = AccessController.doPrivileged(new PrivilegedAction<Class<?>>() {
				public Class<?> run() {
					try {
						return Class.forName("me.jetty.ti.auth.XmlRedisAuthenticationProvider");
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
		if (Login_METHOD == null) {
			Login_METHOD = AccessController.doPrivileged(new PrivilegedAction<Method>() {
				@Override
				public Method run() {
					try {
						Method login = AUTH_SERVICE_ADAPTER_CLASS.getDeclaredMethod("doLogin", new Class[] { String.class });
						login.setAccessible(true);
						return login;
					} catch (Exception e) {
						log.error("Can't Found Auth doLogin Method.", e);
					}
					return null;
				}
			});
		}
		if (Privileged_METHOD == null) {
			Privileged_METHOD = AccessController.doPrivileged(new PrivilegedAction<Method>() {
				@Override
				public Method run() {
					try {
						Method check = AUTH_SERVICE_ADAPTER_CLASS.getDeclaredMethod("doPrivileged", new Class[] { String.class });
						check.setAccessible(true);
						return check;
					} catch (Exception e) {
						log.error("Can't Found Auth doPrivileged Method.", e);
					}
					return null;
				}
			});
		}
	}

	public static LoginResponse doLogin(LoginRequest request) {
		try {
			String reqXml = XmlUtils.toXML(request, Alias.Login_Request);
			String responseXML = (String) Login_METHOD.invoke(AUTH_SERVICE_ADAPTER, new Object[] { reqXml });
			if (StringUtils.isEmpty(responseXML)) {
				return LoginResponse.DEFAULT_RESPONSE;
			}
			return XmlUtils.toObj(LoginResponse.class, responseXML, Alias.Login_Response);
		} catch (Exception e) {
			log.error("Can't invoke Auth doLogin Method.", e);
		}
		return LoginResponse.DEFAULT_RESPONSE;
	}

	public static PrivilegedResponse doPrivileged(PrivilegedRequest request) {
		try {
			String reqXml = XmlUtils.toXML(request, Alias.Privileged_Request);
			String responseXML = (String) Privileged_METHOD.invoke(AUTH_SERVICE_ADAPTER, new Object[] { reqXml });
			if (StringUtils.isEmpty(responseXML)) {
				return PrivilegedResponse.DEFAULT_RESPONSE;
			}
			return XmlUtils.toObj(PrivilegedResponse.class, responseXML, Alias.Privileged_Response);
		} catch (Exception e) {
			log.error("Can't invoke Auth doPrivileged Method.", e);
		}
		return PrivilegedResponse.DEFAULT_RESPONSE;
	}
}