package com.dxc.wba.xref.dto;

import java.util.List;

import com.dxc.wba.xref.type.HeaderType;

public class DataCombineConfig {

	List<HeaderType> programHeader;
	List<HeaderType> tableviewHeader;
	
	public List<HeaderType> getProgramHeader() {
		return programHeader;
	}
	public void setProgramHeader(List<HeaderType> programHeader) {
		this.programHeader = programHeader;
	}
	public List<HeaderType> getTableviewHeader() {
		return tableviewHeader;
	}
	public void setTableviewHeader(List<HeaderType> tableviewHeader) {
		this.tableviewHeader = tableviewHeader;
	}
	
	
	
}
