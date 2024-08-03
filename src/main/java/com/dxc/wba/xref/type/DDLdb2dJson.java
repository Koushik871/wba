package com.dxc.wba.xref.type;

import java.util.List;

import com.dxc.wba.xref.ddentity.DDLGenerationDB2D;

public class DDLdb2dJson {

	private List<Header> config;

	private List<DDLGenerationDB2D> data;

	public List<Header> getConfig() {
		return config;
	}

	public void setConfig(List<Header> list) {
		this.config = list;
	}

	public List<DDLGenerationDB2D> getData() {
		return data;
	}

	public void setData(List<DDLGenerationDB2D> data) {
		this.data = data;
	}

}
