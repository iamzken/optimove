package com.topcheer.service.mapModelMag;

import java.util.List;
import java.util.Map;

import com.topcheer.model.mapModelMag.Mapmodelmag;

public interface IMapmodelmagService {
	
	public List<Mapmodelmag> searchMapmodelmag(Map searchMap);
	
	public List<Mapmodelmag> getMapmodelmag(String mapmodelid);
	
	public List<Mapmodelmag> searchAll();
	
	public void insert(Mapmodelmag mapmodelmag);
	
	public void update(Mapmodelmag mapmodelmag);
	
	public void delete(String id);
	
	public List<Mapmodelmag> getMapModelJson(String mapmodeltype);

}
