package com.topcheer.service.mobile.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.mobile.TemplateattributeMapper;
import com.topcheer.model.mobile.Templateattribute;
import com.topcheer.service.mobile.ITemplateattributeService;

@Service("templateattributeService")
public class TemplateattributeServiceImpl implements ITemplateattributeService{
	
	@Autowired
	private TemplateattributeMapper templateattributeMapper;

	public void delete(String id) {
		templateattributeMapper.delete(id);
		
	}

	public List<Templateattribute> getTemplateattribute(String id) {
		return templateattributeMapper.getTemplateattribute(id);
	}

	public void insert(Templateattribute templateattribute) {
		 templateattributeMapper.insert(templateattribute);
	}

	public List<Templateattribute> searchAll() {
		return templateattributeMapper.searchAll();
	}

	public List<Templateattribute> searchTemplateattribute(Map searchMap) {
		return templateattributeMapper.searchTemplateattribute(searchMap);
	}

	public void update(Templateattribute Templateattribute) {
		templateattributeMapper.update(Templateattribute);
	}

	public TemplateattributeMapper getTemplateattributeMapper() {
		return templateattributeMapper;
	}

	public void setTemplateattributeMapper(TemplateattributeMapper TemplateattributeMapper) {
		this.templateattributeMapper = TemplateattributeMapper;
	}
	
	
	

}
