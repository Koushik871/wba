package com.dxc.wba.xref.ddentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_relation", schema = "dd")
public class MemberRelation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mr_id;

	private int row_no;
	private String memberName;
	private String prodStatus;
	private String memberType;
	private String memberRelation;
	private String relatedMember;
	private String relatedMemProdStatus;
	private String relatedMemType;

	public long getMr_id() {
		return mr_id;
	}

	public void setMr_id(long mr_id) {
		this.mr_id = mr_id;
	}

	public Long converToInt() {
		return Long.valueOf(this.row_no);
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

	public String getRelatedMember() {
		return relatedMember;
	}

	public void setRelatedMember(String relatedMember) {
		this.relatedMember = relatedMember;
	}

	public String getRelatedMemProdStatus() {
		return relatedMemProdStatus;
	}

	public void setRelatedMemProdStatus(String relatedMemProdStatus) {
		this.relatedMemProdStatus = relatedMemProdStatus;
	}

	public String getRelatedMemType() {
		return relatedMemType;
	}

	public void setRelatedMemType(String relatedMemType) {
		this.relatedMemType = relatedMemType;
	}

	public String getMemberRelation() {
		return memberRelation;
	}

	public void setMemberRelation(String memberRelation) {
		this.memberRelation = memberRelation;
	}

	public int getRow_no() {
		return row_no;
	}

	public void setRow_no(int row_no) {
		this.row_no = row_no;
	}

}
