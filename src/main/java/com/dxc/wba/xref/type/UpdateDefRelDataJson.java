package com.dxc.wba.xref.type;

import java.util.List;
import java.util.Map;

import com.dxc.wba.xref.dto.MemBrowseDTO;

public class UpdateDefRelDataJson {

	public Map<String, List<MemBrowseDTO>> definition;

	public Map<String, List<MemBrowseDTO>> relation;

	public Map<String, List<MemBrowseDTO>> getDefinition() {
		return definition;
	}

	public void setDefinition(Map<String, List<MemBrowseDTO>> definition) {
		this.definition = definition;
	}

	public Map<String, List<MemBrowseDTO>> getRelation() {
		return relation;
	}

	public void setRelation(Map<String, List<MemBrowseDTO>> relation) {
		this.relation = relation;
	}

	
}
