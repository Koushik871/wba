package com.dxc.wba.xref.dto;

import java.util.List;

public class OptionalPlanJson {
	
	
    private String fieldName;
    private String fieldValue;
    private String selectedPlanType;
    private List<String> selectedProcessingType;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public String getSelectedPlanType() {
		return selectedPlanType;
	}
	public void setSelectedPlanType(String selectedPlanType) {
		this.selectedPlanType = selectedPlanType;
	}
	public List<String> getSelectedProcessingType() {
		return selectedProcessingType;
	}
	public void setSelectedProcessingType(List<String> selectedProcessingType) {
		this.selectedProcessingType = selectedProcessingType;
	}
    
    
	
	

}
