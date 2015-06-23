package com.topcheer.model;

import javax.validation.constraints.Size;

public class Lookuptype {
	
	//类型	
	@Size(max=30,message="最大长度为{field.max}")
	private String lookupType;
	//自定义级别
	@Size(max=30,message="最大长度为{field.max}")
	private String customizationLevel;
	//描述
	@Size(max=240,message="最大长度为{field.max}")
	private String description;
	//创建人	
	private String createdBy;
	//创建日期	
	private String creationDate;
	//最后修改人
	
	private String lastUpdateBy;
	//最后修改日期
	private String lastUpdateDate;
	
	
	public String getLookupType() {
		return lookupType;
	}
	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}
	public String getCustomizationLevel() {
		return customizationLevel;
	}
	public void setCustomizationLevel(String customizationLevel) {
		this.customizationLevel = customizationLevel;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
