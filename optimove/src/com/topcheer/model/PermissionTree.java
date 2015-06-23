package com.topcheer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PermissionTree {
	
	private String id;
	
	private String text;
	
	private String iconCls;
	
	private String state;
	
	private Boolean checked;
	
	private String grpId;
	
	private Map<String,Object> attributes;
	
	
	private List<PermissionTree> children;

	

	public PermissionTree() {
		super();
	}



	public PermissionTree(String id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	

	public PermissionTree(String id, String text, String iconCls) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
	}
	
	

	public PermissionTree(String id, String text, String iconCls, Boolean checked) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.checked = checked;
	}



	public void addChildren(PermissionTree pTree){
		if(this.children==null){
			this.children = new ArrayList<PermissionTree>();
		}
		children.add(pTree);
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	public String getIconCls() {
		return iconCls;
	}



	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public Boolean getChecked() {
		return checked;
	}



	public void setChecked(Boolean checked) {
		this.checked = checked;
	}



	public Map<String, Object> getAttributes() {
		return attributes;
	}



	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}



	public List<PermissionTree> getChildren() {
		return children;
	}



	public void setChildren(List<PermissionTree> children) {
		this.children = children;
	}



	public String getGrpId() {
		return grpId;
	}



	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	
	

	
}
