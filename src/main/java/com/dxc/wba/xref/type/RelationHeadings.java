package com.dxc.wba.xref.type;

import java.util.ArrayList;
import java.util.List;

public class RelationHeadings {

	private List<String> inputs = new ArrayList<>();
	private List<String> outputs = new ArrayList<>();
	private List<String> updates = new ArrayList<>();
	public List<String> getInputs() {
		return inputs;
	}
	public void setInputs(List<String> inputs) {
		this.inputs = inputs;
	}
	public List<String> getOutputs() {
		return outputs;
	}
	public void setOutputs(List<String> outputs) {
		this.outputs = outputs;
	}
	public List<String> getUpdates() {
		return updates;
	}
	public void setUpdates(List<String> updates) {
		this.updates = updates;
	}

	

}
