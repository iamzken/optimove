package com.topcheer.controller.bankNewsMgr;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.bankNewsMgr.Banknews;
import com.topcheer.service.bankNewsMgr.IBanknewsService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/banknewss")
public class BanknewsController {
	@Autowired
	private IBanknewsService banknewsService;
	
	@RequestMapping("/searchBanknews")
	@ResponseBody
	public Map<String,Object> searchBanknews(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		banknewsService.searchBanknews(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getBanknews")
	@ResponseBody
	public Map<String,Object> getBanknews(String newscode) {
		List<Banknews> banknews = banknewsService.getBanknews(newscode);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, banknews);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Banknews banknews,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		banknewsService.insert(banknews);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Banknews banknews,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		banknewsService.update(banknews);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String newscodes) {
		if(newscodes!=null){
			String[] _ids = newscodes.split(",");
			for(String newscode : _ids){
				if(!newscode.equals("")){
					banknewsService.delete(newscode);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
