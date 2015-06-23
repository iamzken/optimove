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

import com.topcheer.model.mobile.Templatearea;
import com.topcheer.service.mobile.ITemplateareaService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/templateareas")
public class TemplateareaController {
	@Autowired
	private ITemplateareaService templateareaService;
	
	@RequestMapping("/searchTemplatearea")
	@ResponseBody
	public Map<String,Object> searchTemplatearea(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		templateareaService.searchTemplatearea(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getTemplatearea")
	@ResponseBody
	public Map<String,Object> getTemplatearea(String id) {
		List<Templatearea> templatearea = templateareaService.getTemplatearea(id);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, templatearea);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Templatearea templatearea,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		templatearea.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		templatearea.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		templatearea.setUpdatedate(DateUtil.getCurrentDate());
		templatearea.setUpdatetime(DateUtil.getCurrentTime());
		templateareaService.insert(templatearea);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Templatearea templatearea,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		templatearea.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		templatearea.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		templatearea.setUpdatedate(DateUtil.getCurrentDate());
		templatearea.setUpdatetime(DateUtil.getCurrentTime());
		templateareaService.update(templatearea);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String ids) {
		if(ids!=null){
			String[] _ids = ids.split(",");
			for(String id : _ids){
				if(!id.equals("")){
					templateareaService.delete(id);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
