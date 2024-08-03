package com.dxc.wba.xref.ddentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_definition", schema = "dd")
public class MemberDefinition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mem_id;
	private int row_no;

	private String memberName;
	private String prodStatus;
	private String memberType;
	private String memberDocType;
	private String memberDoclNo;
	private String memberLineText;

	public Integer converToInt() {
		return Integer.parseInt(this.memberDoclNo);
	}

	public int getRow_no() {
		return row_no;
	}

	public void setRow_no(int row_no) {
		this.row_no = row_no;
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

	public String getMemberDocType() {
		return memberDocType;
	}

	public void setMemberDocType(String memberDocType) {
		this.memberDocType = memberDocType;
	}

	public String getMemberDoclNo() {
		return memberDoclNo;
	}

	public void setMemberDoclNo(String memberDoclNo) {
		this.memberDoclNo = memberDoclNo;
	}

	public String getMemberLineText() {
		return memberLineText;
	}

	public void setMemberLineText(String memberLineText) {
		this.memberLineText = memberLineText;
	}

}
