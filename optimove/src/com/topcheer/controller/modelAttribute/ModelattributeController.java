package com.topcheer.controller.modelAttribute;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.modelAttribute.Modelattribute;
import com.topcheer.service.mapModelInfo.IMapmodelinfoService;
import com.topcheer.service.modelAttribute.IModelattributeService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/modelattributes")
public class ModelattributeController {
	
	@Autowired
	private IModelattributeService modelattributeService;
	@Autowired
	private IMapmodelinfoService mapmodelinfoService;
	
	@RequestMapping("/searchMapInfo")
	@ResponseBody
	public Map<String,Object> searchMapInfo(HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tablename", request.getParameter("tablename"));
		map.put("rows",modelattributeService.searchModelattribute(map));
		map.put("longitude", request.getParameter("longitude"));
		map.put("latitude", request.getParameter("latitude"));
		map.put("condition", request.getParameter("distanceCondition")+" and (name like '%"+request.getParameter("name")+"%' or address like'%"+request.getParameter("name")+"%')");
		map.put("dataList", modelattributeService.searchMapInfo(map));
		mapmodelinfoService.convertOracleStructTypeToJavaType(map);
		
		return map;
	}
	
	@RequestMapping("/searchModelAttributeAndDataList")
	@ResponseBody
	public Map<String,Object> searchModelAttributeAndDataList(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		
		Page page = Page.newBuilder(request);
		Map<String, Object> searchMap = Page.getSearchPageMap(request);
		String[] queryConditions = request.getParameter("condition").split("&");
		StringBuffer sBuffer = new StringBuffer("");
		for(int i=0;i<queryConditions.length;i++){
			String param = queryConditions[i];
			if(param != null && !"".equals(param)){
				String[] pair = param.split("=");
				if(pair.length == 2){
					sBuffer.append(" and "+pair[0]+" like '%"+URLDecoder.decode(pair[1],"UTF-8")+"%'");
				}
			}
		}
		if(!"".equals(sBuffer.toString())){
			searchMap.put("condition", "where "+sBuffer.toString().substring(sBuffer.toString().indexOf("and")+3));
			page.setPageNo(1);
		}else{
			searchMap.put("condition", "");
		}
		if(searchMap.get("resultpoint") != null && !"".equals(searchMap.get("resultpoint"))){
			return getRegionDataList(page, searchMap, sBuffer);
		}
		
		Map<String,Object> map = page.getPageReturn();
		map.put("dataList", mapmodelinfoService.getTableDataList(searchMap));
		mapmodelinfoService.convertOracleStructTypeToJavaType(map);
		
		return page.getPageReturn();
	}
	
	@RequestMapping("/searchModelAttributeAndDataList4Test")
	@ResponseBody
	public Map<String,Object> searchModelAttributeAndDataList4Test(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		Page page = Page.newBuilder(request);
		Map<String, Object> searchMap = Page.getSearchPageMap(request);
		String[] queryConditions = request.getParameter("condition").split("&");
		StringBuffer sBuffer = new StringBuffer("");
		for(int i=0;i<queryConditions.length;i++){
			String param = queryConditions[i];
			if(param != null && !"".equals(param)){
				String[] pair = param.split("=");
				if(pair.length == 2){
					sBuffer.append(" and "+pair[0]+" like '%"+URLDecoder.decode(pair[1],"UTF-8")+"%'");
				}
			}
		}
		if(!"".equals(sBuffer.toString())){
			searchMap.put("condition", "where "+sBuffer.toString().substring(sBuffer.toString().indexOf("and")+3));
			page.setPageNo(1);
		}else{
			searchMap.put("condition", "");
		}
		if(searchMap.get("resultpoint") != null && !"".equals(searchMap.get("resultpoint"))){
			return getRegionDataList(page, searchMap, sBuffer);
		}
		
		Map<String,Object> map = page.getPageReturn();
		map.put("dataList", mapmodelinfoService.getTableDataList(searchMap));
		mapmodelinfoService.convertOracleStructTypeToJavaType(map);
		response.getWriter().write(request.getParameter("callbackparam")+"("+JSONArray.fromObject(page.getPageReturn())+")");
		
		return null;
	}

	private Map<String, Object> getRegionDataList(Page page, Map<String, Object> searchMap,StringBuffer sBuffer) {
		
		String resultpoint = (String)searchMap.get("resultpoint");
		String[] splitresult = resultpoint.split(",");
		String topleftx = splitresult[0];
		String toplefty = splitresult[1];
		
		List<Modelattribute> modelattributes = new ArrayList<Modelattribute>();
		modelattributes = modelattributeService.searchModelattribute(searchMap);
		StringBuffer searchSql = new StringBuffer("select ");
		for (int i = 0; i < modelattributes.size(); i++) {
			String attribute = modelattributes.get(i).getModelattribute();
			if("longitude".equals(attribute)){
				searchSql.append(" nvl(A.location.sdo_point.x,0) as longitude,");
			}else if("latitude".equals(attribute)){
				searchSql.append(" nvl(A.location.sdo_point.y,0) as latitude,");
			}else{
				searchSql.append(" nvl(A."+attribute+",' ') as "+attribute+",");
			}
		}
		StringBuffer selectsql = new StringBuffer(searchSql.substring(0,searchSql.lastIndexOf(","))+" from ");
		selectsql.append((String)searchMap.get("tablename")+" A");
		StringBuffer conditionsql_0  = new StringBuffer(" where sdo_filter(A.location,mdsys.sdo_geometry(2003,8307,null,mdsys.sdo_elem_info_array(1,1003,1),mdsys.sdo_ordinate_array("+resultpoint+","+topleftx+","+toplefty+")),'querytype=WINDOW')='TRUE'");
		StringBuffer conditionsql_1 = new StringBuffer(" and sdo_relate(A.location,mdsys.sdo_geometry(2003,8307,null,mdsys.sdo_elem_info_array(1,1003,1),mdsys.sdo_ordinate_array("+resultpoint+","+topleftx+","+toplefty+")),'mask=INSIDE querytype=WINDOW') = 'TRUE' order by A.name");
		selectsql.append(conditionsql_0+" "+sBuffer.toString());
		selectsql.append(conditionsql_1);
		searchMap.put("searchsql", selectsql.toString());
		mapmodelinfoService.searchRegionModelData(searchMap);
		
		return  page.getPageReturn();
	}
	
	@RequestMapping("/searchModelattribute")
	@ResponseBody
	public Map<String,Object> searchModelattribute(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		modelattributeService.searchModelattribute(Page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/searchModelattribute4Display")
	@ResponseBody
	public Map<String,Object> searchModelattribute4Display(HttpServletRequest request,HttpServletResponse response) throws IOException {

		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap.put("tablename", request.getParameter("tablename"));
		List<Modelattribute> modelattributes = modelattributeService.searchModelattribute4Display(searchMap);
		StringBuffer column = new StringBuffer("[[");
		for (int i = 0; i < modelattributes.size(); i++) {
			String fieldId = modelattributes.get(i).getModelattribute();
			String title = modelattributes.get(i).getModelattributename();
			if(i==modelattributes.size()-1){
					column.append("{field:'"+fieldId.toUpperCase()+"',title:'"+title+"'}");
			}else{
					column.append("{field:'"+fieldId.toUpperCase()+"',title:'"+title+"'},");
			}
		}
		column.append("]]");
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("column", column);
		return result;
		
	}
	
	@RequestMapping("/searchModelattribute4Display4Test")
	@ResponseBody
	public Map<String,Object> searchModelattribute4Display4Test(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap.put("tablename", request.getParameter("tablename"));
		List<Modelattribute> modelattributes = modelattributeService.searchModelattribute4Display(searchMap);
		StringBuffer column = new StringBuffer("[[");
		for (int i = 0; i < modelattributes.size(); i++) {
			String fieldId = modelattributes.get(i).getModelattribute();
			String title = modelattributes.get(i).getModelattributename();
			if(i==modelattributes.size()-1){
				column.append("{field:'"+fieldId.toUpperCase()+"',title:'"+title+"'}");
			}else{
				column.append("{field:'"+fieldId.toUpperCase()+"',title:'"+title+"'},");
			}
		}
		column.append("]]");
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("column", column);
		response.getWriter().write(request.getParameter("callbackparam")+"("+column+")");
		return null;
		
	}
	
	@RequestMapping("/searchModelattribute4Search")
	@ResponseBody
	public Map<String,Object> searchModelattribute4Search(HttpServletRequest request) {
		
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap.put("tablename", request.getParameter("tablename"));
		List<Modelattribute> modelattributes = modelattributeService.searchModelattribute4Search(searchMap);
		StringBuffer column = new StringBuffer("[[");
		for (int i = 0; i < modelattributes.size(); i++) {
			String fieldId = modelattributes.get(i).getModelattribute();
			String title = modelattributes.get(i).getModelattributename();
			if(i==modelattributes.size()-1){
					column.append("{field:'"+fieldId.toUpperCase()+"',title:'"+title+"'}");
			}else{
					column.append("{field:'"+fieldId.toUpperCase()+"',title:'"+title+"'},");
			}
		}
		column.append("]]");
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("column", column);
		return result;
		
	}
	
	@RequestMapping("/getModelattribute")
	@ResponseBody
	public Map<String,Object> getModelattribute(String id) {
		List<Modelattribute> modelattribute = modelattributeService.getModelattribute(id);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, modelattribute);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Modelattribute modelattribute,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		modelattribute.setId(UUID.randomUUID().toString());
		modelattribute.setModelattributeflag("1");
		modelattributeService.insert(modelattribute);
		alterTable(modelattribute.getTablename(), modelattribute.getModelattribute(), modelattribute.getModelattributetype(), 0);
		
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Modelattribute modelattribute,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		modelattributeService.update(modelattribute);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String ids) {
		if(ids!=null){
			String[] _ids = ids.split(",");
			for(String id : _ids){
				if(!id.equals("")){
					List<Modelattribute> modelattribute = modelattributeService.getModelattribute(id);
					for (Modelattribute ma : modelattribute) {
						alterTable(ma.getTablename(), ma.getModelattribute(), ma.getModelattributetype(), 1);
					}
					modelattributeService.delete(id);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}

	@RequestMapping("/deleteByTablename")
	@ResponseBody
	public Map<String,Object> deleteByTablename(String tablename) {
		if(tablename != null && !"".equals(tablename)){
			modelattributeService.deleteByTablename(tablename);
		}
		return null;
	}
	
	@RequestMapping("/alterTable")
	@ResponseBody
	public Map<String,Object> alterTable(String tablename, String attr, String attrType, int flag) {
		String type = "VARCHAR2(1024)";
		if ("int".equals(attrType)) {
			type = "NUMBER";
		}
		String alterSql = "";
		if (flag == 0) {
			alterSql = "alter table " + tablename + " add " + attr + " " + type;
		} else if (flag == 1) {
			alterSql = "alter table " + tablename + " drop column " + attr;
		}
		Map<String,Object> attrMap = ResultJsonUtil.getResultMap();
		attrMap.put("alterSql", alterSql);
		modelattributeService.alterTable(attrMap);
		return null;
	}
	@RequestMapping("/getAttributes")
	@ResponseBody
	public Map<String,Object> getAttributes(String tablename){
		Map<String,Object> result = new HashMap<String,Object>();
		List<Modelattribute> modelattributes = new ArrayList<Modelattribute>();
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap.put("tablename", tablename);
		modelattributes = modelattributeService.searchModelattribute(searchMap);
		StringBuffer column = new StringBuffer("[[");
		for (int i = 0; i < modelattributes.size(); i++) {
			String fieldId = modelattributes.get(i).getModelattribute();
			String title = modelattributes.get(i).getModelattributename();
			if(i==modelattributes.size()-1){
					column.append("{field:'"+fieldId.toUpperCase()+"',title:'"+title+"'}");
			}else{
					column.append("{field:'"+fieldId.toUpperCase()+"',title:'"+title+"'},");
			}
		}
		column.append("]]");
		result.put("column", column);
		System.out.println(column);
		return result;
	}
}
