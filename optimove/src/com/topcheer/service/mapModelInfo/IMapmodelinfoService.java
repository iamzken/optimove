package com.topcheer.service.mapModelInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.topcheer.model.mapModelInfo.Mapmodelinfo;

public interface IMapmodelinfoService {
	
	public List<Mapmodelinfo> searchMapmodelinfo(Map<String,Object> searchMap);
	
	public List<Mapmodelinfo> getMapmodelinfo(String modelid);
	
	public List<Mapmodelinfo> searchAll();
	
	public void insert(Mapmodelinfo mapmodelinfo);
	
	public void update(Mapmodelinfo mapmodelinfo);
	
	public void delete(String id);

	public void createTable(Mapmodelinfo mapmodelinfo);
	
	public void alterTable(Map<String,Object> map);
	
	public void insertData(Map<String,Object> map);
	
	public void dropTable(Map<String,Object> map);
	
	public List<Map<String,Object>> getModelData(Map<String,Object> map);
	
	public List<Map<String,Object>> getTableDataList(Map<String,Object> queryMap);
	
	public List<Map<String,Object>> getSuperModelData(Map<String,Object> map);
	
	public List<Map<String,Object>> searchRegionModelData(Map<String,Object> map);

	public void convertOracleStructTypeToJavaType(Map<String, Object> map) throws SQLException;
}