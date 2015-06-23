package com.topcheer.dao.mobile;

import java.util.List;
import java.util.Map;


import com.topcheer.model.mobile.Templatearea;


public interface TemplateareaMapper {
	
	public List<Templatearea> searchTemplatearea(Map searchMap);
	
	public List<Templatearea> searchAll();
	
	public List<Templatearea> getTemplatearea(String id);
	
	public void insert(Templatearea templatearea);
	
	public void update(Templatearea templatearea);
	
	public void delete(String id);
	
}
