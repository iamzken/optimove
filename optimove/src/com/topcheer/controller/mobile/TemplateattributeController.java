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

import com.topcheer.model.mobile.Templateattribute;
import com.topcheer.service.mobile.ITemplateattributeService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/templateattributes")
public class TemplateattributeController {
	@Autowired
	private ITemplateattributeService templateattributeService;
	
	@RequestMapping("/searchTemplateattribute")
	@ResponseBody
	public Map<String,Object> searchTemplateattribute(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		templateattributeService.searchTemplateattribute(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getTemplateattribute")
	@ResponseBody
	public Map<String,Object> getTemplateattribute(String id) {
		List<Templateattribute> templateattribute = templateattributeService.getTemplateattribute(id);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, templateattribute);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Templateattribute templateattribute,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		templateattribute.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		templateattribute.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		templateattribute.setUpdatedate(DateUtil.getCurrentDate());
		templateattribute.setUpdatetime(DateUtil.getCurrentTime());
		templateattributeService.insert(templateattribute);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Templateattribute templateattribute,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		templateattribute.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		templateattribute.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		templateattribute.setUpdatedate(DateUtil.getCurrentDate());
		templateattribute.setUpdatetime(DateUtil.getCurrentTime());
		templateattributeService.update(templateattribute);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String ids) {
		if(ids!=null){
			String[] _ids = ids.split(",");
			for(String id : _ids){
				if(!id.equals("")){
					templateattributeService.delete(id);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
