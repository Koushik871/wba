package com.dxc.wba.xref.type;

import java.util.List;

public class ProclibDatajson {
	private String datasetName;
	private List<String> data;

	public ProclibDatajson() {
	}

	public ProclibDatajson(String datasetName, List<String> data) {
		this.datasetName = datasetName;
		this.data = data;
	}

	public String getDatasetName() {
		return datasetName;
	}

	public void setDatasetName(String datasetName) {
		this.datasetName = datasetName;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}
}
