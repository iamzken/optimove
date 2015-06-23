package com.topcheer.service.Impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.LookupvaluesMapper;
import com.topcheer.model.Lookupvalues;
import com.topcheer.service.ILookupvaluesService;

@Service("lookupvaluesService")
public class LookupvaluesServiceImpl implements ILookupvaluesService {
	@Autowired
	private LookupvaluesMapper lookupvaluesMapper;
	
	

	

	public List<Lookupvalues> getLookvalues(String lookupCode) {
	
		return lookupvaluesMapper.getLookvalues(lookupCode);
	}

	

	public void insert(Lookupvalues lookupvalues) {
		lookupvaluesMapper.insert(lookupvalues);
		
	}

	public List<Lookupvalues> searchAll() {
		
		return lookupvaluesMapper.searchAll();
	}

	public List<Lookupvalues> searchLookvalues(Map search) {
		
		return lookupvaluesMapper.searchLookvalues(search);
	}

	public void update(Lookupvalues lookupvalues) {
		lookupvaluesMapper.update(lookupvalues);
		
	}
	public LookupvaluesMapper getLookupvaluesMapper() {
		return lookupvaluesMapper;
	}

	public void setLookupvaluesMapper(LookupvaluesMapper lookupvaluesMapper) {
		this.lookupvaluesMapper = lookupvaluesMapper;
	}

	public List<Lookupvalues> getLookvaluesBylookup(Map search){
		return lookupvaluesMapper.getLookvaluesBylookup(search);
	}

	public void delete(Lookupvalues lookupvalues) {
		this.lookupvaluesMapper.delete(lookupvalues);
		
	}

}
