package com.topcheer.controller.nephogramData;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.nephogramData.Nephogramdata;
import com.topcheer.service.nephogramData.INephogramdataService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/nephogramdatas")
public class NephogramdataController {
	@Autowired
	private INephogramdataService nephogramdataService;
	
	@RequestMapping("/searchNephogramdata")
	@ResponseBody
	public Map<String,Object> searchNephogramdata(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		nephogramdataService.searchNephogramdata(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getNephogramdata")
	@ResponseBody
	public Map<String,Object> getNephogramdata(String nephogramdataid) {
		List<Nephogramdata> nephogramdata = nephogramdataService.getNephogramdata(nephogramdataid);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, nephogramdata);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Nephogramdata nephogramdata,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		nephogramdataService.insert(nephogramdata);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Nephogramdata nephogramdata,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		nephogramdataService.update(nephogramdata);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String nephogramdataids) {
		if(nephogramdataids!=null){
			String[] _ids = nephogramdataids.split(",");
			for(String nephogramdataid : _ids){
				if(!nephogramdataid.equals("")){
					nephogramdataService.delete(nephogramdataid);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
