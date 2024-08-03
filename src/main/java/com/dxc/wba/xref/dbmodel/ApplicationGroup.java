package com.dxc.wba.xref.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "xref_applicationGroup", schema = "xref")
public class ApplicationGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	 @Column(name = "seq_num")
	private Long seq_num;

	@Column(name = "Mainframe_Domain")
	private String mainframeDomain;
	@Column(name = "applGroup")
	private String applGroup;
	@Column(name = "applName")
	private String applName;
	@Column(name = "application")
	private String application;
	@Column(name = "applGroupName")
	private String applGroupName;
	public String getMainframeDomain() {
		return mainframeDomain;
	}
	public void setMainframeDomain(String mainframeDomain) {
		this.mainframeDomain = mainframeDomain;
	}
	public String getApplGroup() {
		return applGroup;
	}
	public void setApplGroup(String applGroup) {
		this.applGroup = applGroup;
	}
	public String getApplName() {
		return applName;
	}
	public void setApplName(String applName) {
		this.applName = applName;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getApplGroupName() {
		return applGroupName;
	}
	public void setApplGroupName(String applGroupName) {
		this.applGroupName = applGroupName;
	}

}