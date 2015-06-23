package com.topcheer.service.specialData.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.specialData.SpecialdataMapper;
import com.topcheer.model.specialData.Specialdata;
import com.topcheer.service.specialData.ISpecialdataService;

@Service("specialdataService")
public class SpecialdataServiceImpl implements ISpecialdataService{
	
	@Autowired
	private SpecialdataMapper specialdataMapper;

	public void delete(String id) {
		specialdataMapper.delete(id);
		
	}

	public List<Specialdata> getSpecialdata(String specialdataid) {
		return specialdataMapper.getSpecialdata(specialdataid);
	}

	public void insert(Specialdata specialdata) {
		 specialdataMapper.insert(specialdata);
	}

	public List<Specialdata> searchAll() {
		return specialdataMapper.searchAll();
	}

	public List<Specialdata> searchSpecialdata(Map searchMap) {
		return specialdataMapper.searchSpecialdata(searchMap);
	}

	public void update(Specialdata Specialdata) {
		specialdataMapper.update(Specialdata);
	}

	public SpecialdataMapper getSpecialdataMapper() {
		return specialdataMapper;
	}

	public void setSpecialdataMapper(SpecialdataMapper SpecialdataMapper) {
		this.specialdataMapper = SpecialdataMapper;
	}
	
	
	

}
