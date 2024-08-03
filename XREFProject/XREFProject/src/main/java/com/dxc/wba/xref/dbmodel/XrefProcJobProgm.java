package com.dxc.wba.xref.dbmodel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Xref_Proc_Job_Progm",schema = "xref")
public class XrefProcJobProgm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @Column(name="Mainframe_Domain")
    private String mainframeDomain;

    @Column(name="Appl_id")
    private String appId;

    @Column(name="CPU_Id")
    private String cpuId;

    @Column(name="Job_name")
    private String jobName;

    @Column(name="Proc_name")
    private String procName;

    @Column(name="Prog_name")
    private String progName;

    @Column(name="Proc_ref_Ct")
    private int procRefCt ;

    @Column(name="Prog_ref_Ct")
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
