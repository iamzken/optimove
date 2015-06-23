package com.topcheer.service.nephogramModelMag;

import java.util.List;
import java.util.Map;

import com.topcheer.model.nephogramModelMag.Nephogrammodelmag;

public interface INephogrammodelmagService {
	
	public List<Nephogrammodelmag> searchNephogrammodelmag(Map searchMap);
	
	public List<Nephogrammodelmag> getNephogrammodelmag(String nephogrammodelid);
	
	public List<Nephogrammodelmag> searchAll();
	
	public void insert(Nephogrammodelmag nephogrammodelmag);
	
	public void update(Nephogrammodelmag nephogrammodelmag);
	
	public void delete(String id);

}
