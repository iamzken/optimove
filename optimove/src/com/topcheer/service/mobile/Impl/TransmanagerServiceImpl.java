package com.topcheer.service.mobile.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.mobile.TransmanagerMapper;
import com.topcheer.model.mobile.Transmanager;
import com.topcheer.service.mobile.ITransmanagerService;

@Service("transmanagerService")
public class TransmanagerServiceImpl implements ITransmanagerService{
	
	@Autowired
	private TransmanagerMapper transmanagerMapper;

	public void delete(String id) {
		transmanagerMapper.delete(id);
		
	}

	public List<Transmanager> getTransmanager(String transcode) {
		return transmanagerMapper.getTransmanager(transcode);
	}

	public void insert(Transmanager transmanager) {
		 transmanagerMapper.insert(transmanager);
	}

	public List<Transmanager> searchAll() {
		return transmanagerMapper.searchAll();
	}

	public List<Transmanager> searchTransmanager(Map searchMap) {
		return transmanagerMapper.searchTransmanager(searchMap);
	}

	public void update(Transmanager Transmanager) {
		transmanagerMapper.update(Transmanager);
	}

	public TransmanagerMapper getTransmanagerMapper() {
		return transmanagerMapper;
	}

	public void setTransmanagerMapper(TransmanagerMapper TransmanagerMapper) {
		this.transmanagerMapper = TransmanagerMapper;
	}
	
	
	

}
