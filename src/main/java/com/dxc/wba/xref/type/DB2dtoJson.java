package com.dxc.wba.xref.type;

import java.util.List;

import com.dxc.wba.xref.dto.ProgDb2Dto;

public class DB2dtoJson {

	private List<HeaderType> config;

	private List<ProgDb2Dto> data;

	public List<HeaderType> getConfig() {
		return config;
	}

	public void setConfig(List<HeaderType> config) {
		this.config = config;
	}

	public List<ProgDb2Dto> getData() {
		return data;
	}

	public void setData(List<ProgDb2Dto> data) {
		this.data = data;
	}
}
