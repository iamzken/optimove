package com.topcheer.service.budgetSchemeMgr.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.budgetSchemeMgr.BudgetSchemeMapper;
import com.topcheer.model.budgetSchemeMgr.BudgetScheme;
import com.topcheer.service.budgetSchemeMgr.IBudgetSchemeService;
@Service("budgetSchemeService")
public class BudgetSchemeServiceImpl implements IBudgetSchemeService {
	@Autowired
	private BudgetSchemeMapper budgetSchemeMapper;
	public BudgetSchemeMapper getBudgetSchemeMapper() {
		return budgetSchemeMapper;
	}

	public void setBudgetSchemeMapper(BudgetSchemeMapper budgetSchemeMapper) {
		this.budgetSchemeMapper = budgetSchemeMapper;
	}

	public void delete(String id) {
		// TODO Auto-generated method stub
		budgetSchemeMapper.delete(id);
	}

	public BudgetScheme getBudgetScheme(String id) {
		// TODO Auto-generated method stub
		return budgetSchemeMapper.getBudgetScheme(id);
	}

	public void insert(BudgetScheme budgetScheme) {
		// TODO Auto-generated method stub
		budgetSchemeMapper.insert(budgetScheme);
	}

	public List<BudgetScheme> searchAll() {
		// TODO Auto-generated method stub
		return budgetSchemeMapper.searchAll();
	}

	@SuppressWarnings("unchecked")
	public List<BudgetScheme> searchBudgetScheme(Map searchMap) {
		return budgetSchemeMapper.searchBudgetScheme(searchMap);
	}

	public void update(BudgetScheme budgetScheme) {
		// TODO Auto-generated method stub
		budgetSchemeMapper.update(budgetScheme);
	}

}
