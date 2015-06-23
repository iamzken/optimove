package com.topcheer.controller.mobile;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.mobile.Billsapply;
import com.topcheer.service.mobile.IBillsapplyService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/billsapplys")
public class BillsapplyController {
	@Autowired
	private IBillsapplyService billsapplyService;
	
	@RequestMapping("/searchBillsapply")
	@ResponseBody
	public Map<String,Object> searchBillsapply(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		billsapplyService.searchBillsapply(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getBillsapply")
	@ResponseBody
	public Map<String,Object> getBillsapply(String custno) {
		List<Billsapply> billsapply = billsapplyService.getBillsapply(custno);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, billsapply);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Billsapply billsapply,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		billsapply.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		billsapply.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		billsapply.setUpdatedate(DateUtil.getCurrentDate());
		billsapply.setUpdatetime(DateUtil.getCurrentTime());
		billsapplyService.insert(billsapply);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Billsapply billsapply,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		billsapply.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		billsapply.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		billsapply.setUpdatedate(DateUtil.getCurrentDate());
		billsapply.setUpdatetime(DateUtil.getCurrentTime());
		billsapplyService.update(billsapply);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String custnos) {
		if(custnos!=null){
			String[] _ids = custnos.split(",");
			for(String custno : _ids){
				if(!custno.equals("")){
					billsapplyService.delete(custno);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
