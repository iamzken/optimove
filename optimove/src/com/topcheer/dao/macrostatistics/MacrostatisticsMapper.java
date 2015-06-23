package com.topcheer.dao.macrostatistics;

import java.util.List;
import java.util.Map;


import com.topcheer.model.macrostatistics.Macrostatistics;


public interface MacrostatisticsMapper {
	
	public List<Macrostatistics> searchMacrostatistics(Map searchMap);
	
	public List<Macrostatistics> searchAll();
	
	public List<Macrostatistics> getMacrostatistics(String macrostatisticsid);
	
	public void insert(Macrostatistics macrostatistics);
	
	public void update(Macrostatistics macrostatistics);
	
	public void delete(String macrostatisticsid);
	
}
