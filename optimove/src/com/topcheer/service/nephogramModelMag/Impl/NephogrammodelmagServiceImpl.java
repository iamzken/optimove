package com.topcheer.service.nephogramModelMag.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.nephogramModelMag.NephogrammodelmagMapper;
import com.topcheer.model.nephogramModelMag.Nephogrammodelmag;
import com.topcheer.service.nephogramModelMag.INephogrammodelmagService;

@Service("nephogrammodelmagService")
public class NephogrammodelmagServiceImpl implements INephogrammodelmagService{
	
	@Autowired
	private NephogrammodelmagMapper nephogrammodelmagMapper;

	public void delete(String id) {
		nephogrammodelmagMapper.delete(id);
		
	}

	public List<Nephogrammodelmag> getNephogrammodelmag(String nephogrammodelid) {
		return nephogrammodelmagMapper.getNephogrammodelmag(nephogrammodelid);
	}

	public void insert(Nephogrammodelmag nephogrammodelmag) {
		 nephogrammodelmagMapper.insert(nephogrammodelmag);
	}

	public List<Nephogrammodelmag> searchAll() {
		return nephogrammodelmagMapper.searchAll();
	}

	public List<Nephogrammodelmag> searchNephogrammodelmag(Map searchMap) {
		return nephogrammodelmagMapper.searchNephogrammodelmag(searchMap);
	}

	public void update(Nephogrammodelmag Nephogrammodelmag) {
		nephogrammodelmagMapper.update(Nephogrammodelmag);
	}

	public NephogrammodelmagMapper getNephogrammodelmagMapper() {
		return nephogrammodelmagMapper;
	}

	public void setNephogrammodelmagMapper(NephogrammodelmagMapper NephogrammodelmagMapper) {
		this.nephogrammodelmagMapper = NephogrammodelmagMapper;
	}
	
	
	

}
