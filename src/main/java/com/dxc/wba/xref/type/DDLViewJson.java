package com.dxc.wba.xref.type;

import java.util.List;

import com.dxc.wba.xref.ddentity.DDLViews;

public class DDLViewJson {

   private List<Header> config;

	private List<DDLViews> data;

	public List<Header> getConfig() {
		return config;
	}

	public void setConfig(List<Header> config) {
		this.config = config;
	}

	public List<DDLViews> getData() {
		return data;
	}

	public void setData(List<DDLViews> data) {
		this.data = data;
	}



}

