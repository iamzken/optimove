package com.topcheer.service.noticeMgr.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.noticeMgr.NoticemiddleMapper;
import com.topcheer.model.noticeMgr.Noticemiddle;
import com.topcheer.service.noticeMgr.INoticemiddleService;

@Service("noticemiddleService")
public class NoticemiddleServiceImpl implements INoticemiddleService{
	
	@Autowired
	private NoticemiddleMapper noticemiddleMapper;

	public void delete(String id) {
		noticemiddleMapper.delete(id);
		
	}

	public List<Noticemiddle> getNoticemiddle(String noticecode) {
		return noticemiddleMapper.getNoticemiddle(noticecode);
	}

	public void insert(Noticemiddle noticemiddle) {
		 noticemiddleMapper.insert(noticemiddle);
	}

	public List<Noticemiddle> searchAll() {
		return noticemiddleMapper.searchAll();
	}

	public List<Noticemiddle> searchNoticemiddle(Map searchMap) {
		return noticemiddleMapper.searchNoticemiddle(searchMap);
	}

	public void update(Noticemiddle Noticemiddle) {
		noticemiddleMapper.update(Noticemiddle);
	}

	public NoticemiddleMapper getNoticemiddleMapper() {
		return noticemiddleMapper;
	}

	public void setNoticemiddleMapper(NoticemiddleMapper NoticemiddleMapper) {
		this.noticemiddleMapper = NoticemiddleMapper;
	}
	
	
	

}
