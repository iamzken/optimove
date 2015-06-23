package com.topcheer.dao.mapModelInfo;

import java.util.List;
import java.util.Map;


import com.topcheer.model.mapModelInfo.Mapmodelinfo;


public interface MapmodelinfoMapper {
	
	public List<Mapmodelinfo> searchMapmodelinfo(Map<String,Object> searchMap);
	
	public List<Mapmodelinfo> searchAll();
	
	public List<Mapmodelinfo> getMapmodelinfo(String modelid);
	
	public void insert(Mapmodelinfo mapmodelinfo);
	
	public void update(Mapmodelinfo mapmodelinfo);
	
	public void delete(String modelid);
	
	public void createTable(Mapmodelinfo mapmodelinfo);
	
	public void alterTable(Map<String,Object> map);
	
	public void insertData(Map<String,Object> map);
	
	public void dropTable(Map<String,Object> map);
	
	public List<Map<String,Object>> getTableDataList(Map<String,Object> queryMap);
	
	public List<Map<String,Object>> getModelAttribute(Map<String,Object> map);
}
