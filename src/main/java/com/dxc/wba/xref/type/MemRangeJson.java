package com.dxc.wba.xref.type;

import java.util.List;

public class MemRangeJson {

	private List<Header> config;

	private List<Object> data;

	// private List<Map<String ,Object >> response;

	public List<Header> getConfig() {
		return config;
	}

	public void setConfig(List<Header> config) {
		this.config = config;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	

	/*
	 * public List<Map<String, Object>> getResponse() { return response; }
	 * 
	 * public void setResponse(List<Map<String, Object>> response) { this.response =
	 * response; }
	 */

}
