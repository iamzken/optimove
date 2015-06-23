package com.topcheer.service.budgetSchemeMgr;

import java.util.List;
import java.util.Map;

import com.topcheer.model.budgetSchemeMgr.BudgetScheme;


public interface IBudgetSchemeService {
	public List<BudgetScheme> searchBudgetScheme(Map searchMap);
	
	public BudgetScheme getBudgetScheme(String id);
	
	public List<BudgetScheme> searchAll();
	
	public void insert(BudgetScheme budgetScheme);
	
	public void update(BudgetScheme budgetScheme);
	
	public void delete(String id);
}
