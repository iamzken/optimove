package com.topcheer.service.applicationMag.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.applicationMag.ApplicationmagMapper;
import com.topcheer.model.applicationMag.Applicationmag;
import com.topcheer.service.applicationMag.IApplicationmagService;

@Service("applicationmagService")
public class ApplicationmagServiceImpl implements IApplicationmagService{
	
	@Autowired
	private ApplicationmagMapper applicationmagMapper;

	public void delete(String id) {
		applicationmagMapper.delete(id);
		
	}

	public List<Applicationmag> getApplicationmag(String applicationid) {
		return applicationmagMapper.getApplicationmag(applicationid);
	}

	public void insert(Applicationmag applicationmag) {
		 applicationmagMapper.insert(applicationmag);
	}

	public List<Applicationmag> searchAll() {
		return applicationmagMapper.searchAll();
	}

	public List<Applicationmag> searchApplicationmag(Map searchMap) {
		return applicationmagMapper.searchApplicationmag(searchMap);
	}

	public void update(Applicationmag Applicationmag) {
		applicationmagMapper.update(Applicationmag);
	}

	public ApplicationmagMapper getApplicationmagMapper() {
		return applicationmagMapper;
	}

	public void setApplicationmagMapper(ApplicationmagMapper ApplicationmagMapper) {
		this.applicationmagMapper = ApplicationmagMapper;
	}
	
	
	

}
