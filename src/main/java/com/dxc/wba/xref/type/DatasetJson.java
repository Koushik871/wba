package com.dxc.wba.xref.type;

import java.util.List;

import com.dxc.wba.xref.dbmodel.XrefDataset;

public class DatasetJson {

	private List<HeaderType> config;

	private List<XrefDataset> data;

	public List<HeaderType> getConfig() {
		return config;
	}

	public void setConfig(List<HeaderType> config) {
		this.config = config;
	}

	public List<XrefDataset> getData() {
		return data;
	}

	public void setData(List<XrefDataset> data) {
		this.data = data;
	}
}
