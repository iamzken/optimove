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

import com.topcheer.model.mobile.Transtemplatemanager;
import com.topcheer.service.mobile.ITranstemplatemanagerService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/transtemplatemanagers")
public class TranstemplatemanagerController {
	@Autowired
	private ITranstemplatemanagerService transtemplatemanagerService;
	
	@RequestMapping("/searchTranstemplatemanager")
	@ResponseBody
	public Map<String,Object> searchTranstemplatemanager(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		transtemplatemanagerService.searchTranstemplatemanager(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getTranstemplatemanager")
	@ResponseBody
	public Map<String,Object> getTranstemplatemanager(String templatecode) {
		List<Transtemplatemanager> transtemplatemanager = transtemplatemanagerService.getTranstemplatemanager(templatecode);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, transtemplatemanager);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Transtemplatemanager transtemplatemanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		transtemplatemanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		transtemplatemanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		transtemplatemanager.setUpdatedate(DateUtil.getCurrentDate());
		transtemplatemanager.setUpdatetime(DateUtil.getCurrentTime());
		transtemplatemanagerService.insert(transtemplatemanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Transtemplatemanager transtemplatemanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		transtemplatemanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		transtemplatemanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		transtemplatemanager.setUpdatedate(DateUtil.getCurrentDate());
		transtemplatemanager.setUpdatetime(DateUtil.getCurrentTime());
		transtemplatemanagerService.update(transtemplatemanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String templatecodes) {
		if(templatecodes!=null){
			String[] _ids = templatecodes.split(",");
			for(String templatecode : _ids){
				if(!templatecode.equals("")){
					transtemplatemanagerService.delete(templatecode);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
