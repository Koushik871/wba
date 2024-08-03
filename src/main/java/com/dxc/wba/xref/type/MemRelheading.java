package com.dxc.wba.xref.type;

import java.util.ArrayList;
import java.util.List;

import com.dxc.wba.xref.dto.MemBrowseDTO;

public class MemRelheading  {


	
	private List<MemBrowseDTO> inputs = new ArrayList<>();
	private List<MemBrowseDTO> outputs = new ArrayList<>();
	private List<MemBrowseDTO>  updates=new ArrayList<>();
	public List<MemBrowseDTO> getInputs() {
		return inputs;
	}
	public void setInputs(List<MemBrowseDTO> inputs) {
		this.inputs = inputs;
	}
	public List<MemBrowseDTO> getOutputs() {
		return outputs;
	}
	public void setOutputs(List<MemBrowseDTO> outputs) {
		this.outputs = outputs;
	}
	public List<MemBrowseDTO> getUpdates() {
		return updates;
	}
	public void setUpdates(List<MemBrowseDTO> updates) {
		this.updates = updates;
	}
	
	

	

	


}
