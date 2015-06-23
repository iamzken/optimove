package com.topcheer.dao.mapModelInfo;

import java.util.List;
import java.util.Map;

public interface MapModelDataMapper {
	
	public List<Map<String,Object>> getModelData(Map<String,Object> map);
	
	public List<Map<String,Object>> getSuperModelData(Map<String,Object> map);
	
	public List<Map<String,Object>> searchRegionModelData(Map<String,Object> map);
}
