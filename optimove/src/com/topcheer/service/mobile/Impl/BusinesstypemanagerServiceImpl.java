package com.topcheer.service.mobile.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.mobile.BusinesstypemanagerMapper;
import com.topcheer.model.mobile.Businesstypemanager;
import com.topcheer.service.mobile.IBusinesstypemanagerService;

@Service("businesstypemanagerService")
public class BusinesstypemanagerServiceImpl implements IBusinesstypemanagerService{
	
	@Autowired
	private BusinesstypemanagerMapper businesstypemanagerMapper;

	public void delete(String id) {
		businesstypemanagerMapper.delete(id);
		
	}

	public List<Businesstypemanager> getBusinesstypemanager(String typecode) {
		return businesstypemanagerMapper.getBusinesstypemanager(typecode);
	}

	public void insert(Businesstypemanager businesstypemanager) {
		 businesstypemanagerMapper.insert(businesstypemanager);
	}

	public List<Businesstypemanager> searchAll() {
		return businesstypemanagerMapper.searchAll();
	}

	public List<Businesstypemanager> searchBusinesstypemanager(Map searchMap) {
		return businesstypemanagerMapper.searchBusinesstypemanager(searchMap);
	}

	public void update(Businesstypemanager Businesstypemanager) {
		businesstypemanagerMapper.update(Businesstypemanager);
	}

	public BusinesstypemanagerMapper getBusinesstypemanagerMapper() {
		return businesstypemanagerMapper;
	}

	public void setBusinesstypemanagerMapper(BusinesstypemanagerMapper BusinesstypemanagerMapper) {
		this.businesstypemanagerMapper = BusinesstypemanagerMapper;
	}
	
	
	

}
