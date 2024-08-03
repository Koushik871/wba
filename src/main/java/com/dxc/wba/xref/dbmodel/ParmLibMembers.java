package com.dxc.wba.xref.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parmlib", schema = "xref")
public class ParmLibMembers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "seq_num")
	private Long seq_num;
	@Column(name = "Mainframe_Domain")
	private String mainframeDomain;
	@Column(name = "Dataset_Lib")
	private String Dataset_Lib;
	@Column(name = "Membername")
	private String memberName;
	@Column(name = "Linenum")
	private int lineNum;
	@Column(name = "Linetext")
	private String lineText;

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

	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	public String getLineText() {
		return lineText;
	}

	public void setLineText(String lineText) {
		this.lineText = lineText;
	}

	public String getDataset_Lib() {
		return Dataset_Lib;
	}

	public void setDataset_Lib(String dataset_Lib) {
		Dataset_Lib = dataset_Lib;
	}
	

}
