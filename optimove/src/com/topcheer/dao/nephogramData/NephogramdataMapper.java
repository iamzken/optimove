package com.topcheer.dao.nephogramData;

import java.util.List;
import java.util.Map;


import com.topcheer.model.nephogramData.Nephogramdata;


public interface NephogramdataMapper {
	
	public List<Nephogramdata> searchNephogramdata(Map searchMap);
	
	public List<Nephogramdata> searchAll();
	
	public List<Nephogramdata> getNephogramdata(String nephogramdataid);
	
	public void insert(Nephogramdata nephogramdata);
	
	public void update(Nephogramdata nephogramdata);
	
	public void delete(String nephogramdataid);
	
	public void deletebymodelId(String nephogrammodelid);
}
