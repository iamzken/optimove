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

import com.topcheer.model.mobile.Systemmanager;
import com.topcheer.service.mobile.ISystemmanagerService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/systemmanagers")
public class SystemmanagerController {
	@Autowired
	private ISystemmanagerService systemmanagerService;
	
	@RequestMapping("/searchSystemmanager")
	@ResponseBody
	public Map<String,Object> searchSystemmanager(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		systemmanagerService.searchSystemmanager(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getSystemmanager")
	@ResponseBody
	public Map<String,Object> getSystemmanager(String syscode) {
		List<Systemmanager> systemmanager = systemmanagerService.getSystemmanager(syscode);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, systemmanager);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Systemmanager systemmanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		systemmanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		systemmanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		systemmanager.setUpdatedate(DateUtil.getCurrentDate());
		systemmanager.setUpdatetime(DateUtil.getCurrentTime());
		systemmanagerService.insert(systemmanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Systemmanager systemmanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		systemmanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		systemmanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		systemmanager.setUpdatedate(DateUtil.getCurrentDate());
		systemmanager.setUpdatetime(DateUtil.getCurrentTime());
		systemmanagerService.update(systemmanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String syscodes) {
		if(syscodes!=null){
			String[] _ids = syscodes.split(",");
			for(String syscode : _ids){
				if(!syscode.equals("")){
					systemmanagerService.delete(syscode);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
