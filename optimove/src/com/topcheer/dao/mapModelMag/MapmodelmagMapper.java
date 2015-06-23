package com.topcheer.dao.mapModelMag;

import java.util.List;
import java.util.Map;


import com.topcheer.model.mapModelMag.Mapmodelmag;


public interface MapmodelmagMapper {
	
	public List<Mapmodelmag> searchMapmodelmag(Map searchMap);
	
	public List<Mapmodelmag> searchAll();
	
	public List<Mapmodelmag> getMapmodelmag(String mapmodelid);
	
	public void insert(Mapmodelmag mapmodelmag);
	
	public void update(Mapmodelmag mapmodelmag);
	
	public void delete(String mapmodelid);
	
	public List<Mapmodelmag> getMapModelJson(String mapmodeltype);
	
}
