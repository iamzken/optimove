package com.topcheer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.BaDept;
import com.topcheer.model.GroupInfo;
import com.topcheer.model.NewBranchInfo;
import com.topcheer.model.StyPro;
import com.topcheer.model.StyProDetail;
import com.topcheer.model.User;
import com.topcheer.service.IBaDeptService;
import com.topcheer.service.IGroupInfoService;
import com.topcheer.service.INewBranchInfoService;
import com.topcheer.service.IStyProService;
import com.topcheer.service.IUserService;
import com.topcheer.utils.FileUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
public class StyProController {
	@Autowired
	private IBaDeptService baDeptService;
	@Autowired
	private IGroupInfoService groupInfoService;
	@Autowired
	private INewBranchInfoService newBranchInfoService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IStyProService styProService;
	
	@RequestMapping("/styPro")
	public String StyPro(HttpServletRequest request) {
		
		return "module/styPro";
	}
	
	//更新流程表
	@RequestMapping("/updateStyPro")
	@ResponseBody
	public Map<String,Object> updateStyPro(@Valid StyPro styPro,@Valid StyProDetail styProDetail,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		
		styProService.update(styPro,styProDetail);
		
		return ResultJsonUtil.getResultMap();
	}
	
	//编辑时显示流程明细
	@RequestMapping("/searchDetail")
	@ResponseBody
	public Map<String,Object> searchDetail(StyPro styPro) {
		
		StyPro pro = styProService.getStyPro(styPro.getId());
		List<StyProDetail> proDetailList = styProService.searchStyProDetail(styPro);
		
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		
		result.put("pro",pro);
		result.put("proDetails",proDetailList);
				
		return result;
	}
	
	//发布流程
	@RequestMapping("/publish")
	@ResponseBody
	public Map<String,Object> publish(@Valid StyPro styPro, BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		
		styProService.publish(styPro);
		
		return ResultJsonUtil.getResultMap();
	}
	
	//获取流程列表
	@RequestMapping("/searchStyPros")
	@ResponseBody
	public Map<String,Object> searchStyPros(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		styProService.searchStyPro(page.getSearchPageMap(request));
		
		return page.getPageReturn();
	}
	
	//获取查询条件下拉列表
	@RequestMapping("/getStySearchInfos")
	@ResponseBody
	public Map<String,Object> getStySearchInfos() {
		
		List<NewBranchInfo> newBranchInfoList = newBranchInfoService.searchAll();
		List<BaDept> baDeptList = baDeptService.searchAll();
		List<GroupInfo> groupInfoList = groupInfoService.searchAll();
		List<User> userList = userService.searchAll();
		
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put("branchData",newBranchInfoList);
		result.put("deptData",baDeptList);
		result.put("groupData",groupInfoList);
		result.put("userData",userList);
		
		JSONObject json = new JSONObject();
		json.putAll(result);
		
		/*FileUtil.createFile("E:\\temp1.json", json.get("branchData").toString());
		FileUtil.createFile("E:\\temp2.json", json.get("deptData").toString());
		FileUtil.createFile("E:\\temp3.json", json.get("groupData").toString());
		FileUtil.createFile("E:\\temp4.json", json.get("userData").toString());*/
		
		return result;
	}
	
	@RequestMapping("/getBrachInfos")
	@ResponseBody
	public Map<String,Object> getBrachInfos() {
		
		List<NewBranchInfo> newBranchInfoList = newBranchInfoService.searchAll();
		
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		
		result.put("branchData",newBranchInfoList);
				
		return result;
	}
	
	@RequestMapping("/getDeptInfos")
	@ResponseBody
	public Map<String,Object> getDeptInfos() {
		
		List<BaDept> baDeptList = baDeptService.searchAll();
		
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		
		result.put("deptData",baDeptList);
				
		return result;
	}
	
	@RequestMapping("/getUserInfos")
	@ResponseBody
	public Map<String,Object> getUserInfos() {
		
		List<User> userList = userService.searchAll();
		
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		
		result.put("userData",userList);
				
		return result;
	}
	
	@RequestMapping("/getGroupInfos")
	@ResponseBody
	public Map<String,Object> getGroupInfos() {
		
		List<GroupInfo> groupInfoList = groupInfoService.searchAll();
		
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		
		result.put("groupData",groupInfoList);
				
		return result;
	}
	
	@RequestMapping(value="/getJson",headers={"content-type=application/json","content-type=application/xml"})
	@ResponseBody
	public Map<String,Object> getJson(String branchJson) {
		
		FileUtil.createFile("E:\\workspace\\jnys\\web\\module\\temp1.json", "(["+branchJson.toString()+"])");
		
		List<GroupInfo> groupInfoList = groupInfoService.searchAll();
		
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		
		result.put("groupData",groupInfoList);
				
		return result;
	}
	
	@RequestMapping(value="/getJson2",headers={"content-type=application/json","content-type=application/xml"})
	@ResponseBody
	public Map<String,Object> getJson2(String deptJson,String groupJson,String userJson) {
		
		FileUtil.createFile("E:\\workspace\\jnys\\web\\module\\temp3.json", "(["+groupJson.toString()+"])");
		FileUtil.createFile("E:\\workspace\\jnys\\web\\module\\temp4.json", "(["+userJson.toString()+"])");
		FileUtil.createFile("E:\\workspace\\jnys\\web\\module\\temp2.json", "(["+deptJson.toString()+"])");
		
		List<GroupInfo> groupInfoList = groupInfoService.searchAll();
		
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		
		result.put("groupData",groupInfoList);
				
		return result;
	}
	
	//查询获取左边树
	@RequestMapping("/getLeftInfos")
	@ResponseBody
	public Map<String,Object> getLeftInfos(User user) {
		
		List<User> userList = userService.getLeftUserInfo(user);
		List<GroupInfo> groupList = userService.getLeftGroupInfo(user);
		
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		
		result.put("leftUserData",userList);
		result.put("leftGroupData",groupList);
				
		return result;
	}
	
	//新增流程
	@RequestMapping("/insertStyPro")
	@ResponseBody
	public Map<String,Object> insertStyPro(@Valid StyPro styPro,@Valid StyProDetail styProDetail,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		
		styProService.insert(styPro,styProDetail);
		
		return ResultJsonUtil.getResultMap();
	}
}