package com.topcheer.model;

import javax.validation.constraints.Size;

public class Lookuptype {
	
	//����	
	@Size(max=30,message="��󳤶�Ϊ{field.max}")
	private String lookupType;
	//�Զ��弶��
	@Size(max=30,message="��󳤶�Ϊ{field.max}")
	private String customizationLevel;
	//����
	@Size(max=240,message="��󳤶�Ϊ{field.max}")
	private String description;
	//������	
	private String createdBy;
	//��������	
	private String creationDate;
	//����޸���
	
	private String lastUpdateBy;
	//����޸�����
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
