package com.dxc.wba.xref.type;

import java.util.List;

import com.dxc.wba.xref.dbmodel.OperationalInstructions;

public class OperInsJson {

	private List<HeaderType> config;

	private List<OperationalInstructions> data;

	public List<HeaderType> getConfig() {
		return config;
	}

	public void setConfig(List<HeaderType> config) {
		this.config = config;
	}

	public List<OperationalInstructions> getData() {
		return data;
	}

	public void setData(List<OperationalInstructions> data) {
		this.data = data;
	}

}
