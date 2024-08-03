package com.dxc.wba.xref.ddentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dd_dd_MemberCategory", schema = "dd")
public class MemberCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mem_id;

	private int Lno;
	private String memCategory;

	public int getLno() {
		return Lno;
	}

	public void setLno(int lno) {
		Lno = lno;
	}

	public String getMemCategory() {
		return memCategory;
	}

	public void setMemCategory(String memCategory) {
		this.memCategory = memCategory;
	}

}