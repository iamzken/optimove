package com.topcheer.model;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class Menu {
	//�˵�����
	@NotEmpty(message="����Ϊ��")
	private    String   menuCode ;
	//���˵����
	private    String   menuParent ;
	//�˵�����
	@NotEmpty(message="����Ϊ��")
	private    String   menuName ;
	//��ָURL
	private    String   menuUrl ;
	//��ָURL
	private    String   icon;
	//����ģ��
	private    String   menuModel ;
	//��־(1-���� 0-ͣ��)
	@NotEmpty(message="����Ϊ��")
	private    String   menuFlag ;
	//��ע
	private    String   menuRemark ;
	//������
	private    BigDecimal   cretaedBy ;
	//��������
	private    Date   creationDate ;
	//����޸���
	private    String   lastUpdatedBy ;
	//����޸�����
	private    Date   lastUpdateDate ;
	//��������
	private    BigDecimal   orderIndex ;
	//�˵���־(0 �˵� 1 ����)
	private    String   isMenu ;
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuParent() {
		return menuParent;
	}
	public void setMenuParent(String menuParent) {
		this.menuParent = menuParent;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getMenuModel() {
		return menuModel;
	}
	public void setMenuModel(String menuModel) {
		this.menuModel = menuModel;
	}
	public String getMenuFlag() {
		return menuFlag;
	}
	public void setMenuFlag(String menuFlag) {
		this.menuFlag = menuFlag;
	}
	public String getMenuRemark() {
		return menuRemark;
	}
	public void setMenuRemark(String menuRemark) {
		this.menuRemark = menuRemark;
	}
	public BigDecimal getCretaedBy() {
		return cretaedBy;
	}
	public void setCretaedBy(BigDecimal cretaedBy) {
		this.cretaedBy = cretaedBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public BigDecimal getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(BigDecimal orderIndex) {
		this.orderIndex = orderIndex;
	}
	public String getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}
	
	
	
	
	
	
}
