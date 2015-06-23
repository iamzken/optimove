package com.topcheer.controller.nephogramAtrribute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.nephogramAtrribute.Nephogramatrribute;
import com.topcheer.service.nephogramAtrribute.INephogramatrributeService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/nephogramatrributes")
public class NephogramatrributeController {
	@Autowired
	private INephogramatrributeService nephogramatrributeService;
	
	@RequestMapping("/searchNephogramatrribute")
	@ResponseBody
	public Map<String,Object> searchNephogramatrribute(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		nephogramatrributeService.searchNephogramatrribute(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getNephogramatrribute")
	@ResponseBody
	public Map<String,Object> getNephogramatrribute(String nephogramattrid) {
		List<Nephogramatrribute> nephogramatrribute = nephogramatrributeService.getNephogramatrribute(nephogramattrid);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, nephogramatrribute);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Nephogramatrribute nephogramatrribute,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		nephogramatrributeService.insert(nephogramatrribute);
		
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Nephogramatrribute nephogramatrribute,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		nephogramatrributeService.update(nephogramatrribute);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> deleteNephogramAttr(@Valid Nephogramatrribute nephogramatrribute,HttpServletRequest request){
		if(nephogramatrribute!=null){
			String nephogramattrid=request.getParameter("nephogramattrid");
			nephogramatrributeService.delete(nephogramattrid);
		}
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/getNephogramAttrById")
	@ResponseBody
	public Map<String,Object> getNephogramAttrById(HttpServletRequest request){
		Page page=Page.newBuilder(request);
		nephogramatrributeService.searchNephogramatrribute(page.getSearchPageMap(request));
		return page.getPageReturn();		
	}
	@RequestMapping("/getNephogramatrributeByModelId")
	@ResponseBody
	public Map<String,Object> getNephogramatrributeByModelId(String nephogrammodelid) {
		Map searchMap = new HashMap();
		searchMap.put("nephogrammodelid", nephogrammodelid);
		List<Nephogramatrribute> nephogramatrribute = nephogramatrributeService.searchNephogramatrribute(searchMap);
		Map<String,Object> result = new HashMap<String,Object>();
		for (Nephogramatrribute nephogramatrribute2 : nephogramatrribute) {
			String attributecode = nephogramatrribute2.getAttributecode();
			String attributeName = nephogramatrribute2.getAttributename();
			result.put(attributecode, attributeName);
		}
		return result;
	}

}
