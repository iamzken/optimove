package com.topcheer.controller.publicData;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.publicData.Publicdata;
import com.topcheer.service.publicData.IPublicdataService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/publicdatas")
public class PublicdataController {
	@Autowired
	private IPublicdataService publicdataService;
	
	@RequestMapping("/searchPublicdata")
	@ResponseBody
	public Map<String,Object> searchPublicdata(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		publicdataService.searchPublicdata(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getPublicdata")
	@ResponseBody
	public Map<String,Object> getPublicdata(String publicdataid) {
		List<Publicdata> publicdata = publicdataService.getPublicdata(publicdataid);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, publicdata);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Publicdata publicdata,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		publicdataService.insert(publicdata);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Publicdata publicdata,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		publicdataService.update(publicdata);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String publicdataids) {
		if(publicdataids!=null){
			String[] _ids = publicdataids.split(",");
			for(String publicdataid : _ids){
				if(!publicdataid.equals("")){
					publicdataService.delete(publicdataid);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
