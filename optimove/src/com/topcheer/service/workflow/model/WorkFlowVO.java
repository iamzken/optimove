/***************************************************************************
 * Confidential.  
 * ShangHai Topcheer Information Corporation
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of ShangHai
 * Topcheer Information Corporation ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with ShangHai Topcheer Information Corporation.
 ***************************************************************************/
package com.topcheer.service.workflow.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*******************************************************************************
 * @version 0.1.0
 * @author liuhui
 * 
 * <p>
 * update Logs
 * <p>
 * 1、Created liuhui 2006-6-19
 * 2、Update Liuhui 2009-4-27
 * <p>
 * TODO
 * 
 ******************************************************************************/
public class WorkFlowVO {
	
	private String insCorpCode;
	
	private String templateId;//流程模板编号

	private String serialNo;// 业务表流水号   预算方案表流水  预算表流水
	private String workType;// 审批类型 0-预算方案模板  1-预算
	private String checkResult;// 审批结果  0-同意，1-不同意
	private String workName;//预算方案名称或预算名称
	private String checkInfo;// 审批备注内容
	private String endState;//审批结束状态 0-成功结束 1-失败结束  2-未结束
	public String getEndState() {
		return endState;
	}
	public void setEndState(String endState) {
		this.endState = endState;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getCheckResult() {
		return checkResult;
	}
	private String errorInfo;//返回错误信息
	
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public String getCheckInfo() {
		return checkInfo;
	}
	public void setCheckInfo(String checkInfo) {
		this.checkInfo = checkInfo;
	}
	
	
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	private String prePolicyId;//投保单号

	private String activityStatus;// 节点实例状态 0-未完成  1-完成成功  2-完成失败  暂时没有太大的意义

	private String activityId;// 节点实例ID

	private boolean isStartActivity;// 是否是开始节点

	private boolean isEndActivity;// 是否是结束节点

	private String preActivityId;// 本节点的前一节点

	private String postActivityId;// 本节点的后一节点
	
	public String operatorBankCode;
	
	public String operatorCode;
	
	public String updateDate;
	
	public String updateTime; 
	
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getPrePolicyId() {
		return prePolicyId;
	}
	public void setPrePolicyId(String prePolicyId) {
		this.prePolicyId = prePolicyId;
	}
	public WorkFlowVO() {
		
	}
	/**
	 * @param insPrdId
	 * @param serialNo
	 * @param activityStatus
	 * @param activityId
	 */
//	public WorkFlowVO(String insPrdId, String serialNo, String activityStatus,
//			String activityId) {
//		// TODO Auto-generated constructor stub
//		this.insPrdId = insPrdId;
//		this.serialNo = serialNo;
//		this.activityStatus = activityStatus;
//		this.activityId = activityId;
//	}
	public WorkFlowVO(String insCorpCode, String serialNo, String activityStatus,
			String activityId) {
		this.insCorpCode = insCorpCode;
		this.serialNo = serialNo;
		this.activityStatus = activityStatus;
		this.activityId = activityId;
	}




	public String getOperatorBankCode() {
		return operatorBankCode;
	}
	public void setOperatorBankCode(String operatorBankCode) {
		this.operatorBankCode = operatorBankCode;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getPostActivityId() {
		return postActivityId;
	}

	public void setPostActivityId(String postActivityId) {
		this.postActivityId = postActivityId;
	}

	public String getPreActivityId() {
		return preActivityId;
	}

	public void setPreActivityId(String preActivityId) {
		this.preActivityId = preActivityId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}
	
	public String getInsCorpCode() {
		return insCorpCode;
	}
	
	public void setInsCorpCode(String insCorpCode) {
		this.insCorpCode = insCorpCode;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	

	public String toString() {
		StringBuffer buff = new StringBuffer();
		Method[] m = this.getClass().getMethods();
		final int idx = 3;

		try {
			for (int i = 0; i < m.length; i++) {
				String methodName = m[i].getName();
				if (methodName.startsWith("get")
						&& !methodName.endsWith("Class")) {
					// String className = m[i].getReturnType().getName();
					Object o = m[i].invoke(this, new Object[] {});
					if (o != null) {
						buff.append(methodName.substring(idx)).append("=[")
								.append(o).append("] ");
					}
				}
			}
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		} catch (InvocationTargetException ite) {
			ite.printStackTrace();
		}
		return buff.toString();
	}
	public boolean isEndActivity() {
		return isEndActivity;
	}
	public boolean getIsEndActivity() {
		return isEndActivity;
	}
	public void setEndActivity(boolean isEndActivity) {
		this.isEndActivity = isEndActivity;
	}
	public boolean isStartActivity() {
		return isStartActivity;
	}
	public boolean getIsStartActivity() {
		return isStartActivity;
	}
	public void setStartActivity(boolean isStartActivity) {
		this.isStartActivity = isStartActivity;
	}

}
