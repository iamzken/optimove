package com.topcheer.service.networkUnicom.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.networkUnicom.NetworkunicomMapper;
import com.topcheer.model.networkUnicom.Networkunicom;
import com.topcheer.service.networkUnicom.INetworkunicomService;

@Service("networkunicomService")
public class NetworkunicomServiceImpl implements INetworkunicomService{
	
	@Autowired
	private NetworkunicomMapper networkunicomMapper;

	public void delete(String id) {
		networkunicomMapper.delete(id);
		
	}

	public List<Networkunicom> getNetworkunicom(String networkunicomid) {
		return networkunicomMapper.getNetworkunicom(networkunicomid);
	}

	public void insert(Networkunicom networkunicom) {
		 networkunicomMapper.insert(networkunicom);
	}

	public List<Networkunicom> searchAll() {
		return networkunicomMapper.searchAll();
	}

	public List<Networkunicom> searchNetworkunicom(Map searchMap) {
		return networkunicomMapper.searchNetworkunicom(searchMap);
	}

	public void update(Networkunicom Networkunicom) {
		networkunicomMapper.update(Networkunicom);
	}

	public NetworkunicomMapper getNetworkunicomMapper() {
		return networkunicomMapper;
	}

	public void setNetworkunicomMapper(NetworkunicomMapper NetworkunicomMapper) {
		this.networkunicomMapper = NetworkunicomMapper;
	}
	
	
	

}
