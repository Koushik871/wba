package com.dxc.wba.xref.type;

import java.util.List;
import java.util.Map;

public class TableViewjson {
	
	private List<Header> config;

	private List<Map<String ,Object >> data;
	
	//private List<Map<String ,Object >> response;

 
	public List<Header> getConfig() {
		return config;
	}

	public void setConfig(List<Header> config) {
		this.config = config;
	}

	

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}

	/*
	 * public List<Map<String, Object>> getResponse() { return response; }
	 * 
	 * public void setResponse(List<Map<String, Object>> response) { this.response =
	 * response; }
	 */
	
}


