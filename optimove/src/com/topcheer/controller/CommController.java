package com.topcheer.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.beanutils.MethodUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.topcheer.model.ChartInfo;
import com.topcheer.model.ColumnInfo;
import com.topcheer.model.Constant;
import com.topcheer.model.GroupInfo;
import com.topcheer.model.Lookupvalues;
import com.topcheer.model.NewBranchInfo;
import com.topcheer.model.PermissionTree;
import com.topcheer.model.ReportInfo;
import com.topcheer.model.XYLine;
import com.topcheer.service.ICommService;
import com.topcheer.service.IGroupInfoService;
import com.topcheer.service.ILookupvaluesService;
import com.topcheer.service.INewBranchInfoService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.JavaUtils;
import com.topcheer.utils.JsonBinder;
import com.topcheer.utils.Page;
import com.topcheer.utils.PoiUtil;
import com.topcheer.utils.ReportUtil;
import com.topcheer.utils.ResultJsonUtil;
import com.topcheer.utils.SpringContextUtil;
import com.topcheer.utils.StringUtil;

/**
 * 
 * @author tanxf
 *
 */
@Controller
@RequestMapping("/comm")
public class CommController {
	private static final Logger logger = Logger.getLogger(CommController.class);
	@Autowired
	private ILookupvaluesService lookupvaluesService;
	@Autowired
	private INewBranchInfoService newBranchInfoService;
	
	@Autowired
	private ICommService commService;
	
	@Autowired
	private IGroupInfoService groupInfoService;
	
	@RequestMapping("/reportData")
	@ResponseBody
	public Map<String,Object>  getReportData(HttpServletRequest request,HttpServletResponse response) throws Exception {
		logger.info("invoke createReport...");
		Map<String,Object> result = ResultJsonUtil.getResultMap();
//		ReportUtil.createBir(request, response);
		ChartInfo info = ReportUtil.getSearchMap(request);
		List<Map<String, Object>> dataList = info.getDataList();
		Map<String,XYLine> dataMap = new HashMap<String, XYLine>();
		for(Map<String, Object> data : dataList){
			String type = data.get("type").toString();
			String value1 = data.get("value1").toString();
			String value2 = data.get("value2")==null?"":data.get("value2").toString();
			XYLine line = dataMap.get(type);
			List<List> list = null;
			if(line == null){
				line = new XYLine();
				list = new ArrayList<List>();
				line.setData(list);
				line.setLabel(type);
				dataMap.put(type, line);
			}
			List valueList = new ArrayList();
			valueList.add(value1);
			valueList.add(value2);
			list.add(valueList);
			
		}
		result.put(ResultJsonUtil.DATA, dataMap.values());
		
		return result;
	}
	
	@RequestMapping("/report")
	public ModelAndView  createReport(HttpServletRequest request,HttpServletResponse response) throws Exception {
		logger.info("invoke createReport...");
		Map<String,Object> result = ResultJsonUtil.getResultMap();
//		ReportUtil.createBir(request, response);
		ReportUtil.createReport(request, response);
		
		return null;
	}
	
	@RequestMapping("/checkExportExcel")
	@ResponseBody
	public Map<String,Object>  checkExportExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		logger.info("invoke checkExportExcel...");
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		String columnInfos = request.getParameter("columnInfos");
		columnInfos = columnInfos.replace("[[", "[").replace("]]", "]");
		String title = request.getParameter("downTitle");
		String report = request.getParameter("report");
		String mapping = request.getParameter("serviceObjId");
		String  methodName = request.getParameter("methodName");
		String  constantKeys = request.getParameter("constantKeys");
		ObjectMapper mapper = JsonBinder.buildNonNullBinder().getMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ColumnInfo.class);
		List<ColumnInfo> columnInfoList = mapper.readValue(columnInfos, javaType); 
		int maxRow = Integer.valueOf(JavaUtils.getPropertiesVal("maxRow"));
		if(columnInfoList!=null&&columnInfoList.size()>maxRow){
			result.put(ResultJsonUtil.RESULT, ResultJsonUtil.FAIL);
			result.put(ResultJsonUtil.ERROR_MSG, "数据过大，请添加条件过滤，最大条数为"+maxRow+",当前为"+columnInfoList.size());
		}
		
		
		return result;
	}
	
	@RequestMapping("/exportExcel")
	public ModelAndView  exportExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		logger.info("invoke exportExcel...");
		String columnInfos = request.getParameter("columnInfos");
		columnInfos = columnInfos.replace("[[", "[").replace("]]", "]");
		String title = request.getParameter("downTitle") ==null ? "Excel下载" : request.getParameter("downTitle");
		String report = request.getParameter("report");
		String serviceObjId = request.getParameter("serviceObjId");
		String  methodName = request.getParameter("methodName");
		String  constantKeys = request.getParameter("constantKeys");
		String  isShowTitle = StringUtil.isNull(request.getParameter("isShowTitle"))?null:String.valueOf(request.getParameter("isShowTitle"));;
		Integer  titleHeight = StringUtil.isNull(request.getParameter("titleHeight"))?null:Integer.valueOf(request.getParameter("titleHeight"));
		Integer  titleFontSize = StringUtil.isNull(request.getParameter("titleFontSize"))?null:Integer.valueOf(request.getParameter("titleFontSize"));
		String  subTitle = StringUtil.isNull(request.getParameter("subTitle"))?null:String.valueOf(request.getParameter("subTitle"));;
		ObjectMapper mapper = JsonBinder.buildNonNullBinder().getMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ColumnInfo.class);
		List<ColumnInfo> columnInfoList = mapper.readValue(columnInfos, javaType); 
		Object obj = SpringContextUtil.getBean(serviceObjId);
		Map searchMap = getSearchMap(request);
		List<Object> list = (List) MethodUtils.invokeMethod(obj, methodName, searchMap);
		
		Map<String,List<Constant>> constatsMap = (Map<String, List<Constant>>) loadConstants(constantKeys).get(ResultJsonUtil.DATA);
		Map<String,List<Constant>> tempConstatsMap = new HashMap<String, List<Constant>>();
		if(constatsMap==null){
			constatsMap = new HashMap<String, List<Constant>>();
		}
		Iterator<String> iterator = constatsMap.keySet().iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			List<Constant> tempList = constatsMap.get(key);
			tempConstatsMap.put(key.toUpperCase(), tempList);
		}
		constatsMap = tempConstatsMap;
		ReportInfo reportInfo = new ReportInfo(title,columnInfoList,list,serviceObjId);
		reportInfo.setIsShowTitle(isShowTitle);
		reportInfo.setTitleHeight(titleHeight);
		reportInfo.setTitleFontSize(titleFontSize);
		reportInfo.setSubTitle(subTitle);
		PoiUtil.exportExcel(reportInfo,response,constatsMap);
		
		return null;
	}
	
	public Map getSearchMap(HttpServletRequest request){
		Map searchMap = new HashMap();
		Map map = request.getParameterMap();
	    Iterator iterator = map.keySet().iterator();
	    while(iterator.hasNext()){
	    	Object key = iterator.next();
    		Object value = map.get(key);
    		if(!key.toString().contains("s_")){
    			continue;
    		}
    		key = key.toString().replace("s_", "");
    		if(value!=null&&value instanceof Object[]&&((Object[])value).length==1){
    			searchMap.put(key, ((Object[])value)[0]);
    		}else{
    			searchMap.put(key, value);
    		}
	    }
	    
	    return searchMap;
	}
	
	@RequestMapping("/searchConstant")
	@ResponseBody
	public Map<String,Object> searchConstant(String key) {
		logger.info("invoke searchConstant...");
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		List<Constant> list = commService.searchConstant(key);
		
		result.put(ResultJsonUtil.DATA, list);
		return result;
	}
	
	@RequestMapping("/loadConstants")
	@ResponseBody
	public Map<String,Object> loadConstants(String constantKeys) {
		logger.info("invoke loadConstants...");
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		if(constantKeys==null||"".equals(constantKeys)){
			return result;
		}
		Map<String,List<Constant>> constatsMap = new HashMap<String, List<Constant>>();
		String[] strs = constantKeys.split(",");
		for(String key : strs){
			List<Constant> list = commService.searchConstant(key);
			String showKey = key;
			if(showKey.contains("|")){
				showKey = showKey.split("\\|")[0];
				if(showKey.contains("&")){
					showKey = showKey.split("&")[1];
				}
			}
			
			constatsMap.put(showKey,list);
		}
		
		result.put(ResultJsonUtil.DATA, constatsMap);
		return result;
	}
	

}
