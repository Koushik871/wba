package com.dxc.wba.xref.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberSequenceDTO {

	@JsonProperty("sequeneNo")
	private String sequeneNo;

	@JsonProperty("description")
	private String description;

	public String getSequeneNo() {
		return sequeneNo;
	}

	public void setSequeneNo(String sequeneNo) {
		this.sequeneNo = sequeneNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
