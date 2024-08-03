package com.dxc.wba.xref.type;

import java.util.List;

import com.dxc.wba.xref.dbmodel.ApplicationGroup;

public class ApplicationGroupJson {
	private List<HeaderType> config;

	private List<ApplicationGroup> data;

	public List<HeaderType> getConfig() {
		return config;
	}

	public void setConfig(List<HeaderType> config) {
		this.config = config;
	}

	public List<ApplicationGroup> getData() {
		return data;
	}

	public void setData(List<ApplicationGroup> data) {
		this.data = data;
	}

	
}


