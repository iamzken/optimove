package com.topcheer.controller.deptMgr;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.deptMgr.Department;
import com.topcheer.service.deptMgr.IDepartmentService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
	@Autowired
	private IDepartmentService departmentService;
	
	@RequestMapping("/searchDepartment")
	@ResponseBody
	public Map<String,Object> searchTbldepartment(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		departmentService.searchDepartment(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getDepartment")
	@ResponseBody
	public Map<String,Object> getTbldepartment(String departmentcode) {
		List<Department> tbldepartment = departmentService.getDepartment(departmentcode);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, tbldepartment);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Department department,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		departmentService.insert(department);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Department department,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		departmentService.update(department);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String departmentcodes) {
		if(departmentcodes!=null){
			String[] _ids = departmentcodes.split(",");
			for(String departmentcode : _ids){
				if(!departmentcode.equals("")){
					departmentService.delete(departmentcode);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
