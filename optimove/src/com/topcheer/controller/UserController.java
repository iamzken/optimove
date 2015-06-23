package com.topcheer.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.User;
import com.topcheer.model.UserGroup;
import com.topcheer.service.IGroupInfoService;
import com.topcheer.service.IUserGroupService;
import com.topcheer.service.IUserService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;
import com.topcheer.utils.StringUtil;

@Controller
@RequestMapping("/users")
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IGroupInfoService groupInfoService;
	@Autowired
	private IUserGroupService userGroupService;
	
	@RequestMapping("/searchUser")
	@ResponseBody
	public Map<String,Object> searchUser(HttpServletRequest request) {
		logger.info("invoke searchUser...");
		Page page = Page.newBuilder(request);
		userService.searchUser(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public Map<String,Object> getUser(String workId) {
		logger.info("invoke getUser...");
		User user = userService.getUser(workId);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, user);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid User user,BindingResult binding) {
		logger.info("invoke insert...");
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		if(StringUtil.isNull(user.getUserPwd())){
			user.setUserPwd(ConstantUtil.DF_PASSWORD);
		}
		if(user.getGroupId()!=null&&!"".equals(user.getGroupId())){
			UserGroup group = new UserGroup();
			group.setGrpId(user.getGroupId());
			group.setWorkId(user.getWorkId());
			group.setCreationDate(DateUtil.getCurrentDate());
			group.setLastUpdateDate(DateUtil.getCurrentDate());
			userGroupService.insert(group);
		}
		userService.insert(user);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid User user,BindingResult binding) {
		logger.info("invoke update...");
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		if(StringUtil.isNull(user.getUserPwd())){
			user.setUserPwd(ConstantUtil.DF_PASSWORD);
		}
		if(user.getGroupId()!=null&&!"".equals(user.getGroupId())){
			List<UserGroup> groupList = userGroupService.getUserGroup(user.getWorkId());
			UserGroup group = new UserGroup();
			if(groupList!=null&&groupList.size()>0){
				group = groupList.get(0);
				group.setGrpId(user.getGroupId());
				group.setLastUpdateDate(DateUtil.getCurrentDate());
				userGroupService.update(group);
			}else{
				group.setGrpId(user.getGroupId());
				group.setWorkId(user.getWorkId());
				group.setCreationDate(DateUtil.getCurrentDate());
				group.setLastUpdateDate(DateUtil.getCurrentDate());
				userGroupService.insert(group);
			}
		}
		userService.update(user);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/updatePwd")
	@ResponseBody
	public Map<String,Object> updatePassword(String workId,String newPwd,String oldPwd) {
		logger.info("invoke update...");
		User info = userService.getUser(workId);
		if(info==null){
			Map<String,Object> result = ResultJsonUtil.getResultErrorMap();
			result.put(ResultJsonUtil.ERROR_MSG, "用户不存在");
			return result;
		}
		if(!info.getUserPwd().equals(oldPwd)){
			Map<String,Object> result = ResultJsonUtil.getResultErrorMap();
			result.put(ResultJsonUtil.ERROR_MSG, "原密码不正确");
			return result;
		}
		info.setUserPwd(newPwd);
		
		userService.update(info);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String workIds) {
		logger.info("invoke delete...");
		if(workIds!=null){
			String[] ids = workIds.split(",");
			for(String workId : ids){
				if(!workId.equals("")){
					userService.delete(workId);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
