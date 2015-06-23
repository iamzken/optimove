package com.topcheer.controller.macrostatistics;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.macrostatistics.Macrostatistics;
import com.topcheer.service.macrostatistics.IMacrostatisticsService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/macrostatisticss")
public class MacrostatisticsController {
	@Autowired
	private IMacrostatisticsService macrostatisticsService;
	
	@RequestMapping("/searchMacrostatistics")
	@ResponseBody
	public Map<String,Object> searchMacrostatistics(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		macrostatisticsService.searchMacrostatistics(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getMacrostatistics")
	@ResponseBody
	public Map<String,Object> getMacrostatistics(String macrostatisticsid) {
		List<Macrostatistics> macrostatistics = macrostatisticsService.getMacrostatistics(macrostatisticsid);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, macrostatistics);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Macrostatistics macrostatistics,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		macrostatisticsService.insert(macrostatistics);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Macrostatistics macrostatistics,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		macrostatisticsService.update(macrostatistics);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String macrostatisticsids) {
		if(macrostatisticsids!=null){
			String[] _ids = macrostatisticsids.split(",");
			for(String macrostatisticsid : _ids){
				if(!macrostatisticsid.equals("")){
					macrostatisticsService.delete(macrostatisticsid);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
