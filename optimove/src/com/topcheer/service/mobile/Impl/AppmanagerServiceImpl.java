package com.topcheer.service.mobile.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.mobile.AppmanagerMapper;
import com.topcheer.model.mobile.Appmanager;
import com.topcheer.service.mobile.IAppmanagerService;

@Service("appmanagerService")
public class AppmanagerServiceImpl implements IAppmanagerService{
	
	@Autowired
	private AppmanagerMapper appmanagerMapper;

	public void delete(String id) {
		appmanagerMapper.delete(id);
		
	}

	public List<Appmanager> getAppmanager(String appcode) {
		return appmanagerMapper.getAppmanager(appcode);
	}

	public void insert(Appmanager appmanager) {
		 appmanagerMapper.insert(appmanager);
	}

	public List<Appmanager> searchAll() {
		return appmanagerMapper.searchAll();
	}

	public List<Appmanager> searchAppmanager(Map searchMap) {
		return appmanagerMapper.searchAppmanager(searchMap);
	}

	public void update(Appmanager Appmanager) {
		appmanagerMapper.update(Appmanager);
	}

	public AppmanagerMapper getAppmanagerMapper() {
		return appmanagerMapper;
	}

	public void setAppmanagerMapper(AppmanagerMapper AppmanagerMapper) {
		this.appmanagerMapper = AppmanagerMapper;
	}
	
	
	

}
