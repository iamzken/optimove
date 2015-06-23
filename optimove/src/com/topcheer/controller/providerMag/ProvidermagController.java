package com.topcheer.controller.providerMag;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.providerMag.Providermag;
import com.topcheer.service.providerMag.IProvidermagService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/providermags")
public class ProvidermagController {
	@Autowired
	private IProvidermagService providermagService;
	
	@RequestMapping("/searchProvidermag")
	@ResponseBody
	public Map<String,Object> searchProvidermag(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		providermagService.searchProvidermag(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getProvidermag")
	@ResponseBody
	public Map<String,Object> getProvidermag(String providerid) {
		List<Providermag> providermag = providermagService.getProvidermag(providerid);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, providermag);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Providermag providermag,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		providermagService.insert(providermag);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Providermag providermag,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		providermagService.update(providermag);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String providerids) {
		if(providerids!=null){
			String[] _ids = providerids.split(",");
			for(String providerid : _ids){
				if(!providerid.equals("")){
					providermagService.delete(providerid);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
