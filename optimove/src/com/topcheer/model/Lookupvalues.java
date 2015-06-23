package com.topcheer.model;

import javax.validation.constraints.Size;

public class Lookupvalues {
	
	//类型
	
	@Size(max=30,message="最大长度为{field.max}")
	private String lookupType;
	
	//编码	
	@Size(max=30,message="最大长度为{field.max}")
	private String lookupCode;
	//含义
	@Size(max=80,message="最大长度为{field.max}")
	private String meaning;
	//备注	
	private String remark;
	//启用标志
	@Size(max=1,message="最大长度为{field.max}")
	private String enabledFlag;
	//选中标志
	@Size(max=1,message="最大长度为{field.max}")
	private String selectFlag;
	
	//创建人	
	private String createdBy;
	//创建日期
	private String creationDate;
	
	//最后更新人	
	private String lastUpdateBy;
	
	//最后更新日期
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
