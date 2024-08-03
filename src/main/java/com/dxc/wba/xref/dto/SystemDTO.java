package com.dxc.wba.xref.dto;

import java.util.ArrayList;
import java.util.List;

public class SystemDTO {

	private List<String> DESCRIPTION = new ArrayList<>();
	private List<String> ADMINISTRATIVE_DATA = new ArrayList<>();
	private List<String> AUTHOR = new ArrayList<>();
	private List<String> COMMENT = new ArrayList<>();
	private List<String> NOTE = new ArrayList<>();
	private List<String> FREQ_PROCESS_MODE = new ArrayList<>();
	private List<String> FREQ_PROCESS_DETAILS = new ArrayList<>();
	private List<String> FREQ_PROCESS_INTERVAL = new ArrayList<>();
	private List<String> ALIAS = new ArrayList<>();
	private List<String> CATALOGUE = new ArrayList<>();

	public List<String> getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(List<String> dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public List<String> getADMINISTRATIVE_DATA() {
		return ADMINISTRATIVE_DATA;
	}

	public void setADMINISTRATIVE_DATA(List<String> aDMINISTRATIVE_DATA) {
		ADMINISTRATIVE_DATA = aDMINISTRATIVE_DATA;
	}

	public List<String> getAUTHOR() {
		return AUTHOR;
	}

	public void setAUTHOR(List<String> aUTHOR) {
		AUTHOR = aUTHOR;
	}

	public List<String> getCOMMENT() {
		return COMMENT;
	}

	public void setCOMMENT(List<String> cOMMENT) {
		COMMENT = cOMMENT;
	}

	public List<String> getNOTE() {
		return NOTE;
	}

	public void setNOTE(List<String> nOTE) {
		NOTE = nOTE;
	}

	public List<String> getFREQ_PROCESS_MODE() {
		return FREQ_PROCESS_MODE;
	}

	public void setFREQ_PROCESS_MODE(List<String> fREQ_PROCESS_MODE) {
		FREQ_PROCESS_MODE = fREQ_PROCESS_MODE;
	}

	public List<String> getFREQ_PROCESS_DETAILS() {
		return FREQ_PROCESS_DETAILS;
	}

	public void setFREQ_PROCESS_DETAILS(List<String> fREQ_PROCESS_DETAILS) {
		FREQ_PROCESS_DETAILS = fREQ_PROCESS_DETAILS;
	}

	public List<String> getFREQ_PROCESS_INTERVAL() {
		return FREQ_PROCESS_INTERVAL;
	}

	public void setFREQ_PROCESS_INTERVAL(List<String> fREQ_PROCESS_INTERVAL) {
		FREQ_PROCESS_INTERVAL = fREQ_PROCESS_INTERVAL;
	}

	

	public List<String> getALIAS() {
		return ALIAS;
	}

	public void setALIAS(List<String> aLIAS) {
		ALIAS = aLIAS;
	}

	public List<String> getCATALOGUE() {
		return CATALOGUE;
	}

	public void setCATALOGUE(List<String> cATALOGUE) {
		CATALOGUE = cATALOGUE;
	}

}
