package com.topcheer.dao;

import java.util.List;
import java.util.Map;
import com.topcheer.model.Menu;


public interface MenuMapper {
	public List<Menu> searchMenu(Map map);
	
	public List<Menu> searchAll();
	
	public int insert(Menu menu);
	
	public void update(Menu menu);
	
	public void delete(String menuCode);
	
	public int searchCount();
	
	public List<Menu> searchMenuByGrpId(String grpId);
}
