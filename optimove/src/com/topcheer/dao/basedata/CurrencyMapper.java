package com.topcheer.dao.basedata;

import java.util.List;
import java.util.Map;


import com.topcheer.model.basedata.Currency;


public interface CurrencyMapper {
	
	public List<Currency> searchCurrency(Map searchMap);
	
	public List<Currency> searchAll();
	
	public List<Currency> getCurrency(String code);
	
	public void insert(Currency currency);
	
	public void update(Currency currency);
	
	public void delete(String code);
	
}
