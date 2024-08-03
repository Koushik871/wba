package com.dxc.wba.xref.type;

import java.util.List;

import com.dxc.wba.xref.dbmodel.DB2PlanEntity;

public class DB2PlanJson {

	private List<HeaderType> config;

	private List<DB2PlanEntity> data;

	public List<HeaderType> getConfig() {
		return config;
	}

	public void setConfig(List<HeaderType> config) {
		this.config = config;
	}

	public List<DB2PlanEntity> getData() {
		return data;
	}

	public void setData(List<DB2PlanEntity> data) {
		this.data = data;
	}

}
