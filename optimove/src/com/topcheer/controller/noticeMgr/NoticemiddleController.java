package com.topcheer.controller.noticeMgr;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.noticeMgr.Noticemiddle;
import com.topcheer.service.noticeMgr.INoticemiddleService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/noticemiddles")
public class NoticemiddleController {
	@Autowired
	private INoticemiddleService noticemiddleService;
	
	@RequestMapping("/searchNoticemiddle")
	@ResponseBody
	public Map<String,Object> searchNoticemiddle(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		noticemiddleService.searchNoticemiddle(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getNoticemiddle")
	@ResponseBody
	public Map<String,Object> getNoticemiddle(String noticecode) {
		List<Noticemiddle> noticemiddle = noticemiddleService.getNoticemiddle(noticecode);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, noticemiddle);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Noticemiddle noticemiddle,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		noticemiddleService.insert(noticemiddle);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Noticemiddle noticemiddle,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		noticemiddleService.update(noticemiddle);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String noticecodes) {
		if(noticecodes!=null){
			String[] _ids = noticecodes.split(",");
			for(String noticecode : _ids){
				if(!noticecode.equals("")){
					noticemiddleService.delete(noticecode);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
