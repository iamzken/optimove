package com.topcheer.controller.grabData;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.grabData.Grabdata;
import com.topcheer.service.grabData.IGrabdataService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/grabdatas")
public class GrabdataController {
	@Autowired
	private IGrabdataService grabdataService;
	
	@RequestMapping("/searchGrabdata")
	@ResponseBody
	public Map<String,Object> searchGrabdata(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		grabdataService.searchGrabdata(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getGrabdata")
	@ResponseBody
	public Map<String,Object> getGrabdata(String grabdataid) {
		List<Grabdata> grabdata = grabdataService.getGrabdata(grabdataid);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, grabdata);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Grabdata grabdata,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		grabdataService.insert(grabdata);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Grabdata grabdata,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		grabdataService.update(grabdata);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String grabdataids) {
		if(grabdataids!=null){
			String[] _ids = grabdataids.split(",");
			for(String grabdataid : _ids){
				if(!grabdataid.equals("")){
					grabdataService.delete(grabdataid);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
