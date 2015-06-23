package com.topcheer.service.mobile.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.mobile.BusinessproductmanagerMapper;
import com.topcheer.model.mobile.Businessproductmanager;
import com.topcheer.service.mobile.IBusinessproductmanagerService;

@Service("businessproductmanagerService")
public class BusinessproductmanagerServiceImpl implements IBusinessproductmanagerService{
	
	@Autowired
	private BusinessproductmanagerMapper businessproductmanagerMapper;

	public void delete(String id) {
		businessproductmanagerMapper.delete(id);
		
	}

	public List<Businessproductmanager> getBusinessproductmanager(String productcode) {
		return businessproductmanagerMapper.getBusinessproductmanager(productcode);
	}

	public void insert(Businessproductmanager businessproductmanager) {
		 businessproductmanagerMapper.insert(businessproductmanager);
	}

	public List<Businessproductmanager> searchAll() {
		return businessproductmanagerMapper.searchAll();
	}

	public List<Businessproductmanager> searchBusinessproductmanager(Map searchMap) {
		return businessproductmanagerMapper.searchBusinessproductmanager(searchMap);
	}

	public void update(Businessproductmanager Businessproductmanager) {
		businessproductmanagerMapper.update(Businessproductmanager);
	}

	public BusinessproductmanagerMapper getBusinessproductmanagerMapper() {
		return businessproductmanagerMapper;
	}

	public void setBusinessproductmanagerMapper(BusinessproductmanagerMapper BusinessproductmanagerMapper) {
		this.businessproductmanagerMapper = BusinessproductmanagerMapper;
	}
	
	
	

}
