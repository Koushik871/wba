package com.dxc.wba.xref.type;

import java.util.List;

import com.dxc.wba.xref.dto.MemberDto;

public class DDCategoryUpdateJson {
	private List<String> category;
	private List<MemberDto> data;

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public List<MemberDto> getData() {
		return data;
	}

	public void setData(List<MemberDto> data) {
		this.data = data;
	}

}
