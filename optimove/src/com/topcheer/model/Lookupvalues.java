package com.topcheer.model;

import javax.validation.constraints.Size;

public class Lookupvalues {
	
	//����
	
	@Size(max=30,message="��󳤶�Ϊ{field.max}")
	private String lookupType;
	
	//����	
	@Size(max=30,message="��󳤶�Ϊ{field.max}")
	private String lookupCode;
	//����
	@Size(max=80,message="��󳤶�Ϊ{field.max}")
	private String meaning;
	//��ע	
	private String remark;
	//���ñ�־
	@Size(max=1,message="��󳤶�Ϊ{field.max}")
	private String enabledFlag;
	//ѡ�б�־
	@Size(max=1,message="��󳤶�Ϊ{field.max}")
	private String selectFlag;
	
	//������	
	private String createdBy;
	//��������
	private String creationDate;
	
	//��������	
	private String lastUpdateBy;
	
	//����������
	private String lastUpdateDate;
	
	
	public String getLookupType() {
		return lookupType;
	}

	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}

	public String getLookupCode() {
		return lookupCode;
	}

	public void setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public String getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(String selectFlag) {
		this.selectFlag = selectFlag;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

}
