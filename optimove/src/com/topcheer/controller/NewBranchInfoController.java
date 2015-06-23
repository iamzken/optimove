package com.topcheer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.NewBranchInfo;
import com.topcheer.service.INewBranchInfoService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/newBranchInfos")
public class NewBranchInfoController {
	@Autowired
	private INewBranchInfoService newBranchInfoService;
	
	@RequestMapping("/searchNewBranchInfo")
	@ResponseBody
	public Map<String,Object> searchNewBranchInfo(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		newBranchInfoService.searchNewBranchInfo(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getNewBranchInfo")
	@ResponseBody
	public Map<String,Object> getNewBranchInfo(String branchCode) {
		List<NewBranchInfo> newBranchInfo = newBranchInfoService.getNewBranchInfo(branchCode);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, newBranchInfo);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid NewBranchInfo newBranchInfo,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		newBranchInfoService.insert(newBranchInfo);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid NewBranchInfo newBranchInfo,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		newBranchInfoService.update(newBranchInfo);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String branchCodes) {
		if(branchCodes!=null){
			String[] _ids = branchCodes.split(",");
			for(String branchCode : _ids){
				if(!branchCode.equals("")){
					newBranchInfoService.delete(branchCode);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
