package com.dxc.wba.xref.ddentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ddlgeneration", schema = "dd")
public class DDLGeneration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ddl_id;
	private int row_no;

	private String db2SubSystem;
	private String ddlGenTableName;
	private String ddlGenSchemaName;
	private String ddlGenDatabaseName;
	private String ddlStorageGroup;
	private String ddlTablespace;
	private String ddlColumnName;
	private String ddlColSeq;
	private String ddlColType;
	private String ddlColLength;
	private String ddlPrimarykeyseq;
	private String ddlForeignkey;

	public int getRow_no() {
		return row_no;
	}

	public void setRow_no(int row_no) {
		this.row_no = row_no;
	}

	public String getDb2SubSystem() {
		return db2SubSystem;
	}

	public void setDb2SubSystem(String db2SubSystem) {
		this.db2SubSystem = db2SubSystem;
	}

	public String getDdlGenTableName() {
		return ddlGenTableName;
	}

	public void setDdlGenTableName(String ddlGenTableName) {
		this.ddlGenTableName = ddlGenTableName;
	}

	public String getDdlGenSchemaName() {
		return ddlGenSchemaName;
	}

	public void setDdlGenSchemaName(String ddlGenSchemaName) {
		this.ddlGenSchemaName = ddlGenSchemaName;
	}

	public String getDdlGenDatabaseName() {
		return ddlGenDatabaseName;
	}

	public void setDdlGenDatabaseName(String ddlGenDatabaseName) {
		this.ddlGenDatabaseName = ddlGenDatabaseName;
	}

	public String getDdlStorageGroup() {
		return ddlStorageGroup;
	}

	public void setDdlStorageGroup(String ddlStorageGroup) {
		this.ddlStorageGroup = ddlStorageGroup;
	}

	public String getDdlTablespace() {
		return ddlTablespace;
	}

	public void setDdlTablespace(String ddlTablespace) {
		this.ddlTablespace = ddlTablespace;
	}

	public String getDdlColumnName() {
		return ddlColumnName;
	}

	public void setDdlColumnName(String ddlColumnName) {
		this.ddlColumnName = ddlColumnName;
	}

	public String getDdlColSeq() {
		return ddlColSeq;
	}

	public void setDdlColSeq(String ddlColSeq) {
		this.ddlColSeq = ddlColSeq;
	}

	public String getDdlColType() {
		return ddlColType;
	}

	public void setDdlColType(String ddlColType) {
		this.ddlColType = ddlColType;
	}

	public String getDdlColLength() {
		return ddlColLength;
	}

	public void setDdlColLength(String ddlColLength) {
		this.ddlColLength = ddlColLength;
	}

	public String getDdlPrimarykeyseq() {
		return ddlPrimarykeyseq;
	}

	public void setDdlPrimarykeyseq(String ddlPrimarykeyseq) {
		this.ddlPrimarykeyseq = ddlPrimarykeyseq;
	}

	public String getDdlForeignkey() {
		return ddlForeignkey;
	}

	public void setDdlForeignkey(String ddlForeignkey) {
		this.ddlForeignkey = ddlForeignkey;
	}

}
