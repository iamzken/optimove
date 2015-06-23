package com.topcheer.service.grabData.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.grabData.GrabdataMapper;
import com.topcheer.model.grabData.Grabdata;
import com.topcheer.service.grabData.IGrabdataService;

@Service("grabdataService")
public class GrabdataServiceImpl implements IGrabdataService{
	
	@Autowired
	private GrabdataMapper grabdataMapper;

	public void delete(String id) {
		grabdataMapper.delete(id);
		
	}

	public List<Grabdata> getGrabdata(String grabdataid) {
		return grabdataMapper.getGrabdata(grabdataid);
	}

	public void insert(Grabdata grabdata) {
		 grabdataMapper.insert(grabdata);
	}

	public List<Grabdata> searchAll() {
		return grabdataMapper.searchAll();
	}

	public List<Grabdata> searchGrabdata(Map searchMap) {
		return grabdataMapper.searchGrabdata(searchMap);
	}

	public void update(Grabdata Grabdata) {
		grabdataMapper.update(Grabdata);
	}

	public GrabdataMapper getGrabdataMapper() {
		return grabdataMapper;
	}

	public void setGrabdataMapper(GrabdataMapper GrabdataMapper) {
		this.grabdataMapper = GrabdataMapper;
	}
	
	
	

}
