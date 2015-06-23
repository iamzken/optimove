package com.topcheer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.GroupRight;
import com.topcheer.model.Menu;
import com.topcheer.model.MenuTree;
import com.topcheer.model.PermissionTree;
import com.topcheer.service.IGroupRightService;
import com.topcheer.service.IMenuService;
import com.topcheer.utils.JsonBinder;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/menu")
public class MenuController {
	private static final Logger logger = Logger.getLogger(MenuController.class);
	@Autowired
	private IMenuService menuService;
	
	@Autowired
	private IGroupRightService groupRightService;

	@RequestMapping("/searchMenu")
	@ResponseBody
	public Map searchMenu(HttpServletRequest request) {
		logger.info("invoke searchMenu...");
		Page page = Page.newBuilder(request);
		menuService.searchMenu(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	/**
	 * 登陆获取菜单项
	 * @param grpId
	 * @return
	 */
	@RequestMapping("/menuTree")
	@ResponseBody
	public List<MenuTree> getMenuTree(String grpId) {
		logger.info("invoke getMenuTree...");
		List menuTreeList = menuService.searchTree(grpId);
		List<GroupRight> groupRightList = groupRightService.getGroupRight(grpId);
		return menuTreeList;
	}
	
	@RequestMapping("/permissionTree")
	@ResponseBody
	public List<PermissionTree> getPermissionTree(String grpId) {
		logger.info("invoke getPermissionTree...");
		List<MenuTree> menuTreeList = menuService.searchTree();
		List<PermissionTree> result = new ArrayList<PermissionTree>();
		List<GroupRight> list = groupRightService.getGroupRight(grpId);
		for(MenuTree menu : menuTreeList){
			PermissionTree pTree = new PermissionTree(menu.getMenuid(),menu.getMenuname(),menu.getIcon(),isRolePermission(menu.getMenuid(), list));
			pTree.setChecked(null);
			convertMenuTree(menu,pTree,list);
			result.add(pTree);
		}
		
		
		return result;
	}
	
	private boolean isRolePermission(String menuCode,List<GroupRight> list){
		boolean bl = false;
		for(GroupRight info : list){
			if(info.getMenuCode()!=null&&menuCode.equals(info.getMenuCode())){
				bl = true;
				break;
			}
		}
		return bl;
	}

	private PermissionTree convertMenuTree(MenuTree menu,PermissionTree pTree,List<GroupRight> list){
		if(menu.getMenus()!=null&&menu.getMenus().size()>0){
			for(MenuTree temp : menu.getMenus()){
				boolean bl = isRolePermission(temp.getMenuid(), list);
				pTree.addChildren(new PermissionTree(temp.getMenuid(),temp.getMenuname(),temp.getIcon(),bl));
				convertMenuTree(temp, pTree,list);
			}
		}
		return pTree;
	}
	
	@RequestMapping("/permissionTree/save")
	@ResponseBody
	public Map<String,Object> savePermissionTree(String treeData) throws Exception {
		logger.info("invoke savePermissionTree...");
		ObjectMapper mapper = JsonBinder.buildNonNullBinder().getMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, PermissionTree.class);
		List<PermissionTree> treeDataList = mapper.readValue(treeData, javaType); ; 
		if(treeDataList!=null&&treeDataList.size()>0){
			groupRightService.delete(treeDataList.get(0).getGrpId());
			for(PermissionTree p : treeDataList){
				GroupRight groupright = new GroupRight(p.getGrpId(),p.getId());
				groupRightService.insert(groupright);
			}
		}
		
		return ResultJsonUtil.getResultMap();
	}

	@RequestMapping("/getMenuById")
	@ResponseBody
	public Map<String,Object> getMenuById(String menuCode) {
		logger.info("invoke getMenuById...");
		return ResultJsonUtil.getResultMap();
	}

	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Menu menu,BindingResult binding) {
		logger.info("invoke insert...");
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		menuService.insert(menu);	
		return ResultJsonUtil.getResultMap();
	}

	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Menu menu,BindingResult binding) {
		logger.info("invoke update...");
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		menuService.update(menu);
		return ResultJsonUtil.getResultMap();
	}
	
	//参数名要加s
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delUser(String menuCodes) {
		logger.info("invoke delUser...");
		if(menuCodes!=null){
			String[] ids = menuCodes.split(",");
			for(String workId : ids){
				if(!workId.equals("")){
					menuService.delete(workId);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}

}
