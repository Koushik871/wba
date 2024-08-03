package com.dxc.wba.xref.type;

import java.util.List;

public class FileResponse {

	
	private List<HeaderType> config;
	public List<HeaderType> getConfig() {
		return config;
	}

	public void setConfig(List<HeaderType> config) {
		this.config = config;
	}

	private List<String> data;

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

}