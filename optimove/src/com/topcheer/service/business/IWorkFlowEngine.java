package com.topcheer.service.business;

import com.topcheer.service.workflow.model.WorkFlowVO;

public interface IWorkFlowEngine {
	
	
	/**
	 * 开始 审批工作流程处理
	 * 
	 * @param workFlowVO
	 * @return
	 */
	public WorkFlowVO beginCheck(WorkFlowVO workFlowVO);
	
	/**
	 * 审批工作流程处理
	 * 
	 * @param workFlowVO
	 * @return
	 */
	public WorkFlowVO check(WorkFlowVO workFlowVO) ;

}
