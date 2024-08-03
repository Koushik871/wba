package com.dxc.wba.xref.type;

import java.util.List;

import com.dxc.wba.xref.dbmodel.CatProcModel;

public class CatProcJson {
	private List<HeaderType> config;

	private List<CatProcModel> data;

	public List<HeaderType> getConfig() {
		return config;
	}

	public void setConfig(List<HeaderType> config) {
		this.config = config;
	}

	public List<CatProcModel> getData() {
		return data;
	}

	public void setData(List<CatProcModel> data) {
		this.data = data;
	}
}


