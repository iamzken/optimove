package com.topcheer.model.budgetSchemeMgr;

public class BudgetScheme {
	//预算方案编号
	private String subjectId;
	//预算方案名称
	private String subjectName;
	//编制维度
	private String madeAspect;
	//编制方式
	private String madeStyle;
	//审批流程
	private String approveFlow;
	//预算周期
	private String budgetRound;
	//状态
	private String status;
	//权限状态
	private String powerStatus;
	//适用级别
	private String suitLevel;
	//编制范围
	private String madeScope;
	//开始日期
	private String beginDate;
	//结束日期
	private String endDate;
	//目录节点Id
	private String catalogId;
	//编制机构
	private String madeGroup;
	//编制人
	private String madePerson;
	//操作日期
	private String operateDate;
	//操作时间
	private String operateTime;
	
	
	//备注
	private String remark;
	public String getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getMadeAspect() {
		return madeAspect;
	}
	public void setMadeAspect(String madeAspect) {
		this.madeAspect = madeAspect;
	}
	public String getMadeStyle() {
		return madeStyle;
	}
	public void setMadeStyle(String madeStyle) {
		this.madeStyle = madeStyle;
	}
	public String getBudgetRound() {
		return budgetRound;
	}
	public void setBudgetRound(String budgetRound) {
		this.budgetRound = budgetRound;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSuitLevel() {
		return suitLevel;
	}
	public void setSuitLevel(String suitLevel) {
		this.suitLevel = suitLevel;
	}
	public String getMadeScope() {
		return madeScope;
	}
	public void setMadeScope(String madeScope) {
		this.madeScope = madeScope;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getMadeGroup() {
		return madeGroup;
	}
	public void setMadeGroup(String madeGroup) {
		this.madeGroup = madeGroup;
	}
	public String getMadePerson() {
		return madePerson;
	}
	public void setMadePerson(String madePerson) {
		this.madePerson = madePerson;
	}
	public String getApproveFlow() {
		return approveFlow;
	}
	public void setApproveFlow(String approveFlow) {
		this.approveFlow = approveFlow;
	}
	public String getPowerStatus() {
		return powerStatus;
	}
	public void setPowerStatus(String powerStatus) {
		this.powerStatus = powerStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
