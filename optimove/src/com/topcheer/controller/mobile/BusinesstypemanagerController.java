package com.topcheer.controller.mobile;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.mobile.Businesstypemanager;
import com.topcheer.service.mobile.IBusinesstypemanagerService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/businesstypemanagers")
public class BusinesstypemanagerController {
	@Autowired
	private IBusinesstypemanagerService businesstypemanagerService;
	
	@RequestMapping("/searchBusinesstypemanager")
	@ResponseBody
	public Map<String,Object> searchBusinesstypemanager(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		businesstypemanagerService.searchBusinesstypemanager(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getBusinesstypemanager")
	@ResponseBody
	public Map<String,Object> getBusinesstypemanager(String typecode) {
		List<Businesstypemanager> businesstypemanager = businesstypemanagerService.getBusinesstypemanager(typecode);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, businesstypemanager);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Businesstypemanager businesstypemanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		businesstypemanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		businesstypemanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		businesstypemanager.setUpdatedate(DateUtil.getCurrentDate());
		businesstypemanager.setUpdatetime(DateUtil.getCurrentTime());
		businesstypemanagerService.insert(businesstypemanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Businesstypemanager businesstypemanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		businesstypemanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		businesstypemanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		businesstypemanager.setUpdatedate(DateUtil.getCurrentDate());
		businesstypemanager.setUpdatetime(DateUtil.getCurrentTime());
		businesstypemanagerService.update(businesstypemanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String typecodes) {
		if(typecodes!=null){
			String[] _ids = typecodes.split(",");
			for(String typecode : _ids){
				if(!typecode.equals("")){
					businesstypemanagerService.delete(typecode);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
