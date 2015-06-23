package com.topcheer.service;

import java.util.List;
import java.util.Map;

import com.topcheer.model.Menu;
import com.topcheer.model.MenuTree;

public interface IMenuService {
	
	
	public List<Menu> searchAll();
	
	public List<MenuTree> searchTree(String grpId);
	
	public List<MenuTree> searchTree();
	
	public int insert(Menu menu);
	
	public void update(Menu menu);
	
	public void delete(String menuCode);
	
	public List<Menu> searchMenu(Map map);

}
