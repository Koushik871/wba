package com.dxc.wba.xref.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.wba.xref.dbmodel.XrefHelp;
import com.dxc.wba.xref.ddentity.DDHelp;
import com.dxc.wba.xref.repository.DDHelpRepository;
import com.dxc.wba.xref.repository.XrefHelpRepository;
import com.dxc.wba.xref.type.HelpJson;

@Service
public class HelpTextService {

	@Autowired
	XrefHelpRepository xreprepo;

	@Autowired
	DDHelpRepository ddrepo;

	public Object getXrefHelpText(String screenName) {
		List<XrefHelp> xrefHelpList = xreprepo.findByScreenName(screenName);
		Object val = null;
		List<String> list = new ArrayList<>();

		for (XrefHelp value : xrefHelpList) {

			list.add(value.getHelpText().stripTrailing());
		}

		HelpJson help = new HelpJson();
		for (XrefHelp value : xrefHelpList) {

			help.setScreenName(value.getScreenName());
		}

//		List<Map<String, String>> configList = new ArrayList<>();

		help.setData(list);
//		// Add header and key entries directly
//		Map<String, String> memberName1 = new LinkedHashMap<>();
//		memberName1.put("header", "Screen Name");
//		memberName1.put("key", "membername");
//		configList.add(memberName1);
//
//		Map<String, Object> jsonResponse = new LinkedHashMap<>();
//		jsonResponse.put("config", configList);
//		jsonResponse.put("data", help);
//		val = jsonResponse;

		val = list;

		return val;

	}

	public Object getDDHelp() {
		List<DDHelp> ddHelpList = ddrepo.findAll();
		ddHelpList.sort(Comparator.comparing(DDHelp::getId));
		Object val = null;
		List<String> list = new ArrayList<>();

		for (DDHelp value : ddHelpList) {

			list.add(value.getHelpText().stripTrailing());
		}

		val = list;

		return val;

	}

	public Object getDDHelpText(String screenName) {
		List<DDHelp> ddHelpList = ddrepo.findByScreenName(screenName);
		Object val = null;
		List<String> list = new ArrayList<>();

		for (DDHelp value : ddHelpList) {

			list.add(value.getHelpText().stripTrailing());
		}

		HelpJson help = new HelpJson();
		for (DDHelp value : ddHelpList) {

			help.setScreenName(value.getScreenName());
		}

//		List<Map<String, String>> configList = new ArrayList<>();

		help.setData(list);
//		// Add header and key entries directly
//		Map<String, String> memberName1 = new LinkedHashMap<>();
//		memberName1.put("header", "Screen Name");
//		memberName1.put("key", "membername");
//		configList.add(memberName1);
//
//		Map<String, Object> jsonResponse = new LinkedHashMap<>();
//		jsonResponse.put("config", configList);
//		jsonResponse.put("data", help);
//		val = jsonResponse;

		val = list;

		return val;

	}

	public Object getXrefFullHelpText() {
		List<XrefHelp> xrefHelpList = xreprepo.findAll();
		Object val = null;
		List<String> list = new ArrayList<>();

		for (XrefHelp value : xrefHelpList) {

			list.add(value.getHelpText().stripTrailing());
		}

		HelpJson help = new HelpJson();
		for (XrefHelp value : xrefHelpList) {

			help.setScreenName(value.getScreenName());
		}

//		List<Map<String, String>> configList = new ArrayList<>();

		help.setData(list);
//		// Add header and key entries directly
//		Map<String, String> memberName1 = new LinkedHashMap<>();
//		memberName1.put("header", "Screen Name");
//		memberName1.put("key", "membername");
//		configList.add(memberName1);
//
//		Map<String, Object> jsonResponse = new LinkedHashMap<>();
//		jsonResponse.put("config", configList);
//		jsonResponse.put("data", help);
//		val = jsonResponse;

		val = list;

		return val;

	}

}
