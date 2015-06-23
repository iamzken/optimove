package com.topcheer.service.providerMag.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.providerMag.ProvidermagMapper;
import com.topcheer.model.providerMag.Providermag;
import com.topcheer.service.providerMag.IProvidermagService;

@Service("providermagService")
public class ProvidermagServiceImpl implements IProvidermagService{
	
	@Autowired
	private ProvidermagMapper providermagMapper;

	public void delete(String id) {
		providermagMapper.delete(id);
		
	}

	public List<Providermag> getProvidermag(String providerid) {
		return providermagMapper.getProvidermag(providerid);
	}

	public void insert(Providermag providermag) {
		 providermagMapper.insert(providermag);
	}

	public List<Providermag> searchAll() {
		return providermagMapper.searchAll();
	}

	public List<Providermag> searchProvidermag(Map searchMap) {
		return providermagMapper.searchProvidermag(searchMap);
	}

	public void update(Providermag Providermag) {
		providermagMapper.update(Providermag);
	}

	public ProvidermagMapper getProvidermagMapper() {
		return providermagMapper;
	}

	public void setProvidermagMapper(ProvidermagMapper ProvidermagMapper) {
		this.providermagMapper = ProvidermagMapper;
	}
	
	
	

}
