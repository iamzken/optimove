package com.topcheer.service.modelAttribute.Impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.modelAttribute.ModelattributeMapper;
import com.topcheer.model.modelAttribute.Modelattribute;
import com.topcheer.service.modelAttribute.IModelattributeService;

@Service("modelattributeService")
public class ModelattributeServiceImpl implements IModelattributeService{
	
	@Autowired
	private ModelattributeMapper modelattributeMapper;
	
	public List<Map<String, Object>>  searchDataByCondition(Map<String, Object> map) {
		return modelattributeMapper.searchDataByCondition(map);
	}

	public List<Map<String, Object>> searchMapInfo(Map<String, Object> map) {
		return modelattributeMapper.searchMapInfo(map);
	}

	public void delete(String id) {
		modelattributeMapper.delete(id);
	}

	public List<Modelattribute> getModelattribute(String id) {
		return modelattributeMapper.getModelattribute(id);
	}

	public void insert(Modelattribute modelattribute) {
		 modelattributeMapper.insert(modelattribute);
	}

	public List<Modelattribute> searchAll() {
		return modelattributeMapper.searchAll();
	}

	public List<Modelattribute> searchModelattribute(Map<String,Object> searchMap) {
		return modelattributeMapper.searchModelattribute(searchMap);
	}

	public void update(Modelattribute Modelattribute) {
		modelattributeMapper.update(Modelattribute);
		//修改对应的字段是否能被查询和被显示
		modelattributeMapper.updateCanDisplayAndCanSearchState(Modelattribute);
	}

	public ModelattributeMapper getModelattributeMapper() {
		return modelattributeMapper;
	}

	public void setModelattributeMapper(ModelattributeMapper ModelattributeMapper) {
		this.modelattributeMapper = ModelattributeMapper;
	}
	
	public void deleteByTablename(String tablename) {
		modelattributeMapper.deleteByTablename(tablename);
	}
	
	public void alterTable(Map<String,Object> map) {
		modelattributeMapper.alterTable(map);
	}

	public String getQueryCondition(Map<String, Object> map) {
		Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
		String condition = "";
		while (iter.hasNext()) {
			Map.Entry<String,Object> entry = iter.next();
		    Object key = entry.getKey();
		    if("tablename".equalsIgnoreCase((String) key) || "page".equalsIgnoreCase((String) key) || "rows".equalsIgnoreCase((String) key)){
		    	continue;
		    }
		    String[] val = (String[]) entry.getValue();
		    if(!val[0].toString().equals("")){
		    	condition += key +" like '%"+val[0].toString()+"%' and ";
		    }
		}
		if(!"".equals(condition)){
			condition = " where "+condition.substring(0, condition.lastIndexOf("and"));
		}
		return condition;
	}

	public List<Modelattribute> searchModelattribute4Display(Map<String, Object> map) {
		return modelattributeMapper.searchModelattribute4Display(map);
	}

	public List<Modelattribute> searchModelattribute4Search(Map<String, Object> map) {
		return modelattributeMapper.searchModelattribute4Search(map);
	}

}
