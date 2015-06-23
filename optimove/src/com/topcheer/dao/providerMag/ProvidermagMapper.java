package com.topcheer.dao.providerMag;

import java.util.List;
import java.util.Map;


import com.topcheer.model.providerMag.Providermag;


public interface ProvidermagMapper {
	
	public List<Providermag> searchProvidermag(Map searchMap);
	
	public List<Providermag> searchAll();
	
	public List<Providermag> getProvidermag(String providerid);
	
	public void insert(Providermag providermag);
	
	public void update(Providermag providermag);
	
	public void delete(String providerid);
	
}
