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

import com.topcheer.model.mobile.Businessproductmanager;
import com.topcheer.service.mobile.IBusinessproductmanagerService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/businessproductmanagers")
public class BusinessproductmanagerController {
	@Autowired
	private IBusinessproductmanagerService businessproductmanagerService;
	
	@RequestMapping("/searchBusinessproductmanager")
	@ResponseBody
	public Map<String,Object> searchBusinessproductmanager(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		businessproductmanagerService.searchBusinessproductmanager(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getBusinessproductmanager")
	@ResponseBody
	public Map<String,Object> getBusinessproductmanager(String productcode) {
		List<Businessproductmanager> businessproductmanager = businessproductmanagerService.getBusinessproductmanager(productcode);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, businessproductmanager);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Businessproductmanager businessproductmanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		businessproductmanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		businessproductmanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		businessproductmanager.setUpdatedate(DateUtil.getCurrentDate());
		businessproductmanager.setUpdatetime(DateUtil.getCurrentTime());
		businessproductmanagerService.insert(businessproductmanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Businessproductmanager businessproductmanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		businessproductmanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		businessproductmanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		businessproductmanager.setUpdatedate(DateUtil.getCurrentDate());
		businessproductmanager.setUpdatetime(DateUtil.getCurrentTime());
		businessproductmanagerService.update(businessproductmanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String productcodes) {
		if(productcodes!=null){
			String[] _ids = productcodes.split(",");
			for(String productcode : _ids){
				if(!productcode.equals("")){
					businessproductmanagerService.delete(productcode);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
