package com.dxc.wba.xref.dto;

public class DbCombineData {

	private DataCombineConfig config;
	
	private CombineData  data;
	
	

	public CombineData getData() {
		return data;
	}

	public void setData(CombineData data) {
		this.data = data;
	}

	public DataCombineConfig getConfig() {
		return config;
	}

	public void setConfig(DataCombineConfig config) {
		this.config = config;
	}
	
	
}
