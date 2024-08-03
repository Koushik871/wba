package com.dxc.wba.xref.dto;

import java.util.List;

import com.dxc.wba.xref.dbmodel.DB2PlanEntity;
import com.dxc.wba.xref.dbmodel.XrefProcJobProgm;

public class CombineData {

	List<XrefProcJobProgm> program;
	List<DB2PlanEntity> tableView;

	public List<XrefProcJobProgm> getProgram() {
		return program;
	}

	public void setProgram(List<XrefProcJobProgm> program) {
		this.program = program;
	}

	public List<DB2PlanEntity> getTableView() {
		return tableView;
	}

	public void setTableView(List<DB2PlanEntity> tableView) {
		this.tableView = tableView;
	}

}
