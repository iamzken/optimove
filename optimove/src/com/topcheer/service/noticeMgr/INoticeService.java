package com.topcheer.service.noticeMgr;

import java.util.List;
import java.util.Map;

import com.topcheer.model.noticeMgr.Notice;

public interface INoticeService {
	
	public List<Notice> searchNotice(Map searchMap);
	
	public List<Notice> getNotice(String noticecode);
	
	public List<Notice> searchAll();
	
	public void insert(Notice notice);
	
	public void update(Notice notice);
	
	public void delete(String id);

}
