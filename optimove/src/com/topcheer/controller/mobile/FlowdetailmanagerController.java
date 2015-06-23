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

import com.topcheer.model.mobile.Flowdetailmanager;
import com.topcheer.service.mobile.IFlowdetailmanagerService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/flowdetailmanagers")
public class FlowdetailmanagerController {
	@Autowired
	private IFlowdetailmanagerService flowdetailmanagerService;
	
	@RequestMapping("/searchFlowdetailmanager")
	@ResponseBody
	public Map<String,Object> searchFlowdetailmanager(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		flowdetailmanagerService.searchFlowdetailmanager(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getFlowdetailmanager")
	@ResponseBody
	public Map<String,Object> getFlowdetailmanager(String flowcode) {
		List<Flowdetailmanager> flowdetailmanager = flowdetailmanagerService.getFlowdetailmanager(flowcode);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, flowdetailmanager);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Flowdetailmanager flowdetailmanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		flowdetailmanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		flowdetailmanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		flowdetailmanager.setUpdatedate(DateUtil.getCurrentDate());
		flowdetailmanager.setUpdatetime(DateUtil.getCurrentTime());
		flowdetailmanagerService.insert(flowdetailmanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Flowdetailmanager flowdetailmanager,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		flowdetailmanager.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		flowdetailmanager.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		flowdetailmanager.setUpdatedate(DateUtil.getCurrentDate());
		flowdetailmanager.setUpdatetime(DateUtil.getCurrentTime());
		flowdetailmanagerService.update(flowdetailmanager);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String flowcodes) {
		if(flowcodes!=null){
			String[] _ids = flowcodes.split(",");
			for(String flowcode : _ids){
				if(!flowcode.equals("")){
					flowdetailmanagerService.delete(flowcode);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
