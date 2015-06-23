package com.topcheer.dao.budgetSchemeMgr;

import java.util.List;
import java.util.Map;

import com.topcheer.model.budgetSchemeMgr.BudgetScheme;
/**
 * @author shi.eh
 * @version 1.0
 */
public interface BudgetSchemeMapper {
	//查询预算方案
	public List<BudgetScheme> searchBudgetScheme(Map map);
	
	public BudgetScheme getBudgetScheme(String subjectId);
	//查询所有的预算方案
	public List<BudgetScheme> searchAll();
	//增加预算方案
	public int insert(BudgetScheme budgetScheme);
	//更新预算方案
	public void update(BudgetScheme budgetScheme);
	//删除预算方案
	public void delete(String subjectId);
	//查询记录数
	public int searchCount();
}
