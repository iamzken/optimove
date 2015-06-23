package com.topcheer.service;

import java.util.List;
import java.util.Map;

import com.topcheer.model.StyPro;
import com.topcheer.model.StyProDetail;

public interface IStyProService {

	public List<StyPro> searchStyPro(Map searchMap);

	public StyPro getStyPro(String proId);

	public List<StyPro> searchAll();

	public void insert(StyPro styPro,StyProDetail styProDetail);

	public void update(StyPro styPro,StyProDetail styProDetail);

	public void delete(String proId);
	
	public void publish(StyPro styPro);
	
	public List<StyProDetail> searchStyProDetail(StyPro styPro);
}