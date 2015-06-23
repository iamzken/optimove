package com.topcheer.service.mobile.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.mobile.RecognitemplatemanagerMapper;
import com.topcheer.model.mobile.Recognitemplatemanager;
import com.topcheer.service.mobile.IRecognitemplatemanagerService;

@Service("recognitemplatemanagerService")
public class RecognitemplatemanagerServiceImpl implements IRecognitemplatemanagerService{
	
	@Autowired
	private RecognitemplatemanagerMapper recognitemplatemanagerMapper;

	public void delete(String id) {
		recognitemplatemanagerMapper.delete(id);
		
	}

	public List<Recognitemplatemanager> getRecognitemplatemanager(String templatecode) {
		return recognitemplatemanagerMapper.getRecognitemplatemanager(templatecode);
	}

	public void insert(Recognitemplatemanager recognitemplatemanager) {
		 recognitemplatemanagerMapper.insert(recognitemplatemanager);
	}

	public List<Recognitemplatemanager> searchAll() {
		return recognitemplatemanagerMapper.searchAll();
	}

	public List<Recognitemplatemanager> searchRecognitemplatemanager(Map searchMap) {
		return recognitemplatemanagerMapper.searchRecognitemplatemanager(searchMap);
	}

	public void update(Recognitemplatemanager Recognitemplatemanager) {
		recognitemplatemanagerMapper.update(Recognitemplatemanager);
	}

	public RecognitemplatemanagerMapper getRecognitemplatemanagerMapper() {
		return recognitemplatemanagerMapper;
	}

	public void setRecognitemplatemanagerMapper(RecognitemplatemanagerMapper RecognitemplatemanagerMapper) {
		this.recognitemplatemanagerMapper = RecognitemplatemanagerMapper;
	}
	
	
	

}
