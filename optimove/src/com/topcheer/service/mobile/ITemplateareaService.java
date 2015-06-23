package com.topcheer.service.mobile;

import java.util.List;
import java.util.Map;

import com.topcheer.model.mobile.Templatearea;

public interface ITemplateareaService {
	
	public List<Templatearea> searchTemplatearea(Map searchMap);
	
	public List<Templatearea> getTemplatearea(String id);
	
	public List<Templatearea> searchAll();
	
	public void insert(Templatearea templatearea);
	
	public void update(Templatearea templatearea);
	
	public void delete(String id);

}
