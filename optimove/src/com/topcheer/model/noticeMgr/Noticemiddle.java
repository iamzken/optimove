package com.topcheer.model.noticeMgr;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Noticemiddle {
	
		//
		
		private    String   noticecode ;
		//
		
		private    String   noticebranch ;
		//
		
		private    String   noticedept ;
		//
		
		private    String   noticegroup ;
    
    
	public String getNoticecode() {
		return noticecode;
	}
	
	public void setNoticecode(String noticecode) {
		this.noticecode = noticecode;
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
	
	
}
