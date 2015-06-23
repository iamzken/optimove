package com.topcheer.service.business;

import com.topcheer.service.workflow.model.WorkFlowVO;

public interface IWorkFlowEngine {
	
	
	/**
	 * ��ʼ �����������̴���
	 * 
	 * @param workFlowVO
	 * @return
	 */
	public WorkFlowVO beginCheck(WorkFlowVO workFlowVO);
	
	/**
	 * �����������̴���
	 * 
	 * @param workFlowVO
	 * @return
	 */
	public WorkFlowVO check(WorkFlowVO workFlowVO) ;

}
