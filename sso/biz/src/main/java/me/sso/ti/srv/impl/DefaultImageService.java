package me.sso.ti.srv.impl;

import org.apache.commons.lang3.StringUtils;

import me.sso.ti.srv.BaseService;
import me.sso.ti.srv.ImageService;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 下午11:20:41
 */
public class DefaultImageService extends BaseService implements ImageService {
	
	@Override
	public String getImagePath(String imageName) {
		if(StringUtils.isEmpty(imageName)) {
			return DefaultImagePath;
		}
		for (ImageRepositoryType type : ImageRepositoryType.values()) {
			if(!type.open) {
				continue;
			}
			if(!StringUtils.startsWith(imageName, type.prefix)) {
				continue;
			}
			return type.repository + StringUtils.substring(imageName, StringUtils.length(type.prefix), StringUtils.length(imageName));
		}
		return DefaultImagePath;
	}
}
