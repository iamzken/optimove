package com.topcheer.service.basedata.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.basedata.CurrencyMapper;
import com.topcheer.model.basedata.Currency;
import com.topcheer.service.basedata.ICurrencyService;

@Service("currencyService")
public class CurrencyServiceImpl implements ICurrencyService{
	
	@Autowired
	private CurrencyMapper currencyMapper;

	public void delete(String id) {
		currencyMapper.delete(id);
		
	}

	public List<Currency> getCurrency(String code) {
		return currencyMapper.getCurrency(code);
	}

	public void insert(Currency currency) {
		 currencyMapper.insert(currency);
	}

	public List<Currency> searchAll() {
		return currencyMapper.searchAll();
	}

	public List<Currency> searchCurrency(Map searchMap) {
		return currencyMapper.searchCurrency(searchMap);
	}

	public void update(Currency Currency) {
		currencyMapper.update(Currency);
	}

	public CurrencyMapper getCurrencyMapper() {
		return currencyMapper;
	}

	public void setCurrencyMapper(CurrencyMapper CurrencyMapper) {
		this.currencyMapper = CurrencyMapper;
	}
	
	
	

}
