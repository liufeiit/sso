package me.sso.ti.srv;

import me.sso.ti.result.Result;
import me.sso.ti.ro.CreateStyleRequest;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 下午10:05:58
 */
public interface StyleService {
	
	Result createStyle(CreateStyleRequest request);

	Result getStyleList();
	
	Result getStyle(Long id);
}