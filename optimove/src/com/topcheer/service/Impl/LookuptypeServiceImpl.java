package com.topcheer.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.LookuptypeMapper;
import com.topcheer.model.Lookuptype;
import com.topcheer.service.ILookuptypeService;;
@Service("lookuptypeService")
public class LookuptypeServiceImpl implements ILookuptypeService {
	@Autowired
	private LookuptypeMapper lookuptypeMapper;


	//	private RetmsgMapper retmsgMapper;	
	public void delete(String lookupType) {
		lookuptypeMapper.delete(lookupType);		
		
	}

	public List<Lookuptype> getLookuptype(String lookupType) {
		
		return lookuptypeMapper.getLookuptype(lookupType);
	}
	public void insert(Lookuptype lookuptype) {
		
		lookuptypeMapper.insert(lookuptype);
	}

	public List<Lookuptype> searchAll() {		
		return lookuptypeMapper.searchAll();
	}

	public List<Lookuptype> searchLookuptype(Map searchMap) {
		System.out.println("searchLookuptype impl");
		
		return lookuptypeMapper.searchLookuptype(searchMap);
	}

	public void update(Lookuptype lookuptype) {
		lookuptypeMapper.update(lookuptype);
		
	}
	public LookuptypeMapper getLookuptypeMapper() {
		return lookuptypeMapper;
	}

	public void setLookuptypeMapper(LookuptypeMapper lookuptypeMapper) {
		this.lookuptypeMapper = lookuptypeMapper;
	}

}
