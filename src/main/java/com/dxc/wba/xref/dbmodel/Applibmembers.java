package com.dxc.wba.xref.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "applib", schema = "xref")
public class Applibmembers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "seq_num")
	private Long seq_num;
 	@Column(name = "Mainframe_Domain")
	private String mainframeDomain;
	@Column(name = "Appl_Id")
	private String Appl_Id;
	@Column(name = "Appl_name")
	private String Appl_name;
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
	
	
	public String getAppl_Id() {
		return Appl_Id;
	}
	public void setAppl_Id(String appl_Id) {
		Appl_Id = appl_Id;
	}
	public String getAppl_name() {
		return Appl_name;
	}
	public void setAppl_name(String appl_name) {
		Appl_name = appl_name;
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
	
	}