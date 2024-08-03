package com.dxc.wba.xref.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="xref_cat_proc",schema = "xref")
public class CatProcModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long job_id;

    @Column(name="MainframeDomain")
    private String mainframeDomain;

    @Column(name="Applid")
    private String appId;

    @Column(name="CPUId")
    private String cpuId;

    @Column(name="Jobname")
    private String jobName;

    @Column(name="Procname")
    private String procName;

    @Column(name="Progname")
    private String progName;

    @Column(name="ProcrefCt")
    private int procRefCt ;

    @Column(name="ProgrefCt")
    private int progRefCt;

    @Column(name="Source")
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

    public int getProcRefCt() {
        return procRefCt;
    }

    public void setProcRefCt(int procRefCt) {
        this.procRefCt = procRefCt;
    }

    public int getProgRefCt() {
        return progRefCt;
    }

    public void setProgRefCt(int progRefCt) {
        this.progRefCt = progRefCt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}



