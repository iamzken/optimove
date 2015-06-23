package com.topcheer.model;

import javax.validation.constraints.Size;



public class NewBranchInfo {
	
		//分支行代码
		@Size(max=8,message="最大长度8")
		private    String   branchCode ;
		//机构级别(0 总行/1 支行/3 网点)
		@Size(max=1,message="最大长度1")
		private    String   grade ;
		//上级行代码
		@Size(max=6,message="最大长度6")
		private    String   upCode ;
		//名称
		@Size(max=120,message="最大长度120")
		private    String   name ;
		//地址
		@Size(max=60,message="最大长度60")
		private    String   address ;
		//邮编
		@Size(max=6,message="最大长度6")
		private    String   zipcode ;
		//电话号码
		@Size(max=18,message="最大长度18")
		private    String   telephone ;
		//传真
		@Size(max=18,message="最大长度18")
		private    String   fax ;
		//经营状态
		@Size(max=1,message="最大长度1")
		private    String   status ;
		//分行手续费清算账号
		@Size(max=19,message="最大长度19")
		private    String   account ;
		//联系人姓名
		@Size(max=60,message="最大长度60")
		private    String   linkManName ;
		//部门
		@Size(max=60,message="最大长度60")
		private    String   linkManDept ;
		//职务
		@Size(max=60,message="最大长度60")
		private    String   linkManPos ;
		//联系人电话
		@Size(max=20,message="最大长度20")
		private    String   linkManTel ;
		//联系人传真
		@Size(max=20,message="最大长度20")
		private    String   linkManFax ;
		//联系人Email
		@Size(max=40,message="最大长度40")
		private    String   linkManEmail ;
		//更新日期
		@Size(max=8,message="最大长度8")
		private    String   createDate ;
		//操作员工
		@Size(max=8,message="最大长度8")
		private    String   operatorCode ;
    
    
	public String getBranchCode() {
		return branchCode;
	}
	
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
    
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
    
	public String getUpCode() {
		return upCode;
	}
	
	public void setUpCode(String upCode) {
		this.upCode = upCode;
	}
	
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
    
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
    
	public String getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
    
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
    
	public String getFax() {
		return fax;
	}
	
	public void setFax(String fax) {
		this.fax = fax;
	}
	
    
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
    
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
    
	public String getLinkManName() {
		return linkManName;
	}
	
	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}
	
    
	public String getLinkManDept() {
		return linkManDept;
	}
	
	public void setLinkManDept(String linkManDept) {
		this.linkManDept = linkManDept;
	}
	
    
	public String getLinkManPos() {
		return linkManPos;
	}
	
	public void setLinkManPos(String linkManPos) {
		this.linkManPos = linkManPos;
	}
	
    
	public String getLinkManTel() {
		return linkManTel;
	}
	
	public void setLinkManTel(String linkManTel) {
		this.linkManTel = linkManTel;
	}
	
    
	public String getLinkManFax() {
		return linkManFax;
	}
	
	public void setLinkManFax(String linkManFax) {
		this.linkManFax = linkManFax;
	}
	
    
	public String getLinkManEmail() {
		return linkManEmail;
	}
	
	public void setLinkManEmail(String linkManEmail) {
		this.linkManEmail = linkManEmail;
	}
	
    
	public String getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
    
	public String getOperatorCode() {
		return operatorCode;
	}
	
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	
	
}
