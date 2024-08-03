package com.dxc.wba.xref.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DataDTO {
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonProperty("definition")
	private MemberUpdateDTO definition;

	public MemberUpdateDTO getDefinition() {
		return definition;
	}

	public void setDefinition(MemberUpdateDTO definition) {
		this.definition = definition;
	}

}