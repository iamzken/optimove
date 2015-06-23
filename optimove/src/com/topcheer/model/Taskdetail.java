package com.topcheer.model;


public class Taskdetail {

	//

	private String taskid;
	//

	private String taskname;
	//

	private String tasktype;
	//

	private String taskcontent;
	//

	private String businessid;
	//

	private String userid;
	//

	private String createtasktime;
	//

	private String finishtasktime;
	//

	private String taskstatus;
	//

	private String operatebranch;
	//

	private String operateperson;
	//

	private String operatedate;
	//

	private String operatetime;
	
	
	

	public Taskdetail() {
	}

	public Taskdetail(String taskid, String taskname, String tasktype,
			String taskcontent, String businessid, String userid,
			String createtasktime, String finishtasktime, String taskstatus,
			String operatebranch, String operateperson, String operatedate,
			String operatetime) {
		this.taskid = taskid;
		this.taskname = taskname;
		this.tasktype = tasktype;
		this.taskcontent = taskcontent;
		this.businessid = businessid;
		this.userid = userid;
		this.createtasktime = createtasktime;
		this.finishtasktime = finishtasktime;
		this.taskstatus = taskstatus;
		this.operatebranch = operatebranch;
		this.operateperson = operateperson;
		this.operatedate = operatedate;
		this.operatetime = operatetime;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getTasktype() {
		return tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	public String getTaskcontent() {
		return taskcontent;
	}

	public void setTaskcontent(String taskcontent) {
		this.taskcontent = taskcontent;
	}

	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCreatetasktime() {
		return createtasktime;
	}

	public void setCreatetasktime(String createtasktime) {
		this.createtasktime = createtasktime;
	}

	public String getFinishtasktime() {
		return finishtasktime;
	}

	public void setFinishtasktime(String finishtasktime) {
		this.finishtasktime = finishtasktime;
	}

	public String getTaskstatus() {
		return taskstatus;
	}

	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}

	public String getOperatebranch() {
		return operatebranch;
	}

	public void setOperatebranch(String operatebranch) {
		this.operatebranch = operatebranch;
	}

	public String getOperateperson() {
		return operateperson;
	}

	public void setOperateperson(String operateperson) {
		this.operateperson = operateperson;
	}

	public String getOperatedate() {
		return operatedate;
	}

	public void setOperatedate(String operatedate) {
		this.operatedate = operatedate;
	}

	public String getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}

}
