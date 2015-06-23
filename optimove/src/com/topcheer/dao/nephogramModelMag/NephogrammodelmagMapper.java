package com.topcheer.dao.nephogramModelMag;

import java.util.List;
import java.util.Map;


import com.topcheer.model.nephogramModelMag.Nephogrammodelmag;


public interface NephogrammodelmagMapper {
	
	public List<Nephogrammodelmag> searchNephogrammodelmag(Map searchMap);
	
	public List<Nephogrammodelmag> searchAll();
	
	public List<Nephogrammodelmag> getNephogrammodelmag(String nephogrammodelid);
	
	public void insert(Nephogrammodelmag nephogrammodelmag);
	
	public void update(Nephogrammodelmag nephogrammodelmag);
	
	public void delete(String nephogrammodelid);
	
}
