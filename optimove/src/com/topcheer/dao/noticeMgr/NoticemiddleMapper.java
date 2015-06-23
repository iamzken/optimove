package com.topcheer.dao.noticeMgr;

import java.util.List;
import java.util.Map;

import com.topcheer.model.noticeMgr.Noticemiddle;


public interface NoticemiddleMapper {
	
	public List<Noticemiddle> searchNoticemiddle(Map searchMap);
	
	public List<Noticemiddle> searchAll();
	
	public List<Noticemiddle> getNoticemiddle(String noticecode);
	
	public void insert(Noticemiddle noticemiddle);
	
	public void update(Noticemiddle noticemiddle);
	
	public void delete(String noticecode);
	
}
