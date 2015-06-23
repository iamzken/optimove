package com.topcheer.controller.basedata;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.basedata.Currency;
import com.topcheer.service.basedata.ICurrencyService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/currencys")
public class CurrencyController {
	@Autowired
	private ICurrencyService currencyService;
	
	@RequestMapping("/searchCurrency")
	@ResponseBody
	public Map<String,Object> searchCurrency(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		currencyService.searchCurrency(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getCurrency")
	@ResponseBody
	public Map<String,Object> getCurrency(String code) {
		List<Currency> currency = currencyService.getCurrency(code);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, currency);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Currency currency,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		currencyService.insert(currency);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Currency currency,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		currencyService.update(currency);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String codes) {
		if(codes!=null){
			String[] _ids = codes.split(",");
			for(String code : _ids){
				if(!code.equals("")){
					currencyService.delete(code);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
