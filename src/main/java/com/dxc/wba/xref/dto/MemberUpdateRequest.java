package com.dxc.wba.xref.dto;

import java.util.ArrayList;
import java.util.List;

public class MemberUpdateRequest {
	private List<MemBrowseDTO> definitions = new ArrayList<>();
	private List<MemBrowseDTO> relations = new ArrayList<>();

	public MemberUpdateRequest() {
		// constructor initializes the lists to empty ArrayList
	}

	public List<MemBrowseDTO> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(List<MemBrowseDTO> definitions) {
		this.definitions = definitions;
	}

	public List<MemBrowseDTO> getRelations() {
		return relations;
	}

	public void setRelations(List<MemBrowseDTO> relations) {
		this.relations = relations;
	}
}
