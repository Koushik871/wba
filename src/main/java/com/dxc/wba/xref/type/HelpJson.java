package com.dxc.wba.xref.type;

import java.util.List;

public class HelpJson {
	private String screenName;
	private List<String> data;

	public HelpJson() {
	}

	public HelpJson(String screenName, List<String> data) {
		this.screenName = screenName;
		this.data = data;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}
}
