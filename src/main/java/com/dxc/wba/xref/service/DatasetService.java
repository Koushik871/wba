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

import com.dxc.wba.xref.dbmodel.ParmLibMembers;
import com.dxc.wba.xref.dbmodel.XrefDataset;
import com.dxc.wba.xref.repository.ParmLibRepository;
import com.dxc.wba.xref.repository.XrefDatasetRepository;
import com.dxc.wba.xref.type.ProclibDatajson;
import com.dxc.wba.xref.type.TextResponse;
import com.dxc.wba.xref.type.TextResponseData;

@Service
public class DatasetService {

	@Autowired
	private XrefDatasetRepository xrefDatasetRepository;

	@Autowired
	private ParmLibRepository parmlibrepository;

	public List<XrefDataset> fetchAllDatasetDatawithWildcardAndMainframeDomain(String datasetName,
			String mainframeDomain) {

		return xrefDatasetRepository.fetchAllDatasetDatawithWildcardAndMainframeDomain(datasetName, mainframeDomain);
	}

	public Object findByMembernameandMainframeDomain(String datasetLib, String memberName, String mainframe_domain) {
		TextResponse response = new TextResponse();

		Object val = null;

		try {
			if (datasetLib == null) {
				List<ParmLibMembers> dataList = null;

				if (mainframe_domain.equals("Prod") || mainframe_domain.equals("PROD")) {
					dataList = parmlibrepository.findByMemberNameAndDomain(memberName, "Prod");
				} else if (mainframe_domain.equals("Dallas") || mainframe_domain.equals("DALLAS")) {
					dataList = parmlibrepository.findByMemberNameAndDomain(memberName, "Dallas");
				}

				List<String> datasetlib = dataList.stream().map(ParmLibMembers::getDataset_Lib).distinct()
						.collect(Collectors.toList());
				if (CollectionUtils.isEmpty(dataList)) {
					List<String> a = new ArrayList<>();
					List<Map<String, String>> memberList = new ArrayList<>();
					;
					if (mainframe_domain.equals("Prod") || mainframe_domain.equals("PROD")) {
						a = parmlibrepository.findbymemberNameAndDomain(memberName, "Prod");
						memberList = parmlibrepository.findbymemberNamedatasetlibandMainframeDomain(memberName, "Prod");
					} else if (mainframe_domain.equals("DALLAS") || mainframe_domain.equals("DALLAS")) {
						a = parmlibrepository.findbymemberNameAndDomain(memberName, "Dallas");
						memberList = parmlibrepository.findbymemberNamedatasetlibandMainframeDomain(memberName,
								"Dallas");
					}

					List<TextResponseData> datas = a.stream().map(value -> {
						TextResponseData data = new TextResponseData();
						data.setMemberName(value);
						return data;
					}).collect(Collectors.toList());
					response.setData(datas);
					List<Map<String, String>> configList = new ArrayList<>();

					Map<String, String> membername = new LinkedHashMap<>();
					membername.put("header", "Member Name");
					membername.put("key", "membername");
					configList.add(membername);

					Map<String, String> dataset_lib = new LinkedHashMap<>();
					dataset_lib.put("header", "Dataset Name");
					dataset_lib.put("key", "dataset_lib");
					configList.add(dataset_lib);

					Map<String, Object> jsonResponse = new LinkedHashMap<>();
					jsonResponse.put("config", configList);
					jsonResponse.put("data", memberList);
					response.setData(datas);
					val = jsonResponse;
					return jsonResponse;

				} else if (datasetlib.size() == 1) {
					List<String> list = dataList.stream().map(ParmLibMembers::getLineText).collect(Collectors.toList());

					Comparator<String> comparator = Comparator.comparing(String::toLowerCase);

					List<String> uniqueList = new ArrayList<>();

					for (String s : list) {
						boolean duplicateFound = false;
						for (String u : uniqueList) {
							if (comparator.compare(s, u) == 0) {
								duplicateFound = true;
								break;
							}
						}
						if (!duplicateFound) {
							uniqueList.add(s);
						}
					}

					ProclibDatajson proc = new ProclibDatajson();
					for (ParmLibMembers value : dataList) {

						proc.setDatasetName(value.getDataset_Lib());
					}
					proc.setData(uniqueList);

					Map<String, Object> jsonResponse = new LinkedHashMap<>();
					jsonResponse.put("data", proc);
					return jsonResponse;

//					Strip String
//					 List<String> list = dataList.stream().map(ParmLibMembers::getLineText).map(String::strip).collect(Collectors.toList());
				} else {
					List<String> a = new ArrayList<>();
					List<Map<String, String>> b = new ArrayList<>();
					List<Map<String, String>> memberList = new ArrayList<>();
					if (mainframe_domain.equals("Prod") || mainframe_domain.equals("PROD")) {
						a = parmlibrepository.findbymemberNameAndDomain(memberName, "Prod");
						b = parmlibrepository.findbymemberNamedatasetlibandMainframeDomain(memberName, "Prod");
						memberList = parmlibrepository.findbymemberNamedatasetlibandMainframeDomain(memberName,
								datasetLib, "Prod");

					} else if (mainframe_domain.equals("DALLAS") || mainframe_domain.equals("DALLAS")) {
						a = parmlibrepository.findbymemberNameAndDomain(memberName, "Dallas");
						b = parmlibrepository.findbymemberNamedatasetlibandMainframeDomain(memberName, "Dallas");
						memberList = parmlibrepository.findbymemberNamedatasetlibandMainframeDomain(memberName,
								datasetLib, "Dallas");
					}
					List<TextResponseData> datas = new ArrayList<>();
					for (String value : a) {
						for (String datasetName : datasetlib) {
							TextResponseData data = new TextResponseData();
							data.setMemberName(value);
							data.setDatasetName(datasetName);
							datas.add(data);
						}
					}
					response.setData(datas);

					List<Map<String, String>> configList = new ArrayList<>();

					Map<String, String> membername = new LinkedHashMap<>();
					membername.put("header", "Member Name");
					membername.put("key", "membername");
					configList.add(membername);

					Map<String, String> dataset_lib = new LinkedHashMap<>();
					dataset_lib.put("header", "Dataset Name");
					dataset_lib.put("key", "dataset_lib");
					configList.add(dataset_lib);

					Map<String, Object> jsonResponse = new LinkedHashMap<>();
					jsonResponse.put("config", configList);
					jsonResponse.put("data", memberList);
					response.setData(datas);
					val = jsonResponse;
					return jsonResponse;
				}

			} else if (memberName == null && (datasetLib.endsWith("PARMLIB") || datasetLib.endsWith("PLANLIB")
					|| datasetLib.endsWith("PARMLBN"))) {

				try {
					List<ParmLibMembers> dataList = null;
					if (mainframe_domain.equals("Prod") || mainframe_domain.equals("PROD")) {
						dataList = parmlibrepository.findByDatasetLibAndDomain(datasetLib, "Prod");
					} else if (mainframe_domain.equals("DALLAS") || mainframe_domain.equals("Dallas")) {
						dataList = parmlibrepository.findByDatasetLibAndDomain(datasetLib, "Dallas");
					}
					List<String> a = new ArrayList<>();
					List<Map<String, String>> b = new ArrayList<>();
					if (mainframe_domain.equals("Prod") || mainframe_domain.equals("PROD")) {
						a = parmlibrepository.findbydatasetLibAndDomain(datasetLib, "Prod");
						b = parmlibrepository.findbydatasetLibAndDomain1(datasetLib, "Prod");
					} else if (mainframe_domain.equals("DALLAS") || mainframe_domain.equals("Dallas")) {
						a = parmlibrepository.findbydatasetLibAndDomain(datasetLib, "Dallas");
						b = parmlibrepository.findbydatasetLibAndDomain1(datasetLib, "Dallas");
					}

					List<String> datasetlib = dataList.stream().map(ParmLibMembers::getDataset_Lib).distinct()
							.collect(Collectors.toList());
					List<TextResponseData> datas = a.stream().map(value -> {
						TextResponseData data = new TextResponseData();
						data.setMemberName(value);
						return data;
					}).collect(Collectors.toList());

					List<Map<String, String>> configList = new ArrayList<>();

					Map<String, String> membername = new LinkedHashMap<>();
					membername.put("header", "Member Name");
					membername.put("key", "membername");
					configList.add(membername);

					Map<String, String> dataset_lib = new LinkedHashMap<>();
					dataset_lib.put("header", "Dataset Name");
					dataset_lib.put("key", "dataset_lib");
					configList.add(dataset_lib);

					Map<String, Object> jsonResponse = new LinkedHashMap<>();
					jsonResponse.put("config", configList);
					jsonResponse.put("data", b);
					response.setData(datas);
					val = jsonResponse;

//				if (datasetlib.size() > 1) {
//					response.setData(datas);
//				}else {
//					response.setData(new ArrayList<>());
//				}
					return jsonResponse;
				} catch (Exception e) {

				}
			} else {
				List<ParmLibMembers> dataList = new ArrayList<>();
				if (mainframe_domain.equals("Prod") || mainframe_domain.equals("PROD")) {
					dataList = parmlibrepository.findByDatasetLibAndMemberNameAndDomain(datasetLib, memberName, "Prod");
				} else if (mainframe_domain.equals("DALLAS") || mainframe_domain.equals("Dallas")) {
					dataList = parmlibrepository.findByDatasetLibAndMemberNameAndDomain(datasetLib, memberName,
							"Dallas");
				}
				if (CollectionUtils.isEmpty(dataList)) {

					List<String> a = new ArrayList<>();
					List<Map<String, String>> memberList = new ArrayList<>();
					if (mainframe_domain.equals("Prod") || mainframe_domain.equals("PROD")) {
						a = parmlibrepository.findbymemberNameandMainframeDomain(memberName, "Prod");
						memberList = parmlibrepository.findbymemberNamedatasetlibandMainframeDomain(memberName, "Prod");
					} else if (mainframe_domain.equals("DALLAS") || mainframe_domain.equals("Dallas")) {
						a = parmlibrepository.findbymemberNameandMainframeDomain(memberName, "Dallas");
						memberList = parmlibrepository.findbymemberNamedatasetlibandMainframeDomain(memberName,
								"Dallas");
					}
					List<String> sortedList = a.stream().sorted(new AlphaNumericComparator())
							.collect(Collectors.toList());
					List<TextResponseData> datas = sortedList.stream().map(value -> {
						TextResponseData data = new TextResponseData();
						data.setMemberName(value);
						return data;
					}).collect(Collectors.toList());
					response.setData(datas);
					List<Map<String, String>> configList = new ArrayList<>();

					Map<String, String> membername = new LinkedHashMap<>();
					membername.put("header", "Member Name");
					membername.put("key", "membername");
					configList.add(membername);

					Map<String, String> dataset_lib = new LinkedHashMap<>();
					dataset_lib.put("header", "Dataset Name");
					dataset_lib.put("key", "dataset_lib");
					configList.add(dataset_lib);

					Map<String, Object> jsonResponse = new LinkedHashMap<>();
					jsonResponse.put("config", configList);
					jsonResponse.put("data", memberList);
					response.setData(datas);
					val = jsonResponse;
					return jsonResponse;
				} else {
					List<String> list = dataList.stream().map(ParmLibMembers::getLineText).collect(Collectors.toList());

					Comparator<String> comparator = Comparator.comparing(String::toLowerCase);

					List<String> uniqueList = new ArrayList<>();

					for (String s : list) {
						boolean duplicateFound = false;
						for (String u : uniqueList) {
							if (comparator.compare(s, u) == 0) {
								duplicateFound = true;
								break;
							}
						}
						if (!duplicateFound) {
							uniqueList.add(s);
						}
					}
					ProclibDatajson proc = new ProclibDatajson();
					for (ParmLibMembers value : dataList) {

						proc.setDatasetName(value.getDataset_Lib());
					}
					proc.setData(uniqueList);

					Map<String, Object> jsonResponse = new LinkedHashMap<>();
					jsonResponse.put("data", proc);
					return jsonResponse;
				}

			}

		} catch (Exception e) {

		}

		List<Map<String, String>> configList = new ArrayList<>();

		Map<String, String> setLastUpdateDate = new LinkedHashMap<>();
		setLastUpdateDate.put("header", "Member Name");
		setLastUpdateDate.put("key", "membername");
		configList.add(setLastUpdateDate);

		Map<String, String> setLastUpdateTime = new LinkedHashMap<>();
		setLastUpdateTime.put("header", "Dataset Name");
		setLastUpdateTime.put("key", "dataset_lib");
		configList.add(setLastUpdateTime);

		Map<String, Object> jsonResponse = new LinkedHashMap<>();
		jsonResponse.put("config", configList);
		jsonResponse.put("data", new ArrayList<>());
		val = jsonResponse;
		return val;

	}

	class AlphaNumericComparator implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			s1 = s1.replaceAll("[^a-zA-Z]+", "");
			s2 = s2.replaceAll("[^a-zA-Z]+", "");
			int result = s1.compareToIgnoreCase(s2);
			if (result == 0) {
				String num1 = s1.replaceAll("[^0-9]+", "");
				String num2 = s2.replaceAll("[^0-9]+", "");
				if (num1.equals("") && num2.equals("")) {
					return 0;
				} else if (num1.equals("")) {
					return -1;
				} else if (num2.equals("")) {
					return 1;
				} else {
					return Integer.parseInt(num1) - Integer.parseInt(num2);
				}
			}
			return result;
		}
	}

	public List<XrefDataset> getAllDatasets() throws Exception {
		return xrefDatasetRepository.findAll();
	}

	public List<XrefDataset> getByDatasetname(String datasetName) {
		return xrefDatasetRepository.findByDatasetName(datasetName);
	}

	public List<XrefDataset> fetchAllDatasetDatawithWildcard(String datasetName) {
		//
		return xrefDatasetRepository.fetchAllDatasetDatawithWildcard(datasetName);
	}

	public Object findByDatasetLibAndMembernamea(String datasetLib, String memberName) {
		TextResponse response = new TextResponse();

		try {
			if (datasetLib == null) {
				List<ParmLibMembers> dataList = parmlibrepository.findMemberName(memberName);
				if (CollectionUtils.isEmpty(dataList)) {
					List<String> a = parmlibrepository.findbymemberName(memberName);
					List<TextResponseData> datas = a.stream().map(value -> {
						TextResponseData data = new TextResponseData();
						data.setMemberName(value);
						return data;
					}).collect(Collectors.toList());
					response.setData(datas);
					return response;
				} else {
					List<String> list = dataList.stream().map(ParmLibMembers::getLineText).collect(Collectors.toList());
					return list;
				}
			} else if (memberName == null) {
				List<ParmLibMembers> dataList = parmlibrepository.findByDatasetLib(datasetLib);
				List<String> a = parmlibrepository.findbydatasetLib(datasetLib);
				List<TextResponseData> datas = a.stream().map(value -> {
					TextResponseData data = new TextResponseData();
					data.setMemberName(value);
					return data;
				}).collect(Collectors.toList());
				response.setData(datas);
				return response;
			} else {
				List<ParmLibMembers> dataList = parmlibrepository.findByDatasetLibAndMembernamea(datasetLib,
						memberName);
				if (CollectionUtils.isEmpty(dataList)) {
					List<String> a = parmlibrepository.findbymemberName(memberName);
					List<String> sortedList = a.stream().sorted(new AlphaNumericComparator())
							.collect(Collectors.toList());
					List<TextResponseData> datas = sortedList.stream().map(value -> {
						TextResponseData data = new TextResponseData();
						data.setMemberName(value);
						return data;
					}).collect(Collectors.toList());
					response.setData(datas);
					return response;
				} else {
					List<String> list = dataList.stream().map(ParmLibMembers::getLineText).collect(Collectors.toList());
					return list;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object getParamLibs(String memberName) {
		List<ParmLibMembers> parmlibmembers = null;
		TextResponse response = new TextResponse();
		Object val = null;

		parmlibmembers = parmlibrepository.findMemberName(memberName);

		if (CollectionUtils.isEmpty(parmlibmembers)) {
			List<String> a = parmlibrepository.findbymemberName(memberName);
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

			for (ParmLibMembers value : parmlibmembers) {

				list.add(value.getLineText());
			}

			val = list;

		}

		return val;
	}

	public Object findByMemberandMainframeDomain(String datasetLib, String memberName, String mainframe_domain) {
		try {
			Object val = null;
			if (memberName == null && (datasetLib != null && (datasetLib.endsWith("PARMLIB")
					|| datasetLib.endsWith("PLANLIB") || datasetLib.endsWith("PARMLBN")))) {

				try {
					List<ParmLibMembers> dataList = null;
					if (mainframe_domain.equals("Prod") || mainframe_domain.equals("PROD")) {
						dataList = parmlibrepository.findByDatasetLibAndDomain(datasetLib, "Prod");
					} else if (mainframe_domain.equals("DALLAS") || mainframe_domain.equals("Dallas")) {
						dataList = parmlibrepository.findByDatasetLibAndDomain(datasetLib, "Dallas");
					}
					List<Map<String, String>> memberList = new ArrayList<>();
					if (mainframe_domain.equals("Prod") || mainframe_domain.equals("PROD")) {

						memberList = parmlibrepository.findbydatasetLibAndDomain1(datasetLib, "Prod");
					} else if (mainframe_domain.equals("DALLAS") || mainframe_domain.equals("Dallas")) {
						memberList = parmlibrepository.findbydatasetLibAndDomain1(datasetLib, "Dallas");
					}

					List<String> datasetlib = dataList.stream().map(ParmLibMembers::getDataset_Lib).distinct()
							.collect(Collectors.toList());

					List<Map<String, String>> configList = new ArrayList<>();

					Map<String, String> membername = new LinkedHashMap<>();
					membername.put("header", "Member Name");
					membername.put("key", "membername");
					configList.add(membername);

					Map<String, String> dataset_lib = new LinkedHashMap<>();
					dataset_lib.put("header", "Dataset Name");
					dataset_lib.put("key", "dataset_lib");
					configList.add(dataset_lib);

					Map<String, Object> jsonResponse = new LinkedHashMap<>();
					jsonResponse.put("config", configList);
					jsonResponse.put("data", memberList);
					val = jsonResponse;
					return val;
				} catch (Exception e) {

				}
			} else if (memberName != null && datasetLib != null) {
				List<ParmLibMembers> dataList = new ArrayList<>();
				if (mainframe_domain.equals("Prod") || mainframe_domain.equals("PROD")) {
					dataList = parmlibrepository.findByDatasetLibAndMemberNameAndDomain(datasetLib, memberName, "Prod");
				} else if (mainframe_domain.equals("DALLAS") || mainframe_domain.equals("Dallas")) {
					dataList = parmlibrepository.findByDatasetLibAndMemberNameAndDomain(datasetLib, memberName,
							"Dallas");
				}

				List<String> list = dataList.stream().map(ParmLibMembers::getLineText).collect(Collectors.toList());

//					Comparator<String> comparator = Comparator.comparing(String::toLowerCase);
//
//					List<String> uniqueList = new ArrayList<>();
//
//					for (String s : list) {
//						boolean duplicateFound = false;
//						for (String u : uniqueList) {
//							if (comparator.compare(s, u) == 0) {
//								duplicateFound = true;
//								break;
//							}
//						}
//						if (!duplicateFound) {
//							uniqueList.add(s);
//						}
//					}
				ProclibDatajson proc = new ProclibDatajson();
				for (ParmLibMembers value : dataList) {
						proc.setDatasetName(value.getDataset_Lib());
					
				}
				if(proc.getDatasetName() ==null){
					proc.setDatasetName("");
				}
				proc.setData(list);

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("data", proc);
				return jsonResponse;
			}

		} catch (Exception e) {

		}
		Object val = null;
		ProclibDatajson proc = new ProclibDatajson();

		proc.setDatasetName("");

		proc.setData(new ArrayList<>());

		Map<String, Object> jsonResponse = new LinkedHashMap<>();
		jsonResponse.put("data", proc);
		val = jsonResponse;

		return val;
	}

//	public Object findByDatasetLibAndMembernamea(String datasetLib, String memberName) {
//		List<ParmLibMembers> dataList = null;
//		TextResponse response = new TextResponse();
//		Object val = null;
//
//		try {
//			if (datasetLib == null) {
//				dataList = parmlibrepository.findMemberName(memberName);
//
//				if (CollectionUtils.isEmpty(dataList)) {
//					List<String> a = parmlibrepository.findbymemberName(memberName);
//					List<TextResponseData> datas = new ArrayList<>();
//					for (String value : a) {
//						TextResponseData data = new TextResponseData();
//						data.setMemberName(value);
//						datas.add(data);
//					}
//
//					response.setData(datas);
//					val = response;
//
//				} else {
//
//					List<String> list = new ArrayList<>();
//
//					for (ParmLibMembers value : dataList) {
//
//						list.add(value.getLineText());
//					}
//
//					val = list;
//
//				}
//
//			} else if (memberName == null) {
//
//				dataList = parmlibrepository.findByDatasetLib(datasetLib);
//
//				List<String> a = parmlibrepository.findbydatasetLib(datasetLib);
////				Collections.sort(a);
//				List<TextResponseData> datas = new ArrayList<>();
//
//				for (String value : a) {
//					TextResponseData data = new TextResponseData();
//					data.setMemberName(value);
//					datas.add(data);
//				}
//
//				response.setData(datas);
//				val = response;
//
//			} else {
//
//				dataList = parmlibrepository.findByDatasetLibAndMembernamea(datasetLib, memberName);
//
//				if (CollectionUtils.isEmpty(dataList)) {
//					List<String> a = new ArrayList<>();
//					a = parmlibrepository.findbymemberName(memberName);
//					Collections.sort(a, new Comparator<String>() {
//						@Override
//						public int compare(String s1, String s2) {
//							s1 = s1.replaceAll("[^a-zA-Z]+", "");
//							s2 = s2.replaceAll("[^a-zA-Z]+", "");
//							int result = s1.compareToIgnoreCase(s2);
//							if (result == 0) {
//								String num1 = s1.replaceAll("[^0-9]+", "");
//								String num2 = s2.replaceAll("[^0-9]+", "");
//								if (num1.equals("") && num2.equals("")) {
//									return 0;
//								} else if (num1.equals("")) {
//									return -1;
//								} else if (num2.equals("")) {
//									return 1;
//								} else {
//									return Integer.parseInt(num1) - Integer.parseInt(num2);
//								}
//							}
//							return result;
//						}
//					});
//					List<TextResponseData> datas = new ArrayList<>();
//					for (String value : a) {
//						TextResponseData data = new TextResponseData();
//						data.setMemberName(value);
//						datas.add(data);
//					}
//
//					response.setData(datas);
//					val = response;
//
//				} else {
//
//					List<String> list = new ArrayList<>();
//
//					for (ParmLibMembers value : dataList) {
//
//						list.add(value.getLineText());
//					}
//
//					val = list;
//
//				}
//
//			}
//
//			return val;
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		return null;
//	}

}
