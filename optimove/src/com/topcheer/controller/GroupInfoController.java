package com.topcheer.controller;

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

import com.topcheer.model.GroupInfo;
import com.topcheer.service.IGroupInfoService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/groupInfos")
public class GroupInfoController {
	private static final Logger logger = Logger.getLogger(GroupInfoController.class);
	@Autowired
	private IGroupInfoService groupInfoService;
	
	@RequestMapping("/searchGroupInfo")
	@ResponseBody
	public Map<String,Object> searchGroupInfo(HttpServletRequest request) {
		logger.info("invoke searchGroupInfo...");
		Page page = Page.newBuilder(request);
		groupInfoService.searchGroupInfo(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getGroupInfo")
	@ResponseBody
	public Map<String,Object> getGroupInfo(String grpId) {
		logger.info("invoke getGroupInfo...");
		List<GroupInfo> groupInfo = groupInfoService.getGroupInfo(grpId);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, groupInfo);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid GroupInfo groupInfo,BindingResult binding) {
		logger.info("invoke insert...");
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		groupInfoService.insert(groupInfo);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid GroupInfo groupInfo,BindingResult binding) {
		logger.info("invoke update...");
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		groupInfoService.update(groupInfo);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String grpIds) {
		logger.info("invoke delete...");
		if(grpIds!=null){
			String[] _ids = grpIds.split(",");
			for(String grpId : _ids){
				if(!grpId.equals("")){
					groupInfoService.delete(grpId);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
