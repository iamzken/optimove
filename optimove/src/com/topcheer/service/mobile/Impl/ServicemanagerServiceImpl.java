package com.topcheer.service.mobile.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.mobile.ServicemanagerMapper;
import com.topcheer.model.mobile.Servicemanager;
import com.topcheer.service.mobile.IServicemanagerService;

@Service("servicemanagerService")
public class ServicemanagerServiceImpl implements IServicemanagerService{
	
	@Autowired
	private ServicemanagerMapper servicemanagerMapper;

	public void delete(String id) {
		servicemanagerMapper.delete(id);
		
	}

	public List<Servicemanager> getServicemanager(String servicecode) {
		return servicemanagerMapper.getServicemanager(servicecode);
	}

	public void insert(Servicemanager servicemanager) {
		 servicemanagerMapper.insert(servicemanager);
	}

	public List<Servicemanager> searchAll() {
		return servicemanagerMapper.searchAll();
	}

	public List<Servicemanager> searchServicemanager(Map searchMap) {
		return servicemanagerMapper.searchServicemanager(searchMap);
	}

	public void update(Servicemanager Servicemanager) {
		servicemanagerMapper.update(Servicemanager);
	}

	public ServicemanagerMapper getServicemanagerMapper() {
		return servicemanagerMapper;
	}

	public void setServicemanagerMapper(ServicemanagerMapper ServicemanagerMapper) {
		this.servicemanagerMapper = ServicemanagerMapper;
	}
	
	
	

}
