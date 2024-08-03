package com.dxc.wba.xref.dto;

public class ProgDb2Dto {

	private String applid;

	private String cpuId;

	private String jobName;

	private String procName;

	private String progName;

	private int procRefCt;

	private int progRefCt;

	private String source;

	private String SSID;

	private String DB2Plan;

	private char DB2Planver;

	private String DBRM;

	private char DBRMVersion;

	private int Stmt;

	private String Verb;

	private String Owner;

	private String tableView;

	public ProgDb2Dto() {
		super();
	}

	public ProgDb2Dto(String applid, String cpuId, String jobName, String procName, String progName, int procRefCt,
			int progRefCt, String source, String sSID, String dB2Plan, char dB2Planver, String dBRM, char dBRMVersion,
			int stmt, String verb, String owner, String tableView) {
		super();
		this.applid = applid;
		this.cpuId = cpuId;
		this.jobName = jobName;
		this.procName = procName;
		this.progName = progName;
		this.procRefCt = procRefCt;
		this.progRefCt = progRefCt;
		this.source = source;
		SSID = sSID;
		DB2Plan = dB2Plan;
		DB2Planver = dB2Planver;
		DBRM = dBRM;
		DBRMVersion = dBRMVersion;
		Stmt = stmt;
		Verb = verb;
		Owner = owner;
		this.tableView = tableView;
	}

	public String getApplId() {
		return applid;
	}

	public void setApplId(String applId) {
		this.applid = applId;
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

	public String getSSID() {
		return SSID;
	}

	public void setSSID(String sSID) {
		SSID = sSID;
	}

	public String getDB2Plan() {
		return DB2Plan;
	}

	public void setDB2Plan(String dB2Plan) {
		DB2Plan = dB2Plan;
	}

	public char getDB2Planver() {
		return DB2Planver;
	}

	public void setDB2Planver(char dB2Planver) {
		DB2Planver = dB2Planver;
	}

	public String getDBRM() {
		return DBRM;
	}

	public void setDBRM(String dBRM) {
		DBRM = dBRM;
	}

	public char getDBRMVersion() {
		return DBRMVersion;
	}

	public void setDBRMVersion(char dBRMVersion) {
		DBRMVersion = dBRMVersion;
	}

	public int getStmt() {
		return Stmt;
	}

	public void setStmt(int stmt) {
		Stmt = stmt;
	}

	public String getVerb() {
		return Verb;
	}

	public void setVerb(String verb) {
		Verb = verb;
	}

	public String getOwner() {
		return Owner;
	}

	public void setOwner(String owner) {
		Owner = owner;
	}

	public String getTableView() {
		return tableView;
	}

	public void setTableView(String tableView) {
		this.tableView = tableView;
	}

	@Override
	public String toString() {
		return "ProgDb2Dto [ applId=" + applid + ", cpuId=" + cpuId + ", jobName=" + jobName + ", procName=" + procName
				+ ", progName=" + progName + ", procRefCt=" + procRefCt + ", progRefCt=" + progRefCt + ", source="
				+ source + ", SSID=" + SSID + ", DB2Plan=" + DB2Plan + ", DB2Planver=" + DB2Planver + ", DBRM=" + DBRM
				+ ", DBRMVersion=" + DBRMVersion + ", Stmt=" + Stmt + ", Verb=" + Verb + ", Owner=" + Owner
				+ ", tableView=" + tableView + "]";
	}

}
