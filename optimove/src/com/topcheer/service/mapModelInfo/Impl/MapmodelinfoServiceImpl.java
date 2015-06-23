package com.topcheer.service.mapModelInfo.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.sql.Datum;
import oracle.sql.STRUCT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.mapModelInfo.MapModelDataMapper;
import com.topcheer.dao.mapModelInfo.MapmodelinfoMapper;
import com.topcheer.model.mapModelInfo.Mapmodelinfo;
import com.topcheer.service.mapModelInfo.IMapmodelinfoService;

@Service("mapmodelinfoService")
public class MapmodelinfoServiceImpl implements IMapmodelinfoService{
	
	@Autowired
	private MapmodelinfoMapper mapmodelinfoMapper;
	@Autowired
	private MapModelDataMapper mapModelDataMapper;

	public void delete(String id) {
		mapmodelinfoMapper.delete(id);
	}

	public List<Mapmodelinfo> getMapmodelinfo(String modelid) {
		return mapmodelinfoMapper.getMapmodelinfo(modelid);
	}

	public void insert(Mapmodelinfo mapmodelinfo) {
		 mapmodelinfoMapper.insert(mapmodelinfo);
	}

	public List<Map<String,Object>> getTableDataList(Map<String,Object> queryMap) {
		return mapmodelinfoMapper.getTableDataList(queryMap);
	}
	public List<Mapmodelinfo> searchAll() {
		return mapmodelinfoMapper.searchAll();
	}

	public List<Mapmodelinfo> searchMapmodelinfo(Map<String,Object> searchMap) {
		return mapmodelinfoMapper.searchMapmodelinfo(searchMap);
	}

	public void update(Mapmodelinfo Mapmodelinfo) {
		mapmodelinfoMapper.update(Mapmodelinfo);
	}

	public MapmodelinfoMapper getMapmodelinfoMapper() {
		return mapmodelinfoMapper;
	}

	public void setMapmodelinfoMapper(MapmodelinfoMapper MapmodelinfoMapper) {
		this.mapmodelinfoMapper = MapmodelinfoMapper;
	}
	
	public void createTable(Mapmodelinfo mapmodelinfo) {
		mapmodelinfoMapper.createTable(mapmodelinfo);
	}
	
	public void alterTable(Map<String,Object> map) {
		mapmodelinfoMapper.alterTable(map);
	}
	
	public void insertData(Map<String,Object> map) {
		mapmodelinfoMapper.insertData(map);
	}
	
	public void dropTable(Map<String,Object> map) {
		mapmodelinfoMapper.dropTable(map);
	}

	public List<Map<String,Object>> getModelData(Map<String,Object> map) {
		return mapModelDataMapper.getModelData(map);
	}

	public List<Map<String, Object>> getSuperModelData(Map<String, Object> map) {
		return mapModelDataMapper.getSuperModelData(map);
	}

	public List<Map<String, Object>> searchRegionModelData(Map<String, Object> map) {
		return mapModelDataMapper.searchRegionModelData(map);
	}

	@SuppressWarnings("unchecked")
	public void convertOracleStructTypeToJavaType(Map<String, Object> map) throws SQLException {
		
		ArrayList<Object> dataList = (ArrayList<Object>) map.get("dataList");
		for(int i=0;i<dataList.size();i++){
			HashMap<String,Object> data = (HashMap<String,Object>)dataList.get(i);
			STRUCT location = (STRUCT)data.get("LOCATION");
			Object[] attributes = location.getAttributes();
			STRUCT struct = (STRUCT) attributes[2];
			Datum[] oracleAttributes = struct.getOracleAttributes();
			String longitude = oracleAttributes[0].toJdbc().toString();
			String latitude = oracleAttributes[1].toJdbc().toString();
			((HashMap<String, Object>)((ArrayList<Object>)map.get("dataList")).get(i)).remove("LOCATION");
			((HashMap<String, Object>)((ArrayList<Object>)map.get("dataList")).get(i)).put("LONGITUDE", longitude);
			((HashMap<String, Object>)((ArrayList<Object>)map.get("dataList")).get(i)).put("LATITUDE", latitude);
		}
		
	}
	
}
