package com.dxc.wba.xref.dto;

import java.util.List;

import com.dxc.wba.xref.ddentity.MemberDefinition;
import com.dxc.wba.xref.ddentity.MemberRelation;

public class DdMemberResponse {

	List<MemberDefinition> memberDefinitions;
	List<MemberRelation> memberRelations;
	public List<MemberDefinition> getMemberDefinitions() {
		return memberDefinitions;
	}
	public void setMemberDefinitions(List<MemberDefinition> memberDefinitions) {
		this.memberDefinitions = memberDefinitions;
	}
	public List<MemberRelation> getMemberRelations() {
		return memberRelations;
	}
	public void setMemberRelations(List<MemberRelation> memberRelations) {
		this.memberRelations = memberRelations;
	}
	
	

}
