package me.sso.ti.vo;

import me.sso.ti.dataobject.StyleDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 下午10:01:22
 */
public class StyleVO {

	private Long id;

	private String name;// 风格名称

	private String description;// 风格描述

	private Long icon;// 风格图标

	private Long background;// 风格背景图片

	private Long floater;// 风格漂浮物

	public static StyleVO newInstance(StyleDO _do) {
		if(_do == null) {
			return null;
		}
		return new StyleVO().toStyle(_do);
	}
	
	private final StyleVO toStyle(StyleDO _do) {
		setBackground(_do.getBackground());
		setDescription(_do.getDescription());
		setFloater(_do.getFloater());
		setIcon(_do.getIcon());
		setId(_do.getId());
		setName(_do.getName());
		return this;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getIcon() {
		return icon;
	}

	public void setIcon(Long icon) {
		this.icon = icon;
	}

	public Long getBackground() {
		return background;
	}

	public void setBackground(Long background) {
		this.background = background;
	}

	public Long getFloater() {
		return floater;
	}

	public void setFloater(Long floater) {
		this.floater = floater;
	}
}