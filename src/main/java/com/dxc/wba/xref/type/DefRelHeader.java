package com.dxc.wba.xref.type;

import java.util.List;

public class DefRelHeader {

	List<Header> relationHeader;
	List<Header> defintionHeader;

	public List<Header> getRelationHeader() {
		return relationHeader;
	}

	public void setRelationHeader(List<Header> relationHeader) {
		this.relationHeader = relationHeader;
	}

	public List<Header> getDefintionHeader() {
		return defintionHeader;
	}

	public void setDefintionHeader(List<Header> defintionHeader) {
		this.defintionHeader = defintionHeader;
	}

}
