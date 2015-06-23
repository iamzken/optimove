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

import com.topcheer.model.mobile.Transmanager;
import com.topcheer.service.mobile.ITransmanagerService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/transmanagers")
public class TransmanagerController {
	@Autowired
	private ITransmanagerService transmanagerService;
	
	@RequestMapping("/searchTransmanager")
	@ResponseBody
	public Map<String,Object> searchTransmanager(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		transmanagerService.searchTransmanager(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getTransmanager")
	@ResponseBody
	public Map<String,Object> getTransmanager(String transcode) {
		List<Transmanager> transmanager = transmanagerService.getTransmanager(transcode);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, transmanager);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Transmanager transmanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		
		transmanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		transmanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		transmanager.setUpdatedate(DateUtil.getCurrentDate());
		transmanager.setUpdatetime(DateUtil.getCurrentTime());
		transmanagerService.insert(transmanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Transmanager transmanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		transmanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		transmanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		transmanager.setUpdatedate(DateUtil.getCurrentDate());
		transmanager.setUpdatetime(DateUtil.getCurrentTime());
		transmanagerService.update(transmanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String transcodes) {
		if(transcodes!=null){
			String[] _ids = transcodes.split(",");
			for(String transcode : _ids){
				if(!transcode.equals("")){
					transmanagerService.delete(transcode);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
