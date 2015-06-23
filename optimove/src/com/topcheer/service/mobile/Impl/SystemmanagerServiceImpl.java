package com.topcheer.service.mobile.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.mobile.SystemmanagerMapper;
import com.topcheer.model.mobile.Systemmanager;
import com.topcheer.service.mobile.ISystemmanagerService;

@Service("systemmanagerService")
public class SystemmanagerServiceImpl implements ISystemmanagerService{
	
	@Autowired
	private SystemmanagerMapper systemmanagerMapper;

	public void delete(String id) {
		systemmanagerMapper.delete(id);
		
	}

	public List<Systemmanager> getSystemmanager(String syscode) {
		return systemmanagerMapper.getSystemmanager(syscode);
	}

	public void insert(Systemmanager systemmanager) {
		 systemmanagerMapper.insert(systemmanager);
	}

	public List<Systemmanager> searchAll() {
		return systemmanagerMapper.searchAll();
	}

	public List<Systemmanager> searchSystemmanager(Map searchMap) {
		return systemmanagerMapper.searchSystemmanager(searchMap);
	}

	public void update(Systemmanager Systemmanager) {
		systemmanagerMapper.update(Systemmanager);
	}

	public SystemmanagerMapper getSystemmanagerMapper() {
		return systemmanagerMapper;
	}

	public void setSystemmanagerMapper(SystemmanagerMapper SystemmanagerMapper) {
		this.systemmanagerMapper = SystemmanagerMapper;
	}
	
	
	

}
