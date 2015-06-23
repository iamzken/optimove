package com.topcheer.dao.apiServiceProvider;

import java.util.List;
import java.util.Map;


import com.topcheer.model.apiServiceProvider.Apiserviceprovider;


public interface ApiserviceproviderMapper {
	
	public List<Apiserviceprovider> searchApiserviceprovider(Map searchMap);
	
	public List<Apiserviceprovider> searchAll();
	
	public List<Apiserviceprovider> getApiserviceprovider(String apicode);
	
	public void insert(Apiserviceprovider apiserviceprovider);
	
	public void update(Apiserviceprovider apiserviceprovider);
	
	public void delete(String apicode);
	
}
