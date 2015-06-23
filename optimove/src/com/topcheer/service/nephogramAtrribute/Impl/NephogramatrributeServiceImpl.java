package com.topcheer.service.nephogramAtrribute.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.nephogramAtrribute.NephogramatrributeMapper;
import com.topcheer.model.nephogramAtrribute.Nephogramatrribute;
import com.topcheer.service.nephogramAtrribute.INephogramatrributeService;

@Service("nephogramatrributeService")
public class NephogramatrributeServiceImpl implements INephogramatrributeService{
	
	@Autowired
	private NephogramatrributeMapper nephogramatrributeMapper;

	public void delete(String id) {
		nephogramatrributeMapper.delete(id);
		
	}

	public List<Nephogramatrribute> getNephogramatrribute(String nephogramattrid) {
		return nephogramatrributeMapper.getNephogramatrribute(nephogramattrid);
	}

	public void insert(Nephogramatrribute nephogramatrribute) {
		 nephogramatrributeMapper.insert(nephogramatrribute);
	}

	public List<Nephogramatrribute> searchAll() {
		return nephogramatrributeMapper.searchAll();
	}

	public List<Nephogramatrribute> searchNephogramatrribute(Map searchMap) {
		return nephogramatrributeMapper.searchNephogramatrribute(searchMap);
	}

	public void update(Nephogramatrribute Nephogramatrribute) {
		nephogramatrributeMapper.update(Nephogramatrribute);
	}

	public NephogramatrributeMapper getNephogramatrributeMapper() {
		return nephogramatrributeMapper;
	}

	public void setNephogramatrributeMapper(NephogramatrributeMapper NephogramatrributeMapper) {
		this.nephogramatrributeMapper = NephogramatrributeMapper;
	}

	public void deletebynephogrammodelid(String nephogrammodelid) {
		nephogramatrributeMapper.deletebynephogrammodelid(nephogrammodelid);
	}
	
	
	

}
