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

import com.topcheer.model.Lookupvalues;
import com.topcheer.service.ILookupvaluesService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/lookupvalues")
public class LookupvaluesController {
	@Autowired
	private ILookupvaluesService lookupvaluesService;
	private static final Logger logger=Logger.getLogger(LookupvaluesController.class);
	@RequestMapping("/searchLookupvalues")
	@ResponseBody
	public Map<String,Object> searchLookvalues(HttpServletRequest request){
		logger.info("searchLookvalues start");
		Page page=Page.newBuilder(request);
		lookupvaluesService.searchLookvalues(page.getSearchPageMap(request));
		logger.info("searchLookvalues end");
		return page.getPageReturn();
	}
	@RequestMapping("/getLookvalues")
	@ResponseBody
	public Map<String,Object> getLookvalues(String lookupCode){
		logger.info("getLookvalues start");
		List<Lookupvalues> lookupvalues=lookupvaluesService.getLookvalues(lookupCode);
		logger.info("lookupvalues is empty"+lookupvalues.isEmpty());
		Map<String,Object> result=ResultJsonUtil.getResultMap();		
		result.put(ResultJsonUtil.DATA, lookupvalues);
		logger.info("getLookvalues end");
		return result;
	}
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Lookupvalues lookupvalues,BindingResult binding){
		if(binding.hasErrors()){
			logger.info("valid Lookupvalues failed.");
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(), binding);
		}
		lookupvaluesService.insert(lookupvalues);
		logger.info("insert lookupvalues");
		
		return ResultJsonUtil.getResultMap();
	}
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Lookupvalues lookupvalues,BindingResult binding){
		if(binding.hasErrors()){
			logger.info("valid Lookupvalues failed.");
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		logger.info("update lookupvalues start");
		lookupvaluesService.update(lookupvalues);
		logger.info("update lookupvalues end");
		return ResultJsonUtil.getResultMap();
	}
	@RequestMapping("/getLookvaluesBylookup")
	@ResponseBody
	public Map<String,Object> getLookvaluesBylookup(HttpServletRequest request){
		logger.info("getLookvaluesBylookup start");
		Page page=Page.newBuilder(request);
		lookupvaluesService.searchLookvalues(page.getSearchPageMap(request));
		logger.info("getLookvaluesBylookup end");
		return page.getPageReturn();		
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> deleteLookvalues(@Valid Lookupvalues lookupvalues,HttpServletRequest request){
		if(lookupvalues!=null){
			
			String lookupType=request.getParameter("lookupType");
			String lookupCode=request.getParameter("lookupCodes");
			lookupvalues.setLookupType(lookupType);
			lookupvalues.setLookupCode(lookupCode);
			logger.info("deleteLookvalues start");
			
			lookupvaluesService.delete(lookupvalues);
			logger.info("deleteLookvalues end");
					
				
			
		}
		return ResultJsonUtil.getResultMap();
	}

}
