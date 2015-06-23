package com.topcheer.dao.budgetSchemeMgr;

import java.util.List;
import java.util.Map;

import com.topcheer.model.budgetSchemeMgr.BudgetScheme;
/**
 * @author shi.eh
 * @version 1.0
 */
public interface BudgetSchemeMapper {
	//��ѯԤ�㷽��
	public List<BudgetScheme> searchBudgetScheme(Map map);
	
	public BudgetScheme getBudgetScheme(String subjectId);
	//��ѯ���е�Ԥ�㷽��
	public List<BudgetScheme> searchAll();
	//����Ԥ�㷽��
	public int insert(BudgetScheme budgetScheme);
	//����Ԥ�㷽��
	public void update(BudgetScheme budgetScheme);
	//ɾ��Ԥ�㷽��
	public void delete(String subjectId);
	//��ѯ��¼��
	public int searchCount();
}
