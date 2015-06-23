package com.topcheer.service.nephogramData;

import java.util.List;
import java.util.Map;

import com.topcheer.model.nephogramData.Nephogramdata;

public interface INephogramdataService {
	
	public List<Nephogramdata> searchNephogramdata(Map searchMap);
	
	public List<Nephogramdata> getNephogramdata(String nephogramdataid);
	
	public List<Nephogramdata> searchAll();
	
	public void insert(Nephogramdata nephogramdata);
	
	public void update(Nephogramdata nephogramdata);
	
	public void delete(String id);
	
	public void deletebymodelId(String nephogrammodelid);

}
