package com.dxc.wba.xref.dbmodel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "xref_proc_job_progm", schema = "xref")
public class XrefProcJobProgm implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_id")
	private Long job_id;

	@Column(name = "MainframeDomain")
	private String mainframeDomain;

	@Column(name = "Applid")
	private String appId;

	@Column(name = "CPUId")
	private String cpuId;

	@Column(name = "Jobname")
	private String jobName;

	@Column(name = "Procname")
	private String procName;

	@Column(name = "Progname")
	private String progName;

	@Column(name = "ProcrefCt")
	private String procRefCt;

	@Column(name = "ProgrefCt")
	private String progRefCt;

	@Column(name = "Source")
	private String source;

	public String getMainframeDomain() {
		return mainframeDomain;
	}

	public void setMainframeDomain(String mainframeDomain) {
		this.mainframeDomain = mainframeDomain;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCpuId() {
		return cpuId;
	}

	public void setCpuId(String cpuId) {
		this.cpuId = cpuId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public String getProgName() {
		return progName;
	}

	public void setProgName(String progName) {
		this.progName = progName;
	}

	public String getProcRefCt() {
		return procRefCt;
	}

	public void setProcRefCt(String procRefCt) {
		this.procRefCt = procRefCt;
	}

	public String getProgRefCt() {
		return progRefCt;
	}

	public void setProgRefCt(String progRefCt) {
		this.progRefCt = progRefCt;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
