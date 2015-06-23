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

import com.topcheer.model.mobile.Flowmanager;
import com.topcheer.service.mobile.IFlowmanagerService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/flowmanagers")
public class FlowmanagerController {
	@Autowired
	private IFlowmanagerService flowmanagerService;
	
	@RequestMapping("/searchFlowmanager")
	@ResponseBody
	public Map<String,Object> searchFlowmanager(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		flowmanagerService.searchFlowmanager(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getFlowmanager")
	@ResponseBody
	public Map<String,Object> getFlowmanager(String flowcode) {
		List<Flowmanager> flowmanager = flowmanagerService.getFlowmanager(flowcode);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, flowmanager);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Flowmanager flowmanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		flowmanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		flowmanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		flowmanager.setUpdatedate(DateUtil.getCurrentDate());
		flowmanager.setUpdatetime(DateUtil.getCurrentTime());
		flowmanagerService.insert(flowmanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Flowmanager flowmanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		flowmanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		flowmanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		flowmanager.setUpdatedate(DateUtil.getCurrentDate());
		flowmanager.setUpdatetime(DateUtil.getCurrentTime());
		flowmanagerService.update(flowmanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String flowcodes) {
		if(flowcodes!=null){
			String[] _ids = flowcodes.split(",");
			for(String flowcode : _ids){
				if(!flowcode.equals("")){
					flowmanagerService.delete(flowcode);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
