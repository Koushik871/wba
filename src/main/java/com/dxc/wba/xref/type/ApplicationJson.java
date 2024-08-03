package com.dxc.wba.xref.type;

import java.util.List;

import com.dxc.wba.xref.dbmodel.ApplicationFileModel;

public class ApplicationJson {

	private List<HeaderType> config;

	private List<ApplicationFileModel> data;

	public List<HeaderType> getConfig() {
		return config;
	}

	public void setConfig(List<HeaderType> config) {
		this.config = config;
	}

	public List<ApplicationFileModel> getData() {
		return data;
	}

	public void setData(List<ApplicationFileModel> data) {
		this.data = data;
	}

	
}

