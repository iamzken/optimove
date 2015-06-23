package com.topcheer.service.providerMag;

import java.util.List;
import java.util.Map;

import com.topcheer.model.providerMag.Providermag;

public interface IProvidermagService {
	
	public List<Providermag> searchProvidermag(Map searchMap);
	
	public List<Providermag> getProvidermag(String providerid);
	
	public List<Providermag> searchAll();
	
	public void insert(Providermag providermag);
	
	public void update(Providermag providermag);
	
	public void delete(String id);

}
