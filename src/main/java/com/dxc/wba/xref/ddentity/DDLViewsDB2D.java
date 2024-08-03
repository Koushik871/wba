package com.dxc.wba.xref.ddentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ddlviews_db2d", schema = "dd")

public class DDLViewsDB2D {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ddlViewId;

	private String ddViewName;
	private long ddViewLno;
	private String ddViewLineText;

	public String getDdViewName() {
		return ddViewName;
	}

	public void setDdViewName(String ddViewName) {
		this.ddViewName = ddViewName;
	}

	public long getDdViewLno() {
		return ddViewLno;
	}

	public void setDdViewLno(long ddViewLno) {
		this.ddViewLno = ddViewLno;
	}

	public String getDdViewLineText() {
		return ddViewLineText;
	}

	public void setDdViewLineText(String ddViewLineText) {
		this.ddViewLineText = ddViewLineText;
	}

}
