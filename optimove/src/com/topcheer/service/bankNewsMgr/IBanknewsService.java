package com.topcheer.service.bankNewsMgr;

import java.util.List;
import java.util.Map;

import com.topcheer.model.bankNewsMgr.Banknews;


public interface IBanknewsService {
	
	public List<Banknews> searchBanknews(Map searchMap);
	
	public List<Banknews> getBanknews(String newscode);
	
	public List<Banknews> searchAll();
	
	public void insert(Banknews banknews);
	
	public void update(Banknews banknews);
	
	public void delete(String id);

}
