package com.topcheer.service.mapModelMag.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.mapModelMag.MapmodelmagMapper;
import com.topcheer.model.mapModelMag.Mapmodelmag;
import com.topcheer.service.mapModelMag.IMapmodelmagService;

@Service("mapmodelmagService")
public class MapmodelmagServiceImpl implements IMapmodelmagService{
	
	@Autowired
	private MapmodelmagMapper mapmodelmagMapper;

	public void delete(String id) {
		mapmodelmagMapper.delete(id);
		
	}

	public List<Mapmodelmag> getMapmodelmag(String mapmodelid) {
		return mapmodelmagMapper.getMapmodelmag(mapmodelid);
	}

	public void insert(Mapmodelmag mapmodelmag) {
		 mapmodelmagMapper.insert(mapmodelmag);
	}

	public List<Mapmodelmag> searchAll() {
		return mapmodelmagMapper.searchAll();
	}

	public List<Mapmodelmag> searchMapmodelmag(Map searchMap) {
		return mapmodelmagMapper.searchMapmodelmag(searchMap);
	}

	public void update(Mapmodelmag Mapmodelmag) {
		mapmodelmagMapper.update(Mapmodelmag);
	}

	public MapmodelmagMapper getMapmodelmagMapper() {
		return mapmodelmagMapper;
	}

	public void setMapmodelmagMapper(MapmodelmagMapper MapmodelmagMapper) {
		this.mapmodelmagMapper = MapmodelmagMapper;
	}
	
	public List<Mapmodelmag> getMapModelJson(String mapmodeltype) {
		return mapmodelmagMapper.getMapModelJson(mapmodeltype);
	}
	

}
