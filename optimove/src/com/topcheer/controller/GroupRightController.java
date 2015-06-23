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

import com.topcheer.model.GroupRight;
import com.topcheer.service.IGroupRightService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/groupRights")
public class GroupRightController {
	private static final Logger logger = Logger.getLogger(GroupRightController.class);
	@Autowired
	private IGroupRightService groupRightService;
	
	@RequestMapping("/searchGroupRight")
	@ResponseBody
	public Map<String,Object> searchGroupRight(HttpServletRequest request) {
		logger.info("invoke searchGroupRight...");
		Page page = Page.newBuilder(request);
		groupRightService.searchGroupRight(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getGroupRight")
	@ResponseBody
	public Map<String,Object> getGroupRight(String grpId) {
		logger.info("invoke getGroupRight...");
		List<GroupRight> groupRight = groupRightService.getGroupRight(grpId);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, groupRight);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid GroupRight groupRight,BindingResult binding) {
		logger.info("invoke insert...");
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		groupRightService.insert(groupRight);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid GroupRight groupRight,BindingResult binding) {
		logger.info("invoke update...");
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		groupRightService.update(groupRight);
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
					groupRightService.delete(grpId);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
