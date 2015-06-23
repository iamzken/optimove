package com.topcheer.service.mobile.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.mobile.TranstemplatemanagerMapper;
import com.topcheer.model.mobile.Transtemplatemanager;
import com.topcheer.service.mobile.ITranstemplatemanagerService;

@Service("transtemplatemanagerService")
public class TranstemplatemanagerServiceImpl implements ITranstemplatemanagerService{
	
	@Autowired
	private TranstemplatemanagerMapper transtemplatemanagerMapper;

	public void delete(String id) {
		transtemplatemanagerMapper.delete(id);
		
	}

	public List<Transtemplatemanager> getTranstemplatemanager(String templatecode) {
		return transtemplatemanagerMapper.getTranstemplatemanager(templatecode);
	}

	public void insert(Transtemplatemanager transtemplatemanager) {
		 transtemplatemanagerMapper.insert(transtemplatemanager);
	}

	public List<Transtemplatemanager> searchAll() {
		return transtemplatemanagerMapper.searchAll();
	}

	public List<Transtemplatemanager> searchTranstemplatemanager(Map searchMap) {
		return transtemplatemanagerMapper.searchTranstemplatemanager(searchMap);
	}

	public void update(Transtemplatemanager Transtemplatemanager) {
		transtemplatemanagerMapper.update(Transtemplatemanager);
	}

	public TranstemplatemanagerMapper getTranstemplatemanagerMapper() {
		return transtemplatemanagerMapper;
	}

	public void setTranstemplatemanagerMapper(TranstemplatemanagerMapper TranstemplatemanagerMapper) {
		this.transtemplatemanagerMapper = TranstemplatemanagerMapper;
	}
	
	
	

}
