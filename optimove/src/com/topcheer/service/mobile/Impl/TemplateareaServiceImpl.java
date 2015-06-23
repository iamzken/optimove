package com.topcheer.service.mobile.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.mobile.TemplateareaMapper;
import com.topcheer.model.mobile.Templatearea;
import com.topcheer.service.mobile.ITemplateareaService;

@Service("templateareaService")
public class TemplateareaServiceImpl implements ITemplateareaService{
	
	@Autowired
	private TemplateareaMapper templateareaMapper;

	public void delete(String id) {
		templateareaMapper.delete(id);
		
	}

	public List<Templatearea> getTemplatearea(String id) {
		return templateareaMapper.getTemplatearea(id);
	}

	public void insert(Templatearea templatearea) {
		 templateareaMapper.insert(templatearea);
	}

	public List<Templatearea> searchAll() {
		return templateareaMapper.searchAll();
	}

	public List<Templatearea> searchTemplatearea(Map searchMap) {
		return templateareaMapper.searchTemplatearea(searchMap);
	}

	public void update(Templatearea Templatearea) {
		templateareaMapper.update(Templatearea);
	}

	public TemplateareaMapper getTemplateareaMapper() {
		return templateareaMapper;
	}

	public void setTemplateareaMapper(TemplateareaMapper TemplateareaMapper) {
		this.templateareaMapper = TemplateareaMapper;
	}
	
	
	

}
