package com.topcheer.service;

import java.util.List;
import java.util.Map;

import com.topcheer.model.Constant;

public interface ICommService {
	
	public String  getConstantDisplay(String key,String value);
	public List<Constant> searchConstant(String key);
	public List<Constant> getConstants(String tableName,String value,String display,String searchStr);
	public List<Map> searchTable(Map searchMap);
	public List<Map> searchSql(String paramSql);
	
}
