package com.dxc.wba.xref.type;

import java.util.List;
import java.util.Map;

public class ProcNameJson {
	private List<HeaderType> config;

	private List<Map<String, Object>> data;

	public List<HeaderType> getConfig() {
		return config;
	}

	public void setConfig(List<HeaderType> config) {
		this.config = config;
	}

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}
}


