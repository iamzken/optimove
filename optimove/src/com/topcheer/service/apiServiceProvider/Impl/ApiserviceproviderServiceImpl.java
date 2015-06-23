package com.topcheer.service.apiServiceProvider.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.apiServiceProvider.ApiserviceproviderMapper;
import com.topcheer.model.apiServiceProvider.Apiserviceprovider;
import com.topcheer.service.apiServiceProvider.IApiserviceproviderService;

@Service("apiserviceproviderService")
public class ApiserviceproviderServiceImpl implements IApiserviceproviderService{
	
	@Autowired
	private ApiserviceproviderMapper apiserviceproviderMapper;

	public void delete(String id) {
		apiserviceproviderMapper.delete(id);
		
	}

	public List<Apiserviceprovider> getApiserviceprovider(String apicode) {
		return apiserviceproviderMapper.getApiserviceprovider(apicode);
	}

	public void insert(Apiserviceprovider apiserviceprovider) {
		 apiserviceproviderMapper.insert(apiserviceprovider);
	}

	public List<Apiserviceprovider> searchAll() {
		return apiserviceproviderMapper.searchAll();
	}

	public List<Apiserviceprovider> searchApiserviceprovider(Map searchMap) {
		return apiserviceproviderMapper.searchApiserviceprovider(searchMap);
	}

	public void update(Apiserviceprovider Apiserviceprovider) {
		apiserviceproviderMapper.update(Apiserviceprovider);
	}

	public ApiserviceproviderMapper getApiserviceproviderMapper() {
		return apiserviceproviderMapper;
	}

	public void setApiserviceproviderMapper(ApiserviceproviderMapper ApiserviceproviderMapper) {
		this.apiserviceproviderMapper = ApiserviceproviderMapper;
	}
	
	
	

}
