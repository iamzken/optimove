package com.topcheer.dao.modelAttribute;

import java.util.List;
import java.util.Map;


import com.topcheer.model.modelAttribute.Modelattribute;


public interface ModelattributeMapper {
	
	public List<Modelattribute> searchModelattribute(Map<String,Object> searchMap);
	
	public List<Modelattribute> searchAll();
	
	public List<Modelattribute> getModelattribute(String id);
	
	public void insert(Modelattribute modelattribute);
	
	public void update(Modelattribute modelattribute);
	
	public void delete(String id);
	
	public void deleteByTablename(String tablename);
	
	public void alterTable(Map<String,Object> map);

	public List<Map<String, Object>> searchMapInfo(Map<String, Object> map);

	public List<Map<String, Object>> searchDataByCondition(Map<String, Object> map);

	public void updateCanDisplayAndCanSearchState(Modelattribute modelattribute);

	public List<Modelattribute> searchModelattribute4Display(Map<String, Object> map);

	public List<Modelattribute> searchModelattribute4Search(Map<String, Object> map);
	
}
