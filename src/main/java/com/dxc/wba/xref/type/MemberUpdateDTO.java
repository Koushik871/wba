package com.dxc.wba.xref.type;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberUpdateDTO {
	@JsonProperty("sequeneNo")
	private List<MemberSequenceDTO> sequeneNo;

	@JsonProperty("description")
	private List<MemberSequenceDTO> description;

	public List<MemberSequenceDTO> getSequeneNo() {
		return sequeneNo;
	}

	public void setSequeneNo(List<MemberSequenceDTO> sequeneNo) {
		this.sequeneNo = sequeneNo;
	}

	public List<MemberSequenceDTO> getDescription() {
		return description;
	}

	public void setDescription(List<MemberSequenceDTO> description) {
		this.description = description;
	}

}