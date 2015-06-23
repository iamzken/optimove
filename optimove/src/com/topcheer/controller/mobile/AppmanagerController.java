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

import com.topcheer.model.mobile.Appmanager;
import com.topcheer.service.mobile.IAppmanagerService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/appmanagers")
public class AppmanagerController {
	@Autowired
	private IAppmanagerService appmanagerService;
	
	@RequestMapping("/searchAppmanager")
	@ResponseBody
	public Map<String,Object> searchAppmanager(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		appmanagerService.searchAppmanager(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getAppmanager")
	@ResponseBody
	public Map<String,Object> getAppmanager(String appcode) {
		List<Appmanager> appmanager = appmanagerService.getAppmanager(appcode);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, appmanager);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Appmanager appmanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		appmanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		appmanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		appmanager.setUpdatedate(DateUtil.getCurrentDate());
		appmanager.setUpdatetime(DateUtil.getCurrentTime());
		appmanagerService.insert(appmanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Appmanager appmanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		appmanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		appmanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		appmanager.setUpdatedate(DateUtil.getCurrentDate());
		appmanager.setUpdatetime(DateUtil.getCurrentTime());
		appmanagerService.update(appmanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String appcodes) {
		if(appcodes!=null){
			String[] _ids = appcodes.split(",");
			for(String appcode : _ids){
				if(!appcode.equals("")){
					appmanagerService.delete(appcode);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
