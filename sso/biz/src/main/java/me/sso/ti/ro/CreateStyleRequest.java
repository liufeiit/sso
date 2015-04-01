package me.sso.ti.ro;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年4月1日 下午10:37:05
 */
public class CreateStyleRequest {
	
	private String name;// 风格名称

	private String description;// 风格描述

	private MultipartFile icon;// 风格图标

	private MultipartFile background;// 风格背景图片

	private MultipartFile floater;// 风格漂浮物

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getIcon() {
		return icon;
	}

	public void setIcon(MultipartFile icon) {
		this.icon = icon;
	}

	public MultipartFile getBackground() {
		return background;
	}

	public void setBackground(MultipartFile background) {
		this.background = background;
	}

	public MultipartFile getFloater() {
		return floater;
	}

	public void setFloater(MultipartFile floater) {
		this.floater = floater;
	}
}