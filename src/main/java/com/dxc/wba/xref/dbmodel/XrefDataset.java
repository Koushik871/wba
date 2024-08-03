package com.dxc.wba.xref.dbmodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Xref_Dataset", schema = "xref")
public class XrefDataset implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dataset_id;

	@Column(name = "MainframeDomain")
	private String mainframeDomain;
	@Column(name = "Datasetname")
	private String datasetName;
	@Column(name = "Region")
	private String region;
	@Column(name = "Refct")
	private String refCt;
	@Column(name = "DSNmbrname")
	private String dsnMbrName;
	@Column(name = "DSNmbrsource")
	private String dsnMbrSource;
	@Column(name = "DSNProcname")
	private String procName;
	@Column(name = "DSNJobname")
	private String jobName;
	@Column(name = "DSNJobrefs")
	private String dsnJobRefs;
	@Column(name = "DSNProcrefs")
	private String dsnProcRefs;

	public String getMainframeDomain() {
		return mainframeDomain;
	}

	public void setMainframeDomain(String mainframeDomain) {
		this.mainframeDomain = mainframeDomain;
	}

	public String getDatasetName() {
		return datasetName;
	}

	public void setDatasetName(String datasetName) {
		this.datasetName = datasetName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRefCt() {
		return refCt;
	}

	public void setRefCt(String refCt) {
		this.refCt = refCt;
	}

	public String getDsnMbrName() {
		return dsnMbrName;
	}

	public void setDsnMbrName(String dsnMbrName) {
		this.dsnMbrName = dsnMbrName;
	}

	public String getDsnMbrSource() {
		return dsnMbrSource;
	}

	public void setDsnMbrSource(String dsnMbrSource) {
		this.dsnMbrSource = dsnMbrSource;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getDsnJobRefs() {
		return dsnJobRefs;
	}

	public void setDsnJobRefs(String dsnJobRefs) {
		this.dsnJobRefs = dsnJobRefs;
	}

	public String getDsnProcRefs() {
		return dsnProcRefs;
	}

	public void setDsnProcRefs(String dsnProcRefs) {
		this.dsnProcRefs = dsnProcRefs;
	}

}
