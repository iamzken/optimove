package com.topcheer.controller.networkUnicom;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.networkUnicom.Networkunicom;
import com.topcheer.service.networkUnicom.INetworkunicomService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/networkunicoms")
public class NetworkunicomController {
	@Autowired
	private INetworkunicomService networkunicomService;
	
	@RequestMapping("/searchNetworkunicom")
	@ResponseBody
	public Map<String,Object> searchNetworkunicom(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		networkunicomService.searchNetworkunicom(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getNetworkunicom")
	@ResponseBody
	public Map<String,Object> getNetworkunicom(String networkunicomid) {
		List<Networkunicom> networkunicom = networkunicomService.getNetworkunicom(networkunicomid);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, networkunicom);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Networkunicom networkunicom,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		networkunicomService.insert(networkunicom);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Networkunicom networkunicom,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		networkunicomService.update(networkunicom);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String networkunicomids) {
		if(networkunicomids!=null){
			String[] _ids = networkunicomids.split(",");
			for(String networkunicomid : _ids){
				if(!networkunicomid.equals("")){
					networkunicomService.delete(networkunicomid);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
