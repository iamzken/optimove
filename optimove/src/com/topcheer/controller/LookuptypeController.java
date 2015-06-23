package com.topcheer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.Lookuptype;
import com.topcheer.service.ILookuptypeService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/lookuptypes")
public class LookuptypeController {
	@Autowired
	private ILookuptypeService lookuptypeService;
	
	
	private static final Logger logger=Logger.getLogger(LookuptypeController.class);
	
	/**
	 * 字典类型查询
	 * @param request	 
	 * @return
	 */
	@RequestMapping("/searchLookuptype")
	@ResponseBody
	public Map<String,Object> searchLookuptype(HttpServletRequest request){
		logger.info("searchLookuptype start");
		Page page=Page.newBuilder(request);		
		lookuptypeService.searchLookuptype(page.getSearchPageMap(request));
		logger.info("searchLookuptype end");
		return page.getPageReturn();		
	}
	/**
	 * 按字典类型查询
	 * @param lookupType	 
	 * @return
	 */
	@RequestMapping("/getLookuptype")
	@ResponseBody
	public Map<String,Object> getLookuptype(String  lookupType){
		logger.info("getLookuptype start");
		List<Lookuptype> lookuptype=lookuptypeService.getLookuptype(lookupType);		
		logger.info("lookuptype is empty "+lookuptype.isEmpty());
		Map<String,Object> result=ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, lookuptype);
		logger.info("getLookuptype end");
		return result;
	}
	
	/**
	 * 添加字典类型记录
	 * @param lookupType
	 * @param binding	 
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Lookuptype lookuptype,BindingResult binding){
		if(binding.hasErrors()){
			logger.info("valid lookuptype failed.");
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(), binding);
		}		
		lookuptypeService.insert(lookuptype);
		logger.info("insert lookuptype");
		return ResultJsonUtil.getResultMap();
		
	}
	/**
	 * 修改字典类型记录
	 * @param lookupType
	 * @param binding	 
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Lookuptype lookuptype, BindingResult binding){
		if(binding.hasErrors()){
			logger.info("valid lookuptype failed.");
			return ResultJsonUtil.analyzeError(binding);
		}
		lookuptypeService.update(lookuptype);
		logger.info("update lookuptype");
		return ResultJsonUtil.getResultMap();
		
	}
	
	/**
	 * 删除字典类型记录
	 * @param lookupType
	 * @param binding	 
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String lookupTypes){
		if(lookupTypes!=null){
			logger.info("delete lookuptype start");
			String[] _ids=lookupTypes.split(",");
			for(String lookupType:_ids ){
				if(!lookupType.equals("")){
					lookuptypeService.delete(lookupType);	
					logger.info("delete lookuptype end");
				}
			}
			
		}
		return ResultJsonUtil.getResultMap();
	}
	
	
}
