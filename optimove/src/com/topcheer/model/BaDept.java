package com.topcheer.model;

import javax.validation.constraints.Size;



public class BaDept {
	
		@Size(max=20,message="��󳤶�20")
		private String deptCode;
		
		@Size(max=60,message="��󳤶�60")
		private String deptName;

		public String getDeptCode() {
			return deptCode;
		}

		public void setDeptCode(String deptCode) {
			this.deptCode = deptCode;
		}

		public String getDeptName() {
			return deptName;
		}

		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}
}