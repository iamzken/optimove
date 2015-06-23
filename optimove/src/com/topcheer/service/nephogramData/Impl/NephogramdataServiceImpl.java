package com.topcheer.service.nephogramData.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.nephogramData.NephogramdataMapper;
import com.topcheer.model.nephogramData.Nephogramdata;
import com.topcheer.service.nephogramData.INephogramdataService;

@Service("nephogramdataService")
public class NephogramdataServiceImpl implements INephogramdataService{
	
	@Autowired
	private NephogramdataMapper nephogramdataMapper;

	public void delete(String id) {
		nephogramdataMapper.delete(id);
		
	}

	public List<Nephogramdata> getNephogramdata(String nephogramdataid) {
		return nephogramdataMapper.getNephogramdata(nephogramdataid);
	}

	public void insert(Nephogramdata nephogramdata) {
		 nephogramdataMapper.insert(nephogramdata);
	}

	public List<Nephogramdata> searchAll() {
		return nephogramdataMapper.searchAll();
	}

	public List<Nephogramdata> searchNephogramdata(Map searchMap) {
		return nephogramdataMapper.searchNephogramdata(searchMap);
	}

	public void update(Nephogramdata Nephogramdata) {
		nephogramdataMapper.update(Nephogramdata);
	}

	public NephogramdataMapper getNephogramdataMapper() {
		return nephogramdataMapper;
	}

	public void setNephogramdataMapper(NephogramdataMapper NephogramdataMapper) {
		this.nephogramdataMapper = NephogramdataMapper;
	}

	public void deletebymodelId(String nephogrammodelid) {
		nephogramdataMapper.deletebymodelId(nephogrammodelid);
	}
	
	
	

}
