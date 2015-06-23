package com.topcheer.controller.developerKeyMag;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.developerKeyMag.Developerkeymag;
import com.topcheer.service.developerKeyMag.IDeveloperkeymagService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/developerkeymags")
public class DeveloperkeymagController {
	@Autowired
	private IDeveloperkeymagService developerkeymagService;
	
	@RequestMapping("/searchDeveloperkeymag")
	@ResponseBody
	public Map<String,Object> searchDeveloperkeymag(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		developerkeymagService.searchDeveloperkeymag(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getDeveloperkeymag")
	@ResponseBody
	public Map<String,Object> getDeveloperkeymag(String keyid) {
		List<Developerkeymag> developerkeymag = developerkeymagService.getDeveloperkeymag(keyid);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, developerkeymag);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Developerkeymag developerkeymag,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
//		developerkeymag.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
//		developerkeymag.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
//		developerkeymag.setUpdatedate(DateUtil.getCurrentDate());
//		developerkeymag.setUpdatetime(DateUtil.getCurrentTime());
		developerkeymagService.insert(developerkeymag);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Developerkeymag developerkeymag,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
//		developerkeymag.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
//		developerkeymag.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
//		developerkeymag.setUpdatedate(DateUtil.getCurrentDate());
//		developerkeymag.setUpdatetime(DateUtil.getCurrentTime());
		developerkeymagService.update(developerkeymag);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String keyids) {
		if(keyids!=null){
			String[] _ids = keyids.split(",");
			for(String keyid : _ids){
				if(!keyid.equals("")){
					developerkeymagService.delete(keyid);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
