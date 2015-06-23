package com.topcheer.controller.mapModelMag;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.topcheer.model.mapModelMag.Mapmodelmag;
import com.topcheer.service.mapModelMag.IMapmodelmagService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/mapmodelmags")
public class MapmodelmagController {
	private Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private IMapmodelmagService mapmodelmagService;
	
	@RequestMapping("/searchMapmodelmag")
	@ResponseBody
	public Map<String,Object> searchMapmodelmag(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		mapmodelmagService.searchMapmodelmag(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getMapmodelmag")
	@ResponseBody
	public Map<String,Object> getMapmodelmag(String mapmodelid) {
		List<Mapmodelmag> mapmodelmag = mapmodelmagService.getMapmodelmag(mapmodelid);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, mapmodelmag);
		return result;
	}
	
	@RequestMapping("/getMapModelJson")
	@ResponseBody
	public Map<String, String> getMapModelJson(String mapmodeltype) {
		String result = "[{text:\""+mapmodeltype+"类型\",children:[";
		String children = "";
		List<Mapmodelmag> mapmodelmag = mapmodelmagService.getMapModelJson(mapmodeltype);
		for (Mapmodelmag mapmodelmag2 : mapmodelmag) {
			children += "{text:\""+mapmodelmag2.getMapmodelname()+"\",attributes:{url:\"\"}},";
		}
		if (children.length() > 0) {
			children = children.substring(0, children.length()-1);
		}
		result += children;
		result += "]}]";
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", result);
		return map;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Mapmodelmag mapmodelmag,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		mapmodelmagService.insert(mapmodelmag);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Mapmodelmag mapmodelmag,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		mapmodelmagService.update(mapmodelmag);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String mapmodelids) {
		if(mapmodelids!=null){
			String[] _ids = mapmodelids.split(",");
			for(String mapmodelid : _ids){
				if(!mapmodelid.equals("")){
					mapmodelmagService.delete(mapmodelid);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}
	@RequestMapping("/searchAll")
	@ResponseBody
	public Map<String,String> searchAll(HttpServletRequest request){
		//0-模板库 1-信息窗口库 2-图层库 3-控件库
		String modeltype=request.getParameter("modeltype");
		List<Mapmodelmag> mapmodelmag = mapmodelmagService.searchAll();
		Map<String,String> map = new HashMap<String,String>();
		for (Mapmodelmag mapmodelmag2 : mapmodelmag) {
			String type = mapmodelmag2.getMapmodeltype();
			//if("模板".equals(type)){
			if(modeltype.equals(type)){
				map.put(mapmodelmag2.getMapmodelid(), mapmodelmag2.getMapmodelname());
			}
		}
		return map;
	}
	@RequestMapping("/getMapmodelmagMap")
	@ResponseBody
	public Map<String,Object> getMapmodelmagMap(String mapmodelid) {
		List<Mapmodelmag> mapmodelmag = mapmodelmagService.getMapmodelmag(mapmodelid);
		Map<String,Object> map = new HashMap<String,Object>();
		for (Mapmodelmag mapmodelmag2 : mapmodelmag) {
			String[] attrs = mapmodelmag2.getAttributedes().split(",");
				for (String string : attrs) {
					map.put(string, string);
				}
				map.put("model", mapmodelmag2.getHtmlmodel());
		}
		return map;
	}
	
	@RequestMapping("/getMapModelTree")
	@ResponseBody
	public Map<String,String> getMapModelTree(HttpServletRequest request){
		//0-模板库 1-信息窗口库 2-图层库 3-控件库
		String modeltype=request.getParameter("modeltype");
		Map<String,String> result = new HashMap<String,String>();
	//	[{text: '模板名称',href: '#parent1',nodes: [{text: '子节点 1',href: '#child2',id:'1'},{text: '子节点 2',href: '#child2',id:'2'}]}]
		StringBuffer mapModelData = new StringBuffer("[ {text : '组件名称',nodes : [");
		List<Mapmodelmag> mapmodelmag = mapmodelmagService.searchAll();
		List<Mapmodelmag> modelList = new ArrayList<Mapmodelmag>();
		for (Mapmodelmag mapmodelmag2 : mapmodelmag) {
			String type = mapmodelmag2.getMapmodeltype();
			if(modeltype.equals(type)){
			//if("模板".equals(type)){
				modelList.add(mapmodelmag2);
			}
		}
		for (int i = 0;i<modelList.size();i++) {
			String id = modelList.get(i).getMapmodelid();
			String modelName = modelList.get(i).getMapmodelname();
			String details = modelList.get(i).getMapmodeldes();
			String modelAtrr = modelList.get(i).getAttributedes();
			String modelDes = modelList.get(i).getDatades();
			if(i==modelList.size()-1){
				mapModelData.append("{text:'"+modelName+"',attributes : {url : '"+modelAtrr+"',modelId:'"+id+"',des:'"+details+"'}}");
			}else{
				mapModelData.append("{text:'"+modelName+"',attributes : {url : '"+modelAtrr+"',modelId:'"+id+"',des:'"+details+"'}},");
			}
		}
		mapModelData.append("]}]");
		result.put("result", mapModelData.toString());
		log.info(mapModelData.toString());
		return result;
	}
}
