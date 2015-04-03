package me.sso.ti.vo;

import java.util.Date;

import me.sso.ti.dataobject.StyleDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 下午10:01:22
 */
public class StyleVO extends BaseObject {

	private Long id;

	private String name;// 风格名称

	private String description;// 风格描述

	private Long icon;// 风格图标

	private Long background;// 风格背景图片

	private Long floater;// 风格漂浮物
	
	private Byte status;// 状态，1:可用，0:不可用

	private Date gmt_created;

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
		setStatus(_do.getStatus());
		setGmt_created(_do.getGmt_created());
		return this;
	}
	
	public String getCreated() {
		return formatDate(gmt_created);
	}
	
	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getGmt_created() {
		return gmt_created;
	}

	public void setGmt_created(Date gmt_created) {
		this.gmt_created = gmt_created;
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