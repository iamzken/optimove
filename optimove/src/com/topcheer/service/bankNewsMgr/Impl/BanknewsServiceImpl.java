package com.topcheer.service.bankNewsMgr.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.bankNewsMgr.BanknewsMapper;
import com.topcheer.model.bankNewsMgr.Banknews;
import com.topcheer.service.bankNewsMgr.IBanknewsService;


@Service("banknewsService")
public class BanknewsServiceImpl implements IBanknewsService{
	
	@Autowired
	private BanknewsMapper banknewsMapper;

	public void delete(String id) {
		banknewsMapper.delete(id);
		
	}

	public List<Banknews> getBanknews(String newscode) {
		return banknewsMapper.getBanknews(newscode);
	}

	public void insert(Banknews banknews) {
		 banknewsMapper.insert(banknews);
	}

	public List<Banknews> searchAll() {
		return banknewsMapper.searchAll();
	}

	public List<Banknews> searchBanknews(Map searchMap) {
		return banknewsMapper.searchBanknews(searchMap);
	}

	public void update(Banknews Banknews) {
		banknewsMapper.update(Banknews);
	}

	public BanknewsMapper getBanknewsMapper() {
		return banknewsMapper;
	}

	public void setBanknewsMapper(BanknewsMapper BanknewsMapper) {
		this.banknewsMapper = BanknewsMapper;
	}
	
	
	

}
