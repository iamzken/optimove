package com.topcheer.controller.apiServiceProvider;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.apiServiceProvider.Apiserviceprovider;
import com.topcheer.service.apiServiceProvider.IApiserviceproviderService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/apiserviceproviders")
public class ApiserviceproviderController {
	@Autowired
	private IApiserviceproviderService apiserviceproviderService;
	
	@RequestMapping("/searchApiserviceprovider")
	@ResponseBody
	public Map<String,Object> searchApiserviceprovider(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		apiserviceproviderService.searchApiserviceprovider(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getApiserviceprovider")
	@ResponseBody
	public Map<String,Object> getApiserviceprovider(String apicode) {
		List<Apiserviceprovider> apiserviceprovider = apiserviceproviderService.getApiserviceprovider(apicode);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, apiserviceprovider);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Apiserviceprovider apiserviceprovider,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		apiserviceproviderService.insert(apiserviceprovider);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Apiserviceprovider apiserviceprovider,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		apiserviceproviderService.update(apiserviceprovider);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String apicodes) {
		if(apicodes!=null){
			String[] _ids = apicodes.split(",");
			for(String apicode : _ids){
				if(!apicode.equals("")){
					apiserviceproviderService.delete(apicode);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
