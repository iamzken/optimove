package com.topcheer.service.apiServiceProvider;

import java.util.List;
import java.util.Map;

import com.topcheer.model.apiServiceProvider.Apiserviceprovider;

public interface IApiserviceproviderService {
	
	public List<Apiserviceprovider> searchApiserviceprovider(Map searchMap);
	
	public List<Apiserviceprovider> getApiserviceprovider(String apicode);
	
	public List<Apiserviceprovider> searchAll();
	
	public void insert(Apiserviceprovider apiserviceprovider);
	
	public void update(Apiserviceprovider apiserviceprovider);
	
	public void delete(String id);

}
