package com.topcheer.service.noticeMgr;

import java.util.List;
import java.util.Map;

import com.topcheer.model.noticeMgr.Noticemiddle;

public interface INoticemiddleService {
	
	public List<Noticemiddle> searchNoticemiddle(Map searchMap);
	
	public List<Noticemiddle> getNoticemiddle(String noticecode);
	
	public List<Noticemiddle> searchAll();
	
	public void insert(Noticemiddle noticemiddle);
	
	public void update(Noticemiddle noticemiddle);
	
	public void delete(String id);

}
