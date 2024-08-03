package com.dxc.wba.xref.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Xref_Table_view", schema = "xref")
public class TableViewEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Seq_num;

	@Column(name = "MainframeDomain")
	private String mainframeDomain;
	@Column(name = "SSID")
	private String SSID;
	@Column(name = "DB2Plan")
	private String db2plan;
	@Column(name = "DB2Planver")
	private char DB2Planver;
	@Column(name = "DBRM")
	private String DBRM;
	@Column(name = "DBRMVersion")
	private char DBRMVersion;
	@Column(name = "Stmt")
	private int Stmt;
	@Column(name = "Verb")
	private String Verb;
	@Column(name = "Owner")
	private String Owner;
	@Column(name = "tableView")
	private String tableView;
	@Column(name = "Timestamps")
	private String Timestamps;

	public String getMainframeDomain() {
		return mainframeDomain;
	}

	public void setMainframeDomain(String mainframeDomain) {
		this.mainframeDomain = mainframeDomain;
	}

	public String getSSID() {
		return SSID;
	}

	public void setSSID(String sSID) {
		SSID = sSID;
	}

	public Long getSeq_num() {
		return Seq_num;
	}

	public void setSeq_num(Long seq_num) {
		Seq_num = seq_num;
	}

	public String getDb2plan() {
		return db2plan;
	}

	public void setDb2plan(String db2plan) {
		this.db2plan = db2plan;
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

	public String getTimestamps() {
		return Timestamps;
	}

	public void setTimestamps(String timestamps) {
		Timestamps = timestamps;
	}

	public String getTableView() {
		return tableView;
	}

	public void setTableView(String tableView) {
		this.tableView = tableView;
	}

	@Override
	public String toString() {
		return "TableViewEntity [Seq_num=" + Seq_num + ", mainframeDomain=" + mainframeDomain + ", SSID=" + SSID
				+ ", db2plan=" + db2plan + ", DB2Planver=" + DB2Planver + ", DBRM=" + DBRM + ", DBRMVersion="
				+ DBRMVersion + ", Stmt=" + Stmt + ", Verb=" + Verb + ", Owner=" + Owner + ", tableView=" + tableView
				+ ", Timestamps=" + Timestamps + "]";
	}

}