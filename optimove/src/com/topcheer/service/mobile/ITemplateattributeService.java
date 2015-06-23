package com.topcheer.service.mobile;

import java.util.List;
import java.util.Map;

import com.topcheer.model.mobile.Templateattribute;

public interface ITemplateattributeService {
	
	public List<Templateattribute> searchTemplateattribute(Map searchMap);
	
	public List<Templateattribute> getTemplateattribute(String id);
	
	public List<Templateattribute> searchAll();
	
	public void insert(Templateattribute templateattribute);
	
	public void update(Templateattribute templateattribute);
	
	public void delete(String id);

}
