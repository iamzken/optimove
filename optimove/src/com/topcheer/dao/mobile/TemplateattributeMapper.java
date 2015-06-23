package com.topcheer.dao.mobile;

import java.util.List;
import java.util.Map;


import com.topcheer.model.mobile.Templateattribute;


public interface TemplateattributeMapper {
	
	public List<Templateattribute> searchTemplateattribute(Map searchMap);
	
	public List<Templateattribute> searchAll();
	
	public List<Templateattribute> getTemplateattribute(String id);
	
	public void insert(Templateattribute templateattribute);
	
	public void update(Templateattribute templateattribute);
	
	public void delete(String id);
	
}
