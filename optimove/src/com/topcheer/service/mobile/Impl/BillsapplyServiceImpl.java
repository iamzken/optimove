package com.topcheer.service.mobile.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.mobile.BillsapplyMapper;
import com.topcheer.model.mobile.Billsapply;
import com.topcheer.service.mobile.IBillsapplyService;

@Service("billsapplyService")
public class BillsapplyServiceImpl implements IBillsapplyService{
	
	@Autowired
	private BillsapplyMapper billsapplyMapper;

	public void delete(String id) {
		billsapplyMapper.delete(id);
		
	}

	public List<Billsapply> getBillsapply(String custno) {
		return billsapplyMapper.getBillsapply(custno);
	}

	public void insert(Billsapply billsapply) {
		 billsapplyMapper.insert(billsapply);
	}

	public List<Billsapply> searchAll() {
		return billsapplyMapper.searchAll();
	}

	public List<Billsapply> searchBillsapply(Map searchMap) {
		return billsapplyMapper.searchBillsapply(searchMap);
	}

	public void update(Billsapply Billsapply) {
		billsapplyMapper.update(Billsapply);
	}

	public BillsapplyMapper getBillsapplyMapper() {
		return billsapplyMapper;
	}

	public void setBillsapplyMapper(BillsapplyMapper BillsapplyMapper) {
		this.billsapplyMapper = BillsapplyMapper;
	}
	
	
	

}
