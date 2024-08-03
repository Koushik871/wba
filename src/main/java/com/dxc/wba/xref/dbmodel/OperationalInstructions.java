package com.dxc.wba.xref.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "OperationalInstructions", schema = "xref")
public class OperationalInstructions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int opId;
	
	@Column(name = "mainframedomain")
	private String mainframeDomain;
	@Column(name = "membername")
	private String memberName;
	@Column(name = "applid")
	private String applId;
	@Column(name = "op_no")
	private String opNo;
	@Column(name = "validto")
	private String validTo;
	@Column(name = "validfrom")
	private String validFrom;
	@Column(name = "Timelastupdated")
	private String timeLastUpdated;
	@JsonFormat(pattern = "yy/mm/dd", shape = Shape.STRING)
	@Column(name = "Datelastupdated")
	private String dateLastUpdated;
	@Column(name = "line_no")
	private Integer lineNo;
	@Column(name = "opx_user")
	private String opxUser;
	@Column(name = "bussiness_func", length = 1000)
	private String bussinessFunc;
	@Column(name = "appl_func", length = 1000)
	private String applFunc;
	@Column(name = "recovery_action", length = 1000)
	private String recoveryAction;
	@Column(name = "spl_Ins", length = 1000)
	private String splIns;
	@Column(name = "InsOper", length = 2000)
	private String insOper;
	@Column(name = "ins_osg", length = 1000)
	private String insOsg;

	public String getMainframeDomain() {
		return mainframeDomain;
	}
	public void setMainframeDomain(String mainframeDomain) {
		this.mainframeDomain = mainframeDomain;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getApplId() {
		return applId;
	}
	public void setApplId(String applId) {
		this.applId = applId;
	}

	public String getOpNo() {
		return opNo;
	}
	public void setOpNo(String opNo) {
		this.opNo = opNo;
	}
	
	
	public Integer getLineNo() {
		return lineNo;
	}
	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}
	public String getValidTo() {
		return validTo;
	}
	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}
	public String getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}
	public String getTimeLastUpdated() {
		return timeLastUpdated;
	}
	public void setTimeLastUpdated(String timeLastUpdated) {
		this.timeLastUpdated = timeLastUpdated;
	}
	public String getDateLastUpdated() {
		return dateLastUpdated;
	}
	public void setDateLastUpdated(String dateLastUpdated) {
		this.dateLastUpdated = dateLastUpdated;
	}
	public String getOpxUser() {
		return opxUser;
	}
	public void setOpxUser(String opxUser) {
		this.opxUser = opxUser;
	}
	public String getBussinessFunc() {
		return bussinessFunc;
	}
	public void setBussinessFunc(String bussinessFunc) {
		this.bussinessFunc = bussinessFunc;
	}
	public String getApplFunc() {
		return applFunc;
	}
	public void setApplFunc(String applFunc) {
		this.applFunc = applFunc;
	}
	public String getRecoveryAction() {
		return recoveryAction;
	}
	public void setRecoveryAction(String recoveryAction) {
		this.recoveryAction = recoveryAction;
	}
	public String getSplIns() {
		return splIns;
	}
	public void setSplIns(String splIns) {
		this.splIns = splIns;
	}
	public String getInsOper() {
		return insOper;
	}
	public void setInsOper(String insOper) {
		this.insOper = insOper;
	}
	public String getInsOsg() {
		return insOsg;
	}
	public void setInsOsg(String insOsg) {
		this.insOsg = insOsg;
	}

	
}
