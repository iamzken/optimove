package com.topcheer.dao.noticeMgr;

import java.util.List;
import java.util.Map;

import com.topcheer.model.noticeMgr.Notice;


public interface NoticeMapper {
	
	public List<Notice> searchNotice(Map searchMap);
	
	public List<Notice> searchAll();
	
	public List<Notice> getNotice(String noticecode);
	
	public void insert(Notice notice);
	
	public void update(Notice notice);
	
	public void delete(String noticecode);
	
}
