package com.dxc.wba.xref.type;

import java.util.List;

import com.dxc.wba.xref.ddentity.DDLViewsDB2D;

public class DB2DViewsJson {

	private List<Header> config;

	private List<DDLViewsDB2D> data;

	public List<Header> getConfig() {
		return config;
	}

	public void setConfig(List<Header> config) {
		this.config = config;
	}

	public List<DDLViewsDB2D> getData() {
		return data;
	}

	public void setData(List<DDLViewsDB2D> data) {
		this.data = data;
	}

}
