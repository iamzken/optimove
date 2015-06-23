package com.topcheer.service.macrostatistics;

import java.util.List;
import java.util.Map;

import com.topcheer.model.macrostatistics.Macrostatistics;

public interface IMacrostatisticsService {
	
	public List<Macrostatistics> searchMacrostatistics(Map searchMap);
	
	public List<Macrostatistics> getMacrostatistics(String macrostatisticsid);
	
	public List<Macrostatistics> searchAll();
	
	public void insert(Macrostatistics macrostatistics);
	
	public void update(Macrostatistics macrostatistics);
	
	public void delete(String id);

}
