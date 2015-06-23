package com.topcheer.model;

import java.util.ArrayList;
import java.util.List;

public class ReportInfo {
	private String title;
	private List<ColumnInfo> columnInfos = new ArrayList<ColumnInfo>();
	private List<Object> dataList = new ArrayList<Object>();
	private String serviceObjId;
	private Integer width;
	private Integer height;
	//
	private String subTitle;
	//是否显示title false  true 空是显示 不等于false显示
	private String isShowTitle;
	//titleHeight 标题高度
	private Integer titleHeight;
	//标题大小
	private Integer titleFontSize;
	
	
	
	
	public ReportInfo() {
		super();
	}
	public ReportInfo(String title, List<ColumnInfo> columnInfos,
			List<Object> dataList) {
		super();
		this.title = title;
		this.columnInfos = columnInfos;
		this.dataList = dataList;
	}
	public ReportInfo(String title, List<ColumnInfo> columnInfos,List<Object> dataList,String serviceObjId) {
		super();
		this.title = title;
		this.columnInfos = columnInfos;
		this.dataList = dataList;
		this.serviceObjId = serviceObjId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<ColumnInfo> getColumnInfos() {
		return columnInfos;
	}
	public void setColumnInfos(List<ColumnInfo> columnInfos) {
		this.columnInfos = columnInfos;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public List<Object> getDataList() {
		return dataList;
	}
	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
	}
	public String getServiceObjId() {
		return serviceObjId;
	}
	public void setServiceObjId(String serviceObjId) {
		this.serviceObjId = serviceObjId;
	}
	public String getIsShowTitle() {
		return isShowTitle;
	}
	public void setIsShowTitle(String isShowTitle) {
		this.isShowTitle = isShowTitle;
	}
	public Integer getTitleHeight() {
		return titleHeight;
	}
	public void setTitleHeight(Integer titleHeight) {
		this.titleHeight = titleHeight;
	}
	public Integer getTitleFontSize() {
		return titleFontSize;
	}
	public void setTitleFontSize(Integer titleFontSize) {
		this.titleFontSize = titleFontSize;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	
	
	
}
