package com.topcheer.model;

import javax.validation.constraints.Size;

public class StyPro {

	@Size(max = 20, message = "最大长度20")
	private String id;

	@Size(max = 100, message = "最大长度100")
	private String name;

	@Size(max = 20, message = "最大长度20")
	private String type;

	@Size(max = 20, message = "最大长度20")
	private String status;
	
	@Size(max = 20, message = "最大长度20")
	private String code;

	@Size(max = 200, message = "最大长度200")
	private String descs;

	@Size(max = 2000, message = "最大长度2000")
	private String json_str;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public String getJson_str() {
		return json_str;
	}

	public void setJson_str(String json_str) {
		this.json_str = json_str;
	}
}