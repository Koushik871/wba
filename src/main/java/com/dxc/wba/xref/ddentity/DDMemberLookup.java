package com.dxc.wba.xref.ddentity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_definition_lookup", schema = "dd")
public class DDMemberLookup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private double row_no;
	@Column(name = "member_name")
	private String memberName;
	@Column(name = "prod_status")
	private String prodStatus;
	@Column(name = "member_type")
	private String memberType;
	@Column(name = "member_org")
	private String memberOrg;

	public double getRow_no() {
		return row_no;
	}

	public void setRow_no(double row_no) {
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

	public String getMemberOrg() {
		return memberOrg;
	}

	public void setMemberOrg(String memberOrg) {
		this.memberOrg = memberOrg;
	}

}
