package com.topcheer.service.mobile;

import java.util.List;
import java.util.Map;

import com.topcheer.model.mobile.Billsapply;

public interface IBillsapplyService {
	
	public List<Billsapply> searchBillsapply(Map searchMap);
	
	public List<Billsapply> getBillsapply(String custno);
	
	public List<Billsapply> searchAll();
	
	public void insert(Billsapply billsapply);
	
	public void update(Billsapply billsapply);
	
	public void delete(String id);

}
