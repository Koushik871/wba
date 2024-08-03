package com.dxc.wba.xref.type;

import java.util.List;

import com.dxc.wba.xref.dbmodel.XrefProcJobProgm;

public class ProgramPlanJson {
	
	private List<HeaderType> config;

private List<XrefProcJobProgm> data;

public List<HeaderType> getConfig() {
	return config;
}

public void setConfig(List<HeaderType> config) {
	this.config = config;
}

public List<XrefProcJobProgm> getData() {
	return data;
}

public void setData(List<XrefProcJobProgm> data) {
	this.data = data;
}


}
