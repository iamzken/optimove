package com.topcheer.model.noticeMgr;

import org.hibernate.validator.constraints.NotEmpty;




public class Notice {
	
		//
		@NotEmpty(message="公告编号不能为空")
		private    String   noticecode ;
		//
		
		private    String   noticetitle ;
		//
		
		private    String   noticecontent ;
		//
		
		private    String   noticestatus ;
		//
		
		private    String   importantlevel ;
		//
		
		private    String   createrid ;
		//
		
		private    String   creatername ;
		//
		
		private    String   startdate ;
		//
		
		private    String   enddate ;
		//
		
		private    String   noticebranch ;
		//
		
		private    String   noticedept ;
		//
		
		private    String   noticegroup ;
		//
		
		private    String   operatorname ;
		//
		
		private    String   operatetime ;
    
    
	public String getNoticecode() {
		return noticecode;
	}
	
	public void setNoticecode(String noticecode) {
		this.noticecode = noticecode;
	}
	
    
	public String getNoticetitle() {
		return noticetitle;
	}
	
	public void setNoticetitle(String noticetitle) {
		this.noticetitle = noticetitle;
	}
	
    
	public String getNoticecontent() {
		return noticecontent;
	}
	
	public void setNoticecontent(String noticecontent) {
		this.noticecontent = noticecontent;
	}
	
    
	public String getNoticestatus() {
		return noticestatus;
	}
	
	public void setNoticestatus(String noticestatus) {
		this.noticestatus = noticestatus;
	}
	
    
	public String getImportantlevel() {
		return importantlevel;
	}
	
	public void setImportantlevel(String importantlevel) {
		this.importantlevel = importantlevel;
	}
	
    
	public String getCreaterid() {
		return createrid;
	}
	
	public void setCreaterid(String createrid) {
		this.createrid = createrid;
	}
	
    
	public String getCreatername() {
		return creatername;
	}
	
	public void setCreatername(String creatername) {
		this.creatername = creatername;
	}
	
    
	public String getStartdate() {
		return startdate;
	}
	
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	
    
	public String getEnddate() {
		return enddate;
	}
	
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
    
	public String getNoticebranch() {
		return noticebranch;
	}
	
	public void setNoticebranch(String noticebranch) {
		this.noticebranch = noticebranch;
	}
	
    
	public String getNoticedept() {
		return noticedept;
	}
	
	public void setNoticedept(String noticedept) {
		this.noticedept = noticedept;
	}
	
    
	public String getNoticegroup() {
		return noticegroup;
	}
	
	public void setNoticegroup(String noticegroup) {
		this.noticegroup = noticegroup;
	}
	
    
	public String getOperatorname() {
		return operatorname;
	}
	
	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}
	
    
	public String getOperatetime() {
		return operatetime;
	}
	
	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}
	
	
}
