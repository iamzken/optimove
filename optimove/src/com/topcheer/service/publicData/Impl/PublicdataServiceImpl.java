package com.topcheer.service.publicData.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.publicData.PublicdataMapper;
import com.topcheer.model.publicData.Publicdata;
import com.topcheer.service.publicData.IPublicdataService;

@Service("publicdataService")
public class PublicdataServiceImpl implements IPublicdataService{
	
	@Autowired
	private PublicdataMapper publicdataMapper;

	public void delete(String id) {
		publicdataMapper.delete(id);
		
	}

	public List<Publicdata> getPublicdata(String publicdataid) {
		return publicdataMapper.getPublicdata(publicdataid);
	}

	public void insert(Publicdata publicdata) {
		 publicdataMapper.insert(publicdata);
	}

	public List<Publicdata> searchAll() {
		return publicdataMapper.searchAll();
	}

	public List<Publicdata> searchPublicdata(Map searchMap) {
		return publicdataMapper.searchPublicdata(searchMap);
	}

	public void update(Publicdata Publicdata) {
		publicdataMapper.update(Publicdata);
	}

	public PublicdataMapper getPublicdataMapper() {
		return publicdataMapper;
	}

	public void setPublicdataMapper(PublicdataMapper PublicdataMapper) {
		this.publicdataMapper = PublicdataMapper;
	}
	
	
	

}
