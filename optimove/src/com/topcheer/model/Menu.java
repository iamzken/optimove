package com.topcheer.model;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class Menu {
	//菜单项编号
	@NotEmpty(message="不能为空")
	private    String   menuCode ;
	//父菜单编号
	private    String   menuParent ;
	//菜单名称
	@NotEmpty(message="不能为空")
	private    String   menuName ;
	//所指URL
	private    String   menuUrl ;
	//所指URL
	private    String   icon;
	//所属模块
	private    String   menuModel ;
	//标志(1-启用 0-停用)
	@NotEmpty(message="不能为空")
	private    String   menuFlag ;
	//备注
	private    String   menuRemark ;
	//创建人
	private    BigDecimal   cretaedBy ;
	//创建日期
	private    Date   creationDate ;
	//最后修改人
	private    String   lastUpdatedBy ;
	//最后修改日期
	private    Date   lastUpdateDate ;
	//排序字面
	private    BigDecimal   orderIndex ;
	//菜单标志(0 菜单 1 功能)
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
