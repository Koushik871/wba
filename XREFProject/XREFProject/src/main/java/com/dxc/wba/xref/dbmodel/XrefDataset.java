package com.dxc.wba.xref.dbmodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Xref_Dataset",schema = "xref")
public class XrefDataset implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long datasetId;

    @Column(name="Mainframe_Domain")
    private String mainframeDomain;
    @Column(name="Dataset_name")
    private String datasetName;
    @Column(name="Region")
    private String region;
    @Column(name="Ref_ct")
    private int refCt;
    @Column(name="DSN_mbr_name")
    private String dsnMbrName;
    @Column(name="DSN_mbr_source")
    private String dsnMbrSource;
    @Column(name="DSN_Proc_name")
    private String dsnProcName;
    @Column(name="DSN_Job_name")
    private String dsnJobName;

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

    public int getRefCt() {
        return refCt;
    }

    public void setRefCt(int refCt) {
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

    public String getDsnProcName() {
        return dsnProcName;
    }

    public void setDsnProcName(String dsnProcName) {
        this.dsnProcName = dsnProcName;
    }

    public String getDsnJobName() {
        return dsnJobName;
    }

    public void setDsnJobName(String dsnJobName) {
        this.dsnJobName = dsnJobName;
    }
}
