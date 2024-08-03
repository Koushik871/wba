package com.dxc.wba.xref.type;

import java.util.ArrayList;
import java.util.List;

import com.dxc.wba.xref.dto.MemBrowseDTO;

public class MemberHeadingType {


	private List<MemBrowseDTO> catalogue = new ArrayList<>();
	private List<MemBrowseDTO> comment = new ArrayList<>();
	private List<MemBrowseDTO> description = new ArrayList<>();
	private List<MemBrowseDTO> administrativeData = new ArrayList<>();
	private List<MemBrowseDTO> language = new ArrayList<>();
	private List<MemBrowseDTO> note = new ArrayList<>();
	private List<MemBrowseDTO> alias = new ArrayList<>();

	public List<MemBrowseDTO> getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(List<MemBrowseDTO> catalogue) {
		this.catalogue = catalogue;
	}

	public List<MemBrowseDTO> getComment() {
		return comment;
	}

	public void setComment(List<MemBrowseDTO> comment) {
		this.comment = comment;
	}

	public List<MemBrowseDTO> getDescription() {
		return description;
	}

	public void setDescription(List<MemBrowseDTO> description) {
		this.description = description;
	}

	public List<MemBrowseDTO> getAdministrativeData() {
		return administrativeData;
	}

	public void setAdministrativeData(List<MemBrowseDTO> administrativeData) {
		this.administrativeData = administrativeData;
	}

	public List<MemBrowseDTO> getLanguage() {
		return language;
	}

	public void setLanguage(List<MemBrowseDTO> language) {
		this.language = language;
	}

	public List<MemBrowseDTO> getNote() {
		return note;
	}

	public void setNote(List<MemBrowseDTO> note) {
		this.note = note;
	}

	public List<MemBrowseDTO> getAlias() {
		return alias;
	}

	public void setAlias(List<MemBrowseDTO> alias) {
		this.alias = alias;
	}

}
