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

import com.topcheer.model.mobile.Recognitemplatemanager;
import com.topcheer.service.mobile.IRecognitemplatemanagerService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/recognitemplatemanagers")
public class RecognitemplatemanagerController {
	@Autowired
	private IRecognitemplatemanagerService recognitemplatemanagerService;
	
	@RequestMapping("/searchRecognitemplatemanager")
	@ResponseBody
	public Map<String,Object> searchRecognitemplatemanager(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		recognitemplatemanagerService.searchRecognitemplatemanager(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getRecognitemplatemanager")
	@ResponseBody
	public Map<String,Object> getRecognitemplatemanager(String templatecode) {
		List<Recognitemplatemanager> recognitemplatemanager = recognitemplatemanagerService.getRecognitemplatemanager(templatecode);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, recognitemplatemanager);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Recognitemplatemanager recognitemplatemanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		recognitemplatemanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		recognitemplatemanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		recognitemplatemanager.setUpdatedate(DateUtil.getCurrentDate());
		recognitemplatemanager.setUpdatetime(DateUtil.getCurrentTime());
		recognitemplatemanagerService.insert(recognitemplatemanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Recognitemplatemanager recognitemplatemanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		recognitemplatemanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		recognitemplatemanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		recognitemplatemanager.setUpdatedate(DateUtil.getCurrentDate());
		recognitemplatemanager.setUpdatetime(DateUtil.getCurrentTime());
		recognitemplatemanagerService.update(recognitemplatemanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String templatecodes) {
		if(templatecodes!=null){
			String[] _ids = templatecodes.split(",");
			for(String templatecode : _ids){
				if(!templatecode.equals("")){
					recognitemplatemanagerService.delete(templatecode);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
