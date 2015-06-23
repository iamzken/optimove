package com.topcheer.controller.applicationMag;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.applicationMag.Applicationmag;
import com.topcheer.service.applicationMag.IApplicationmagService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/applicationmags")
public class ApplicationmagController {
	@Autowired
	private IApplicationmagService applicationmagService;
	
	@RequestMapping("/searchApplicationmag")
	@ResponseBody
	public Map<String,Object> searchApplicationmag(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		applicationmagService.searchApplicationmag(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getApplicationmag")
	@ResponseBody
	public Map<String,Object> getApplicationmag(String applicationid) {
		List<Applicationmag> applicationmag = applicationmagService.getApplicationmag(applicationid);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, applicationmag);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Applicationmag applicationmag,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		applicationmagService.insert(applicationmag);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Applicationmag applicationmag,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		applicationmagService.update(applicationmag);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String applicationids) {
		if(applicationids!=null){
			String[] _ids = applicationids.split(",");
			for(String applicationid : _ids){
				if(!applicationid.equals("")){
					applicationmagService.delete(applicationid);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
