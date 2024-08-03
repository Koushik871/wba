package com.dxc.wba.xref.type;

import java.util.List;

import com.dxc.wba.xref.dto.MemBrowseDTO;

public class DefRelDataUpdate {
	public List<MemBrowseDTO> defintion;

	public List<MemBrowseDTO> relation;

	public List<MemBrowseDTO> getDefintion() {
		return defintion;
	}

	public void setDefintion(List<MemBrowseDTO> defintion) {
		this.defintion = defintion;
	}

	public List<MemBrowseDTO> getRelation() {
		return relation;
	}

	public void setRelation(List<MemBrowseDTO> relation) {
		this.relation = relation;
	}

}
