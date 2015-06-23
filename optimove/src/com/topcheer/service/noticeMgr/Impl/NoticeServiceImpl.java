package com.topcheer.service.noticeMgr.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.noticeMgr.NoticeMapper;
import com.topcheer.model.noticeMgr.Notice;
import com.topcheer.service.noticeMgr.INoticeService;

@Service("noticeService")
public class NoticeServiceImpl implements INoticeService{
	
	@Autowired
	private NoticeMapper noticeMapper;

	public void delete(String id) {
		noticeMapper.delete(id);
		
	}

	public List<Notice> getNotice(String noticecode) {
		return noticeMapper.getNotice(noticecode);
	}

	public void insert(Notice notice) {
		 noticeMapper.insert(notice);
	}

	public List<Notice> searchAll() {
		return noticeMapper.searchAll();
	}

	public List<Notice> searchNotice(Map searchMap) {
		return noticeMapper.searchNotice(searchMap);
	}

	public void update(Notice Notice) {
		noticeMapper.update(Notice);
	}

	public NoticeMapper getNoticeMapper() {
		return noticeMapper;
	}

	public void setNoticeMapper(NoticeMapper NoticeMapper) {
		this.noticeMapper = NoticeMapper;
	}
	
	
	

}
