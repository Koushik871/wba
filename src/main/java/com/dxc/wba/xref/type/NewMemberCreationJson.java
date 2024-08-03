package com.dxc.wba.xref.type;

import java.util.List;

import com.dxc.wba.xref.ddentity.MemberDefinitonTemp;

public class NewMemberCreationJson {
	private List<HeaderType> config;

	private List<MemberDefinitonTemp> data;

	public List<HeaderType> getConfig() {
		return config;
	}

	public void setConfig(List<HeaderType> config) {
		this.config = config;
	}

	public List<MemberDefinitonTemp> getData() {
		return data;
	}

	public void setData(List<MemberDefinitonTemp> data) {
		this.data = data;
	}



}
