package com.topcheer.service.developerKeyMag.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.developerKeyMag.DeveloperkeymagMapper;
import com.topcheer.model.developerKeyMag.Developerkeymag;
import com.topcheer.service.developerKeyMag.IDeveloperkeymagService;

@Service("developerkeymagService")
public class DeveloperkeymagServiceImpl implements IDeveloperkeymagService{
	
	@Autowired
	private DeveloperkeymagMapper developerkeymagMapper;

	public void delete(String id) {
		developerkeymagMapper.delete(id);
		
	}

	public List<Developerkeymag> getDeveloperkeymag(String keyid) {
		return developerkeymagMapper.getDeveloperkeymag(keyid);
	}

	public void insert(Developerkeymag developerkeymag) {
		 developerkeymagMapper.insert(developerkeymag);
	}

	public List<Developerkeymag> searchAll() {
		return developerkeymagMapper.searchAll();
	}

	public List<Developerkeymag> searchDeveloperkeymag(Map searchMap) {
		return developerkeymagMapper.searchDeveloperkeymag(searchMap);
	}

	public void update(Developerkeymag Developerkeymag) {
		developerkeymagMapper.update(Developerkeymag);
	}

	public DeveloperkeymagMapper getDeveloperkeymagMapper() {
		return developerkeymagMapper;
	}

	public void setDeveloperkeymagMapper(DeveloperkeymagMapper DeveloperkeymagMapper) {
		this.developerkeymagMapper = DeveloperkeymagMapper;
	}
	
	
	

}
