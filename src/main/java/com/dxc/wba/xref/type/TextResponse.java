package com.dxc.wba.xref.type;

import java.util.ArrayList;
import java.util.List;

public class TextResponse {

	private List<HeaderType> config;

	private List<TextResponseData> data;

	public TextResponse() {
		List<HeaderType> ab = new ArrayList<>();
		HeaderType headerType = new HeaderType();
		headerType.setHeader("Member");
		headerType.setKey("memberName");
//		HeaderType headerType1 = new HeaderType();
//		headerType1.setHeader("Dataset Name");
//		headerType1.setKey("datasetName");

		ab.add(headerType);
//		ab.add(headerType1);
		this.config = ab;
	}

	public List<HeaderType> getConfig() {
		return config;
	}

	public void setConfig(List<HeaderType> config) {
		this.config = config;
	}

	public List<TextResponseData> getData() {
		return data;
	}

	public void setData(List<TextResponseData> data) {
		this.data = data;
	}

	public void setMessage(String string) {
		// TODO Auto-generated method stub

	}

}
