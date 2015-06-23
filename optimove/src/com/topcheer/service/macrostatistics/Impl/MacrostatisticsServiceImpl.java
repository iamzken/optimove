package com.topcheer.service.macrostatistics.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.macrostatistics.MacrostatisticsMapper;
import com.topcheer.model.macrostatistics.Macrostatistics;
import com.topcheer.service.macrostatistics.IMacrostatisticsService;

@Service("macrostatisticsService")
public class MacrostatisticsServiceImpl implements IMacrostatisticsService{
	
	@Autowired
	private MacrostatisticsMapper macrostatisticsMapper;

	public void delete(String id) {
		macrostatisticsMapper.delete(id);
		
	}

	public List<Macrostatistics> getMacrostatistics(String macrostatisticsid) {
		return macrostatisticsMapper.getMacrostatistics(macrostatisticsid);
	}

	public void insert(Macrostatistics macrostatistics) {
		 macrostatisticsMapper.insert(macrostatistics);
	}

	public List<Macrostatistics> searchAll() {
		return macrostatisticsMapper.searchAll();
	}

	public List<Macrostatistics> searchMacrostatistics(Map searchMap) {
		return macrostatisticsMapper.searchMacrostatistics(searchMap);
	}

	public void update(Macrostatistics Macrostatistics) {
		macrostatisticsMapper.update(Macrostatistics);
	}

	public MacrostatisticsMapper getMacrostatisticsMapper() {
		return macrostatisticsMapper;
	}

	public void setMacrostatisticsMapper(MacrostatisticsMapper MacrostatisticsMapper) {
		this.macrostatisticsMapper = MacrostatisticsMapper;
	}
	
	
	

}
