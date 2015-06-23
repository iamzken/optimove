package com.topcheer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChartInfo{
	String tableName;
	String where;
	String orderBy;
	String parameter;
	String sql;
	String serviceObjId;
	String  methodName;
	String  reportTitle;
	//三个值分号隔开";"
	String  valueKeys;
	
	String  reportTitleX;
	String  reportTitleY;
	
	List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
	
	String reportType;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	
	
	public String getServiceObjId() {
		return serviceObjId;
	}
	public void setServiceObjId(String serviceObjId) {
		this.serviceObjId = serviceObjId;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getValueKeys() {
		return valueKeys;
	}
	public void setValueKeys(String valueKeys) {
		this.valueKeys = valueKeys;
	}
	
	
	public List<Map<String, Object>> getDataList() {
		return dataList;
	}
	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}
	public ChartInfo() {
		super();
	}
	
	
	public String getReportTitle() {
		return reportTitle;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}
	public ChartInfo(String tableName, String where, String orderBy,
			String parameter, String sql) {
		super();
		this.tableName = tableName;
		this.where = where;
		this.orderBy = orderBy;
		this.parameter = parameter;
		this.sql = sql;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getReportTitleX() {
		return reportTitleX;
	}
	public void setReportTitleX(String reportTitleX) {
		this.reportTitleX = reportTitleX;
	}
	public String getReportTitleY() {
		return reportTitleY;
	}
	public void setReportTitleY(String reportTitleY) {
		this.reportTitleY = reportTitleY;
	}
	
	
	
	
}
