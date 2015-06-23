package com.topcheer.dao.bankNewsMgr;

import java.util.List;
import java.util.Map;

import com.topcheer.model.bankNewsMgr.Banknews;




public interface BanknewsMapper {
	
	public List<Banknews> searchBanknews(Map searchMap);
	
	public List<Banknews> searchAll();
	
	public List<Banknews> getBanknews(String newscode);
	
	public void insert(Banknews banknews);
	
	public void update(Banknews banknews);
	
	public void delete(String newscode);
	
}
