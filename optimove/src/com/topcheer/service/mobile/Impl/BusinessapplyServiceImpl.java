package com.topcheer.service.mobile.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.mobile.BusinessapplyMapper;
import com.topcheer.model.mobile.Businessapply;
import com.topcheer.service.mobile.IBusinessapplyService;

@Service("businessapplyService")
public class BusinessapplyServiceImpl implements IBusinessapplyService{
	
	@Autowired
	private BusinessapplyMapper businessapplyMapper;

	public void delete(String id) {
		businessapplyMapper.delete(id);
		
	}

	public List<Businessapply> getBusinessapply(String businessid) {
		return businessapplyMapper.getBusinessapply(businessid);
	}

	public void insert(Businessapply businessapply) {
		 businessapplyMapper.insert(businessapply);
	}

	public List<Businessapply> searchAll() {
		return businessapplyMapper.searchAll();
	}

	public List<Businessapply> searchBusinessapply(Map searchMap) {
		return businessapplyMapper.searchBusinessapply(searchMap);
	}

	public void update(Businessapply Businessapply) {
		businessapplyMapper.update(Businessapply);
	}

	public BusinessapplyMapper getBusinessapplyMapper() {
		return businessapplyMapper;
	}

	public void setBusinessapplyMapper(BusinessapplyMapper BusinessapplyMapper) {
		this.businessapplyMapper = BusinessapplyMapper;
	}
	
	
	

}
