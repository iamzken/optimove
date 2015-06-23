package com.topcheer.dao.mobile;

import java.util.List;
import java.util.Map;


import com.topcheer.model.mobile.Billsapply;


public interface BillsapplyMapper {
	
	public List<Billsapply> searchBillsapply(Map searchMap);
	
	public List<Billsapply> searchAll();
	
	public List<Billsapply> getBillsapply(String custno);
	
	public void insert(Billsapply billsapply);
	
	public void update(Billsapply billsapply);
	
	public void delete(String custno);
	
}
