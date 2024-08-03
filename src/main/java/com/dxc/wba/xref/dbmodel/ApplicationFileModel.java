package com.dxc.wba.xref.dbmodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "xref_application", schema = "xref")
public class ApplicationFileModel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq_num;
	
	@Column(name = "Mainframe_Domain")
	private String mainframeDomain;
	@Column(name = "Appl_Id")
	private String Appl_Id;
	@Column(name = "Appl_name")
	private String Appl_name;
	@Column(name = "Appl_name_Desc_1")
	private String Appl_name_Desc_1;
	@Column(name = "Op_id")
	private String Op_id;
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
	public String getAppl_name_Desc_1() {
		return Appl_name_Desc_1;
	}
	public void setAppl_name_Desc_1(String appl_name_Desc_1) {
		Appl_name_Desc_1 = appl_name_Desc_1;
	}
	public String getOp_id() {
		return Op_id;
	}
	public void setOp_id(String op_id) {
		Op_id = op_id;
	}

}
