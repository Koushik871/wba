package com.dxc.wba.xref.ddentity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dd_dd_member_definition", schema = "dd")
public class DDMemberdefinition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mem_id;

	private String memberName;
	private String prodStatus;
	private String memberType;
	private int lineNo;

	@Column(name = "memberLineText", length = 500)
	private String memberLineText;

//	public Integer converToInt() {
//		return Integer.parseInt(this.lineNo);
//	}

	public long getMem_id() {
		return mem_id;
	}

	public int getLineNo() {
		return lineNo;
	}

	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}

	public void setMem_id(long mem_id) {
		this.mem_id = mem_id;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getProdStatus() {
		return prodStatus;
	}

	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getMemberLineText() {
		return memberLineText;
	}

	public void setMemberLineText(String memberLineText) {
		this.memberLineText = memberLineText;
	}

}
