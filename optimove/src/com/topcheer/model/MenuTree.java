package com.topcheer.model;

import java.util.ArrayList;
import java.util.List;

public class MenuTree {
	//菜单项编号
	private    String   menuid ;
	//父菜单编号
	private    String   icon ;
	//菜单名称
	private    String   menuname ;
	//所指URL
	private    String   url ;
	//所属模块
	private    List<MenuTree>   menus = new ArrayList<MenuTree>();
	
	
	
	public MenuTree() {
		super();
	}
	
	
	
	public MenuTree(String menuid, String menuname, String url) {
		super();
		this.menuid = menuid;
		this.menuname = menuname;
		this.url = url;
	}
	
	



	public MenuTree(String menuid, String menuname, String url, String icon) {
		super();
		this.menuid = menuid;
		this.icon = icon;
		this.menuname = menuname;
		this.url = url;
	}



	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<MenuTree> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuTree> menus) {
		this.menus = menus;
	}
	
	
	
	
}
