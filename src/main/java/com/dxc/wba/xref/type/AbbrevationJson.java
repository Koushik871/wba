package com.dxc.wba.xref.type;

import java.util.List;

import com.dxc.wba.xref.dbmodel.Abbrevations;

public class AbbrevationJson {

	private List<HeaderType> config;

	private List<Abbrevations> data;

	public List<HeaderType> getConfig() {
		return config;
	}

	public void setConfig(List<HeaderType> config) {
		this.config = config;
	}

	public List<Abbrevations> getData() {
		return data;
	}

	public void setData(List<Abbrevations> data) {
		this.data = data;
	}
	
	

}
