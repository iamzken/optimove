package com.topcheer.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.MenuMapper;
import com.topcheer.model.Menu;
import com.topcheer.model.MenuTree;
import com.topcheer.service.IMenuService;
@Service("menuService")
public class MenuServiceImpl implements IMenuService {
	private static final Logger logger = Logger.getLogger(MenuServiceImpl.class);
	@Autowired
	private MenuMapper menuMapper;

	public void delete(String menuCode) {
		menuMapper.delete(menuCode);
	}

	public int insert(Menu menu) {
		menuMapper.insert(menu);
		return 0;
	}

	public List<Menu> searchAll() {
		return menuMapper.searchAll();
	}

	public List<Menu> searchMenu(Map map) {
		return menuMapper.searchMenu(map);
	}

	public void update(Menu menu) {
		menuMapper.update(menu);
	}
	
	public List<MenuTree> searchTree(String grpId) {
		List<Menu> list = menuMapper.searchMenuByGrpId(grpId);
		return createTree(list);
	}
	
	public List<MenuTree> searchTree() {
		List<Menu> list = searchAll();
		return createTree(list);
	}


	public List<MenuTree> createTree(List<Menu> list) {
//		List<Menu> list = searchAll();
//		List<Menu> list = menuMapper.searchMenuByGrpId(grpId);
		List<MenuTree> resultList = new ArrayList<MenuTree>();
		if(list==null||list.size()==0){
			return resultList;
		}
		for(int i=0;i<list.size();i++){
			Menu menu = list.get(i);
			Boolean bl = true;
			if(menu.getMenuParent()==null){
				resultList.add(convertMenu(menu));
			}else{
				for(int j=0;j<list.size();j++){
					Menu temp = list.get(j);
					if(temp!=menu&&menu.getMenuParent().trim().equals(temp.getMenuCode().trim())){
						bl=false;
						break;
					}
				}
				if(bl)
					resultList.add(convertMenu(menu));
			}
		}
		for(MenuTree menu : resultList){
			recursionMenu(menu,list);
		}
		return resultList;
	}
	
	private MenuTree convertMenu(Menu menu){
		return new MenuTree(menu.getMenuCode().trim(),menu.getMenuName().trim(),menu.getMenuUrl(),menu.getIcon());
	}
	
	private void recursionMenu(MenuTree menu,List<Menu> list){
		for(int i=0;i<list.size();i++){
			Menu temp = list.get(i);
			if(temp.getMenuParent()!=null&&menu.getMenuid().trim().equals(temp.getMenuParent().trim())){
				MenuTree nextMenu = convertMenu(temp);
				menu.getMenus().add(nextMenu);
				recursionMenu(nextMenu, list);
				
			}
		}
	}

}
