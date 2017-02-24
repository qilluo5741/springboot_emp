package com.sharebo.emp.web.entity;

import java.io.Serializable;

/**
 * 菜单实体
 * @author zhuhaiyuan
 *
 */
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String url;
	private String menuname;
	private String icon;
	private String target="menuFrame";
	private Integer authority;//0，普通员工；1，管理员
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getAuthority() {
		return authority;
	}
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}
	public Menu(String id, String url, String menuname, String icon, Integer authority) {
		super();
		this.id = id;
		this.url = url;
		this.menuname = menuname;
		this.icon = icon;
		this.authority = authority;
	}
	public Menu() {
		super();
	}
}
