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

import com.topcheer.model.mobile.Servicemanager;
import com.topcheer.service.mobile.IServicemanagerService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/servicemanagers")
public class ServicemanagerController {
	@Autowired
	private IServicemanagerService servicemanagerService;
	
	@RequestMapping("/searchServicemanager")
	@ResponseBody
	public Map<String,Object> searchServicemanager(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		servicemanagerService.searchServicemanager(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getServicemanager")
	@ResponseBody
	public Map<String,Object> getServicemanager(String servicecode) {
		List<Servicemanager> servicemanager = servicemanagerService.getServicemanager(servicecode);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, servicemanager);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Servicemanager servicemanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		servicemanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		servicemanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		servicemanager.setUpdatedate(DateUtil.getCurrentDate());
		servicemanager.setUpdatetime(DateUtil.getCurrentTime());
		servicemanagerService.insert(servicemanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Servicemanager servicemanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		servicemanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		servicemanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		servicemanager.setUpdatedate(DateUtil.getCurrentDate());
		servicemanager.setUpdatetime(DateUtil.getCurrentTime());
		servicemanagerService.update(servicemanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String servicecodes) {
		if(servicecodes!=null){
			String[] _ids = servicecodes.split(",");
			for(String servicecode : _ids){
				if(!servicecode.equals("")){
					servicemanagerService.delete(servicecode);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
