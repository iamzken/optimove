package com.topcheer.service.basedata;

import java.util.List;
import java.util.Map;

import com.topcheer.model.basedata.Currency;

public interface ICurrencyService {
	
	public List<Currency> searchCurrency(Map searchMap);
	
	public List<Currency> getCurrency(String code);
	
	public List<Currency> searchAll();
	
	public void insert(Currency currency);
	
	public void update(Currency currency);
	
	public void delete(String id);

}
