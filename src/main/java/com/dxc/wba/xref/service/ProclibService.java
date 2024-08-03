package com.dxc.wba.xref.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dxc.wba.xref.dbmodel.CatProcModel;
import com.dxc.wba.xref.dbmodel.ProclibMembers;
import com.dxc.wba.xref.dbmodel.XrefProcJobProgm;
import com.dxc.wba.xref.repository.CatProcRepository;
import com.dxc.wba.xref.repository.ProclibRepository;
import com.dxc.wba.xref.repository.XrefProcJobProgmRepository;
import com.dxc.wba.xref.type.CatProcJson;
import com.dxc.wba.xref.type.HeaderType;
import com.dxc.wba.xref.type.JobProgJson;
import com.dxc.wba.xref.type.ProcNameJson;
import com.dxc.wba.xref.type.ProclibDatajson;
import com.dxc.wba.xref.type.TextResponse;
import com.dxc.wba.xref.type.TextResponseData;

@Service
public class ProclibService {

	@Autowired
	private CatProcRepository catProcRepository;

	@Autowired
	private ProclibRepository proclibRepository;

	@Autowired
	XrefProcJobProgmRepository xrefProcJobProgmRepository;

	public Object getProclibFiles(String memberName, String mainframe_domain) {

		List<ProclibMembers> proclibMembers = null;
		TextResponse response = new TextResponse();
		Object val = null;
		List<Map<String, Object>> d = proclibRepository.findDatsetNameDallas(memberName);

		if (mainframe_domain.equals("DALLAS") || mainframe_domain.equals("Dallas")) {
			// filter by DALLAS
			proclibMembers = proclibRepository.findMemberNameByDallas(memberName);
			List<String> datasetlib = proclibMembers.stream().map(ProclibMembers::getDatasetName).distinct()
					.collect(Collectors.toList());

			if (CollectionUtils.isEmpty(proclibMembers)) {
				List<String> a = proclibRepository.findbymemberNameDallas(memberName);
				List<Map<String, Object>> b = proclibRepository.findbymemberNameandDatasetDallas(memberName);

				List<Map<String, String>> configList = new ArrayList<>();

				Map<String, String> memberName1 = new LinkedHashMap<>();
				memberName1.put("header", "Member Name");
				memberName1.put("key", "membername");
				configList.add(memberName1);

				Map<String, String> setLastUpdateTime = new LinkedHashMap<>();
				setLastUpdateTime.put("header", "Dataset Name");
				setLastUpdateTime.put("key", "dataset_name");
				configList.add(setLastUpdateTime);

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("config", configList);
				jsonResponse.put("data", b);

				val = jsonResponse;

			} else if (datasetlib.size() == 1) {

				List<String> list = new ArrayList<>();

				ProclibDatajson proc = new ProclibDatajson();

				proc.setData(list);
				for (ProclibMembers value : proclibMembers) {

					proc.setDatasetName(value.getDatasetName());
				}
				for (ProclibMembers value : proclibMembers) {

					list.add(value.getLineText().stripTrailing());
				}

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("data", proc);

				val = jsonResponse;

			} else {
				List<String> a = proclibRepository.findbymemberNameDallas(memberName);
				List<Map<String, Object>> b = proclibRepository.findbymemberNameandDatasetDallas(memberName);
				List<Map<String, String>> configList = new ArrayList<>();

				// Add header and key entries directly
				Map<String, String> memberName1 = new LinkedHashMap<>();
				memberName1.put("header", "Member Name");
				memberName1.put("key", "membername");
				configList.add(memberName1);

				Map<String, String> setLastUpdateTime = new LinkedHashMap<>();
				setLastUpdateTime.put("header", "Dataset Name");
				setLastUpdateTime.put("key", "dataset_name");
				configList.add(setLastUpdateTime);

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("config", configList);
				jsonResponse.put("data", b);
				val = jsonResponse;

			}
		} else if (mainframe_domain.equals("PROD") || mainframe_domain.equals("Prod")) {
			// filter by PROD
			proclibMembers = proclibRepository.findMemberNameByProd(memberName);
			List<String> datasetlib = proclibMembers.stream().map(ProclibMembers::getDatasetName).distinct()
					.collect(Collectors.toList());
			if (CollectionUtils.isEmpty(proclibMembers)) {
				List<Map<String, Object>> b = proclibRepository.findbymemberNameandDatasetProd(memberName);

				List<Map<String, String>> configList = new ArrayList<>();

				// Add header and key entries directly
				Map<String, String> memberName1 = new LinkedHashMap<>();
				memberName1.put("header", "Member Name");
				memberName1.put("key", "membername");
				configList.add(memberName1);

				Map<String, String> setLastUpdateTime = new LinkedHashMap<>();
				setLastUpdateTime.put("header", "Dataset Name");
				setLastUpdateTime.put("key", "dataset_name");
				configList.add(setLastUpdateTime);

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("config", configList);
				jsonResponse.put("data", b);
				val = jsonResponse;

			}

			if (datasetlib.size() == 1) {

				List<String> list = new ArrayList<>();

				ProclibDatajson proc = new ProclibDatajson();

				proc.setData(list);
				for (ProclibMembers value : proclibMembers) {

					proc.setDatasetName(value.getDatasetName());
				}
				for (ProclibMembers value : proclibMembers) {

					list.add(value.getLineText().stripTrailing());
				}

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("data", proc);

				val = jsonResponse;

			} else {

				List<Map<String, Object>> b = proclibRepository.findbymemberNameandDatasetProd(memberName);

				List<Map<String, String>> configList = new ArrayList<>();

				// Add header and key entries directly
				Map<String, String> memberName1 = new LinkedHashMap<>();
				memberName1.put("header", "Member Name");
				memberName1.put("key", "membername");
				configList.add(memberName1);

				Map<String, String> setLastUpdateTime = new LinkedHashMap<>();
				setLastUpdateTime.put("header", "Dataset Name");
				setLastUpdateTime.put("key", "dataset_name");
				configList.add(setLastUpdateTime);

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("config", configList);
				jsonResponse.put("data", b);
				val = jsonResponse;

			}
		}

		return val;
	}

	public JobProgJson getCatProcByProcName1(String procName, String mainframeDomain) {
		List<XrefProcJobProgm> response = new ArrayList<>();
		if ("PROD".equalsIgnoreCase(mainframeDomain) || "Prod".equalsIgnoreCase(mainframeDomain)) {
			response = xrefProcJobProgmRepository.getCatProcByProcNameByWildcardsAndMainframeDomain(procName, "Prod");
		} else if ("DALLAS".equalsIgnoreCase(mainframeDomain) || "Dallas".equalsIgnoreCase(mainframeDomain)) {
			response = xrefProcJobProgmRepository.getCatProcByProcNameByWildcardsAndMainframeDomain(procName, "Dallas");
		}
		response = response.stream().filter(x -> !x.getAppId().equalsIgnoreCase("N/A")).collect(Collectors.toList());
		response.sort(Comparator.comparing(XrefProcJobProgm::getAppId));

		JobProgJson catProcJson = new JobProgJson();
		catProcJson.setConfig(procheaders());

		catProcJson.setData(response);
		System.out.println("retrieving the list of Jobprogram ========" + procName);
		return catProcJson;
	}

	public JobProgJson getCatProcsByProcName(String procName, String mainframeDomain) {
		List<XrefProcJobProgm> response;
		if ("PROD".equalsIgnoreCase(mainframeDomain) || "Prod".equalsIgnoreCase(mainframeDomain)) {
			response = xrefProcJobProgmRepository.getCatProcByProcNamesAndMainframeDomain(procName, "Prod");
		} else if ("DALLAS".equalsIgnoreCase(mainframeDomain) || "Dallas".equalsIgnoreCase(mainframeDomain)) {
			response = xrefProcJobProgmRepository.getCatProcByProcNamesAndMainframeDomain(procName, "Dallas");
		} else {
			response = null;
		}
		response = response.stream().filter(x -> !x.getAppId().equalsIgnoreCase("N/A")).collect(Collectors.toList());
		response.sort(Comparator.comparing(XrefProcJobProgm::getAppId));

		JobProgJson catProcJson = new JobProgJson();
		catProcJson.setConfig(procheaders());
		catProcJson.setData(response);
		System.out.println("retrieving the list of Jobprogram ========" + procName);
		return catProcJson;
	}

	public CatProcJson getCatProcByProcName(String procName) {

		CatProcJson catProcJson = new CatProcJson();
		try {

			List<CatProcModel> response = catProcRepository.getCatProcByProcName(procName);

			List<HeaderType> headerTypeList = new ArrayList<>();

			HeaderType headerType = new HeaderType();
			headerType.setHeader("Application Name");
			headerType.setKey("appId");

			HeaderType headerType1 = new HeaderType();
			headerType1.setHeader("Operation Id");
			headerType1.setKey("cpuId");

			HeaderType headerType2 = new HeaderType();
			headerType2.setHeader("Job Name");
			headerType2.setKey("jobName");

			HeaderType headerType3 = new HeaderType();
			headerType3.setHeader("Procedure Name");
			headerType3.setKey("procName");

			HeaderType headerType4 = new HeaderType();
			headerType4.setHeader("No of Refs");
			headerType4.setKey("procRefCt");

			HeaderType headerType5 = new HeaderType();
			headerType5.setHeader("Program Name");
			headerType5.setKey("progName");

			HeaderType headerType6 = new HeaderType();
			headerType6.setHeader("No of Refs");
			headerType6.setKey("progRefCt");

			HeaderType headerType7 = new HeaderType();
			headerType7.setHeader("Source");
			headerType7.setKey("source");

			headerTypeList.add(headerType);
			headerTypeList.add(headerType1);
			headerTypeList.add(headerType2);
			headerTypeList.add(headerType3);
			headerTypeList.add(headerType4);
			headerTypeList.add(headerType5);
			headerTypeList.add(headerType6);
			headerTypeList.add(headerType7);

			catProcJson.setConfig(headerTypeList);
			catProcJson.setData(response);

			System.out.println("reterving the list of Jobprogram ========" + procName);
			return catProcJson;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public CatProcJson getCatProcByProcName1(String procName) {

		List<CatProcModel> response = catProcRepository.getCatProcByProcNameByWildcards(procName);
		CatProcJson catProcJson = new CatProcJson();

		catProcJson.setConfig(procheaders());
		catProcJson.setData(response);

		System.out.println("reterving the list of Jobprogram ========" + procName);
		return catProcJson;
	}

	public CatProcJson getCatProcsByProcName(String procName) {
		List<CatProcModel> response = catProcRepository.getCatProcByProcNames(procName);
		CatProcJson catProcJson = new CatProcJson();

		catProcJson.setConfig(procheaders());
		catProcJson.setData(response);

		System.out.println("reterving the list of Jobprogram ========" + procName);
		return catProcJson;
	}

	public ProcNameJson getProcName(String procName) {
		ProcNameJson proc = new ProcNameJson();
		try {

			List<Map<String, Object>> response = new ArrayList<>();
			response = catProcRepository.getProcName(procName);
			getprocheader();
			proc.setConfig(getprocheader());
			proc.setData(response);

			return proc;

		} catch (Exception e) {
			return null;
		}

	}

	public ProcNameJson getProcNameWithAsterisk(String procName) {
		ProcNameJson proc = new ProcNameJson();
		try {

			List<Map<String, Object>> response = new ArrayList<>();
			response = catProcRepository.getProcNamewithwildcards(procName);
			getprocheader();
			proc.setConfig(getprocheader());
			proc.setData(response);

			return proc;

		} catch (Exception e) {
			return null;
		}
	}

	public Object getProclibFiles(String memberName) {

		List<ProclibMembers> proclibMembers = null;
		TextResponse response = new TextResponse();
		Object val = null;

		proclibMembers = proclibRepository.findMemberName(memberName);

		if (CollectionUtils.isEmpty(proclibMembers)) {
			List<String> a = proclibRepository.findbymemberName(memberName);
			List<TextResponseData> datas = new ArrayList<>();
			for (String value : a) {
				TextResponseData data = new TextResponseData();
				data.setMemberName(value);
				datas.add(data);
			}

			response.setData(datas);
			val = response;

		} else {

			List<String> list = new ArrayList<>();

			for (ProclibMembers value : proclibMembers) {

				list.add(value.getLineText().stripTrailing());
			}

			val = list;

		}

		return val;
	}

	private List<HeaderType> getprocheader() {

		List<HeaderType> headerTypeList = new ArrayList<>();

		HeaderType headerType7 = new HeaderType();
		headerType7.setHeader("Member");
		headerType7.setKey("member");

		headerTypeList.add(headerType7);

		return headerTypeList;
	}

	private List<HeaderType> procheaders() {
		List<HeaderType> headerTypeList = new ArrayList<>();

		HeaderType headerType = new HeaderType();
		headerType.setHeader("Application Name");
		headerType.setKey("appId");

		HeaderType headerType1 = new HeaderType();
		headerType1.setHeader("Operation Id");
		headerType1.setKey("cpuId");

		HeaderType headerType2 = new HeaderType();
		headerType2.setHeader("Job Name");
		headerType2.setKey("jobName");

		HeaderType headerType3 = new HeaderType();
		headerType3.setHeader("Procedure Name");
		headerType3.setKey("procName");

		HeaderType headerType4 = new HeaderType();
		headerType4.setHeader("No of Refs");
		headerType4.setKey("procRefCt");

		HeaderType headerType5 = new HeaderType();
		headerType5.setHeader("Program Name");
		headerType5.setKey("progName");

		HeaderType headerType6 = new HeaderType();
		headerType6.setHeader("No of Refs");
		headerType6.setKey("progRefCt");

		HeaderType headerType7 = new HeaderType();
		headerType7.setHeader("Source");
		headerType7.setKey("source");

		headerTypeList.add(headerType);
		headerTypeList.add(headerType1);
		headerTypeList.add(headerType2);
		headerTypeList.add(headerType3);
		headerTypeList.add(headerType4);
		headerTypeList.add(headerType5);
		headerTypeList.add(headerType6);
		headerTypeList.add(headerType7);
		return headerTypeList;

	}

	public Object getProclibFiless(String membername, String datasetLib, String mainframeDomain) {

		List<ProclibMembers> proclibMembers = null;
		TextResponse response = new TextResponse();
		Object val = null;
		List<Map<String, Object>> d = proclibRepository.findDatsetNameDallas(membername);

		if (mainframeDomain.equals("DALLAS") || mainframeDomain.equals("Dallas")) {
			// filter by DALLAS
			proclibMembers = proclibRepository.findMemberNameByDallas(membername);
			List<String> datasetlib = proclibMembers.stream().map(ProclibMembers::getDatasetName).distinct()
					.collect(Collectors.toList());
			if (CollectionUtils.isEmpty(proclibMembers)) {
				List<String> a = proclibRepository.findbymemberNameDallas(membername);
				List<Map<String, Object>> memberList = proclibRepository.findbymemberNameandDatasetDallas(membername);

				List<Map<String, String>> configList = new ArrayList<>();

				// Add header and key entries directly
				Map<String, String> memberName = new LinkedHashMap<>();
				memberName.put("header", "Member Name");
				memberName.put("key", "membername");
				configList.add(memberName);

				Map<String, String> dataset_name = new LinkedHashMap<>();
				dataset_name.put("header", "Dataset Name");
				dataset_name.put("key", "dataset_name");
				configList.add(dataset_name);

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("config", configList);
				jsonResponse.put("data", memberList);
				val = jsonResponse;

			} else

			if (datasetlib.size() == 1) {

				List<String> list = new ArrayList<>();

				ProclibDatajson proc = new ProclibDatajson();

				proc.setData(list);
				for (ProclibMembers value : proclibMembers) {

					proc.setDatasetName(value.getDatasetName());
				}
				for (ProclibMembers value : proclibMembers) {

					list.add(value.getLineText().stripTrailing());
				}

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("data", proc);

				val = jsonResponse;

			} else if (!datasetLib.isEmpty()) {
				List<ProclibMembers> proclibMemberss = new ArrayList<>();
				proclibMemberss = proclibRepository.findMemberNameByDallass(membername, datasetLib);
				List<String> list = new ArrayList<>();

				ProclibDatajson proc = new ProclibDatajson();

				proc.setData(list);
				for (ProclibMembers value : proclibMemberss) {

					proc.setDatasetName(value.getDatasetName());
				}
				for (ProclibMembers value : proclibMemberss) {

					list.add(value.getLineText().stripTrailing());
				}

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("data", proc);

				val = jsonResponse;

			} else {
				List<String> a = proclibRepository.findbymemberNameDallas(membername);
				List<Map<String, Object>> memberList = proclibRepository.findbymemberNameandDatasetDallas(membername);
				List<TextResponseData> datas = new ArrayList<>();
				for (String value : a) {
					TextResponseData data = new TextResponseData();
					data.setMemberName(value);
					datas.add(data);
				}
				List<Map<String, String>> configList = new ArrayList<>();

				// Add header and key entries directly
				Map<String, String> memberName = new LinkedHashMap<>();
				memberName.put("header", "Member Name");
				memberName.put("key", "membername");
				configList.add(memberName);

				Map<String, String> dataset_name = new LinkedHashMap<>();
				dataset_name.put("header", "Dataset Name");
				dataset_name.put("key", "dataset_name");
				configList.add(dataset_name);

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("config", configList);
				jsonResponse.put("data", memberList);
				response.setData(datas);
				val = jsonResponse;

			}
		} else if (mainframeDomain.equals("PROD") || mainframeDomain.equals("Prod")) {
			// filter by PROD
			proclibMembers = proclibRepository.findMemberNameByProd(membername);
			List<String> datasetlib = proclibMembers.stream().map(ProclibMembers::getDatasetName).distinct()
					.collect(Collectors.toList());
			if (CollectionUtils.isEmpty(proclibMembers)) {
				List<Map<String, Object>> memberList = proclibRepository.findbymemberNameandDatasetProd(membername);

				List<Map<String, String>> configList = new ArrayList<>();

				// Add header and key entries directly
				Map<String, String> memberName = new LinkedHashMap<>();
				memberName.put("header", "Member Name");
				memberName.put("key", "membername");
				configList.add(memberName);

				Map<String, String> dataset_name = new LinkedHashMap<>();
				dataset_name.put("header", "Dataset Name");
				dataset_name.put("key", "dataset_name");
				configList.add(dataset_name);

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("config", configList);
				jsonResponse.put("data", memberList);
				val = jsonResponse;

			} else if (datasetlib.size() == 1) {

				List<String> list = new ArrayList<>();

				ProclibDatajson proc = new ProclibDatajson();

				proc.setData(list);
				for (ProclibMembers value : proclibMembers) {

					proc.setDatasetName(value.getDatasetName());
				}
				for (ProclibMembers value : proclibMembers) {

					list.add(value.getLineText().stripTrailing());
				}

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("data", proc);

				val = jsonResponse;

			} else if (!datasetLib.isEmpty()) {
				List<ProclibMembers> proclibMemberss = new ArrayList<>();
				proclibMemberss = proclibRepository.findMemberNameByProdd(membername, datasetLib);
				List<String> list = new ArrayList<>();

				ProclibDatajson proc = new ProclibDatajson();

				proc.setData(list);
				for (ProclibMembers value : proclibMemberss) {

					proc.setDatasetName(value.getDatasetName());
				}
				for (ProclibMembers value : proclibMemberss) {

					list.add(value.getLineText().stripTrailing());
				}

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("data", proc);

				val = jsonResponse;

			} else {
				List<String> a = proclibRepository.findbymemberNameDallas(membername);
				List<Map<String, Object>> b = proclibRepository.findbymemberNameandDatasetProd(membername);
				List<TextResponseData> datas = new ArrayList<>();
				for (String value : a) {
					TextResponseData data = new TextResponseData();
					data.setMemberName(value);
					datas.add(data);
				}
				List<Map<String, String>> configList = new ArrayList<>();

				// Add header and key entries directly
				Map<String, String> memberName = new LinkedHashMap<>();
				memberName.put("header", "Member Name");
				memberName.put("key", "membername");
				configList.add(memberName);

				Map<String, String> dataset_name = new LinkedHashMap<>();
				dataset_name.put("header", "Dataset Name");
				dataset_name.put("key", "dataset_name");
				configList.add(dataset_name);

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("config", configList);
				jsonResponse.put("data", b);
				response.setData(datas);
				val = jsonResponse;

			}

		}

		return val;

	}

	public Object getProclibwildcard(String memberName, String mainframe_domain) {

		List<ProclibMembers> proclibMembers = null;
		Object val = null;

		if (mainframe_domain.equals("DALLAS") || mainframe_domain.equals("Dallas")) {
			List<Map<String, Object>> b = proclibRepository.findbymemberandDatasetDallasDrilldown(memberName);
			List<Map<String, String>> configList = new ArrayList<>();

			// Add header and key entries directly
			Map<String, String> memberName1 = new LinkedHashMap<>();
			memberName1.put("header", "Member Name");
			memberName1.put("key", "membername");
			configList.add(memberName1);

			Map<String, String> setLastUpdateTime = new LinkedHashMap<>();
			setLastUpdateTime.put("header", "Dataset Name");
			setLastUpdateTime.put("key", "dataset_name");
			configList.add(setLastUpdateTime);

			Map<String, Object> jsonResponse = new LinkedHashMap<>();
			jsonResponse.put("config", configList);
			jsonResponse.put("data", b);
			val = jsonResponse;

		} else if (mainframe_domain.equals("PROD") || mainframe_domain.equals("Prod")) {

			List<Map<String, Object>> b = proclibRepository.findbymemberandDatasetProdDrilldown(memberName);

			List<Map<String, String>> configList = new ArrayList<>();

			Map<String, String> memberName1 = new LinkedHashMap<>();
			memberName1.put("header", "Member Name");
			memberName1.put("key", "membername");
			configList.add(memberName1);

			Map<String, String> setLastUpdateTime = new LinkedHashMap<>();
			setLastUpdateTime.put("header", "Dataset Name");
			setLastUpdateTime.put("key", "dataset_name");
			configList.add(setLastUpdateTime);

			Map<String, Object> jsonResponse = new LinkedHashMap<>();
			jsonResponse.put("config", configList);
			jsonResponse.put("data", b);
			val = jsonResponse;

		}

		return val;

	}

}