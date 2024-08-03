package com.dxc.wba.xref.dbmodel;

import com.dxc.wba.xref.type.MemRelheading;
import com.dxc.wba.xref.type.MemberHeadingType;

public class DefinitionAndRelation {
	
	private MemberHeadingType definition;
	private MemRelheading relation;
	public MemberHeadingType getDefinition() {
		return definition;
	}
	public void setDefinition(MemberHeadingType definition) {
		this.definition = definition;
	}
	public MemRelheading getRelation() {
		return relation;
	}
	public void setRelation(MemRelheading relation) {
		this.relation = relation;
	}

	
	
	

}
