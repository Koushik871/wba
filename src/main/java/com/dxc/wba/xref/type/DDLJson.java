package com.dxc.wba.xref.type;

import java.util.List;

import com.dxc.wba.xref.ddentity.DDLGeneration;

public class DDLJson {

	private List<Header> config;

	private List<DDLGeneration> data;

	public List<Header> getConfig() {
		return config;
	}

	public void setConfig(List<Header> list) {
		this.config = list;
	}

	public List<DDLGeneration> getData() {
		return data;
	}

	public void setData(List<DDLGeneration> data) {
		this.data = data;
	}

}
