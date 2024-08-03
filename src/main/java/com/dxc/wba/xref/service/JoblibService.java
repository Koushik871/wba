package com.dxc.wba.xref.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dxc.wba.xref.dbmodel.JoblibMembers;
import com.dxc.wba.xref.repository.JoblibRepository;
import com.dxc.wba.xref.type.JobLibDataJson;
import com.dxc.wba.xref.type.TextResponse;
import com.dxc.wba.xref.type.TextResponseData;

@Service
public class JoblibService {

	@Autowired
	JoblibRepository jobRepository;

	public Object getjobFiles(String memberName) {

		List<JoblibMembers> joblibMembers = null;
		TextResponse response = new TextResponse();
		Object val = null;

		joblibMembers = jobRepository.findMemberName(memberName);

		if (CollectionUtils.isEmpty(joblibMembers)) {
			List<String> a = jobRepository.findbymemberName(memberName);
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

			for (JoblibMembers value : joblibMembers) {

				list.add(value.getLineText());
			}

			val = list;

		}

		return val;
	}

	public Object getJoblibFiles(String memberName, String mainframe_domain) {

		List<JoblibMembers> joblibMembers = null;
		TextResponse response = new TextResponse();
		Object val = null;

		if (mainframe_domain.equals("DALLAS") || mainframe_domain.equals("Dallas")) {
			// filter by DALLAS
			joblibMembers = jobRepository.findMemberNameByDallas(memberName);
			List<String> datasetlib = joblibMembers.stream().map(JoblibMembers::getDatasetName).distinct()
					.collect(Collectors.toList());
//			if (CollectionUtils.isEmpty(joblibMembers)) {
//				List<String> a = jobRepository.findbymemberNameDallas(memberName);
//				List<Map<String, Object>> b = jobRepository.findbymemberandDatasetNameDallas(memberName);
//				List<TextResponseData> datas = new ArrayList<>();
//				for (String value : a) {
//					TextResponseData data = new TextResponseData();
//					data.setMemberName(value);
//					data.setDatasetName(mainframe_domain);
//					datas.add(data);
//				}
//				response.setData(datas);
//
//				List<Map<String, String>> configList = new ArrayList<>();
//
//				// Add header and key entries directly
//				Map<String, String> setLastUpdateDate = new LinkedHashMap<>();
//				setLastUpdateDate.put("header", "Member Name");
//				setLastUpdateDate.put("key", "membername");
//				configList.add(setLastUpdateDate);
//
//				Map<String, String> setLastUpdateTime = new LinkedHashMap<>();
//				setLastUpdateTime.put("header", "Dataset Name");
//				setLastUpdateTime.put("key", "dataset_name");
//				configList.add(setLastUpdateTime);
//
//				Map<String, Object> jsonResponse = new LinkedHashMap<>();
//				jsonResponse.put("config", configList);
//				jsonResponse.put("data", b);
//
//				val = jsonResponse;
//
//			} else 
			if (datasetlib.size() == 1) {

				List<String> list = new ArrayList<>();

				JobLibDataJson job = new JobLibDataJson();

				job.setData(list);
				for (JoblibMembers value : joblibMembers) {

					job.setDatasetName(value.getDatasetName());
				}
				for (JoblibMembers value : joblibMembers) {

					list.add(value.getLineText().stripTrailing());
				}

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("data", job);

				val = jsonResponse;

			} else {
				List<String> a = jobRepository.findbymemberNameDallas(memberName);
				List<Map<String, Object>> b = jobRepository.findbymemberandDatasetNameDallas(memberName);
				List<TextResponseData> datas = new ArrayList<>();
				for (String value : a) {
					TextResponseData data = new TextResponseData();
					data.setMemberName(value);
					data.setDatasetName(mainframe_domain);
					datas.add(data);
				}
				response.setData(datas);

				List<Map<String, String>> configList = new ArrayList<>();

				Map<String, String> setLastUpdateDate = new LinkedHashMap<>();
				setLastUpdateDate.put("header", "Member Name");
				setLastUpdateDate.put("key", "membername");
				configList.add(setLastUpdateDate);

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
			joblibMembers = jobRepository.findMemberNameByProd(memberName);
			List<String> datasetlib = joblibMembers.stream().map(JoblibMembers::getDatasetName).distinct()
					.collect(Collectors.toList());
//			if (CollectionUtils.isEmpty(joblibMembers)) {
//				List<String> a = jobRepository.findbymemberNameProd(memberName);
//				List<Map<String, Object>> b = jobRepository.findbymemberandDatasetNameProd(memberName);
//				List<TextResponseData> datas = new ArrayList<>();
//				for (String value : a) {
//					TextResponseData data = new TextResponseData();
//					data.setMemberName(value);
//					datas.add(data);
//				}
//
//				List<Map<String, String>> configList = new ArrayList<>();
//
//				// Add header and key entries directly
//				Map<String, String> setLastUpdateDate = new LinkedHashMap<>();
//				setLastUpdateDate.put("header", "Member Name");
//				setLastUpdateDate.put("key", "membername");
//				configList.add(setLastUpdateDate);
//
//				Map<String, String> setLastUpdateTime = new LinkedHashMap<>();
//				setLastUpdateTime.put("header", "Dataset Name");
//				setLastUpdateTime.put("key", "dataset_name");
//				configList.add(setLastUpdateTime);
//
//				Map<String, Object> jsonResponse = new LinkedHashMap<>();
//				jsonResponse.put("config", configList);
//				jsonResponse.put("data", b);
//
//				response.setData(datas);
//				val = jsonResponse;
//
//			} else
			if (datasetlib.size() == 1) {

				List<String> list = new ArrayList<>();

				JobLibDataJson job = new JobLibDataJson();

				job.setData(list);
				for (JoblibMembers value : joblibMembers) {

					job.setDatasetName(value.getDatasetName());
				}
				for (JoblibMembers value : joblibMembers) {

					list.add(value.getLineText().stripTrailing());
				}

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("data", job);

				val = jsonResponse;

			} else {
				List<String> a = jobRepository.findbymemberNameProd(memberName);
				List<Map<String, Object>> b = jobRepository.findbymemberandDatasetNameProd(memberName);
				List<TextResponseData> datas = new ArrayList<>();
				for (String value : a) {
					TextResponseData data = new TextResponseData();
					data.setMemberName(value);
					datas.add(data);
				}

				List<Map<String, String>> configList = new ArrayList<>();

				Map<String, String> setLastUpdateDate = new LinkedHashMap<>();
				setLastUpdateDate.put("header", "Member Name");
				setLastUpdateDate.put("key", "membername");
				configList.add(setLastUpdateDate);

				Map<String, String> setLastUpdateTime = new LinkedHashMap<>();
				setLastUpdateTime.put("header", "Dataset Name");
				setLastUpdateTime.put("key", "dataset_name");
				configList.add(setLastUpdateTime);

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("config", configList);
				jsonResponse.put("data", b);

				response.setData(datas);
				val = jsonResponse;

			}
		}

		return val;
	}

	public Object getJoblibFiless(String memberName, String datasetLib, String mainframe_domain) {

		List<JoblibMembers> joblibMembers = null;
		TextResponse response = new TextResponse();
		Object val = null;

		if (mainframe_domain.equals("DALLAS") || mainframe_domain.equals("Dallas")) {
			// filter by DALLAS
			joblibMembers = jobRepository.findMemberNameByDallas(memberName);
			List<String> datasetlib = joblibMembers.stream().map(JoblibMembers::getDatasetName).distinct()
					.collect(Collectors.toList());
//			if (CollectionUtils.isEmpty(joblibMembers)) {
//				List<String> a = jobRepository.findbymemberNameDallas(memberName);
//				List<Map<String, Object>> b = jobRepository.findbymemberandDatasetNameDallas(memberName);
//				List<TextResponseData> datas = new ArrayList<>();
//				for (String value : a) {
//					TextResponseData data = new TextResponseData();
//					data.setMemberName(value);
//					data.setDatasetName(mainframe_domain);
//					datas.add(data);
//				}
//				response.setData(datas);
//
//				List<Map<String, String>> configList = new ArrayList<>();
//
//				// Add header and key entries directly
//				Map<String, String> setLastUpdateDate = new LinkedHashMap<>();
//				setLastUpdateDate.put("header", "Member Name");
//				setLastUpdateDate.put("key", "membername");
//				configList.add(setLastUpdateDate);
//
//				Map<String, String> setLastUpdateTime = new LinkedHashMap<>();
//				setLastUpdateTime.put("header", "Dataset Name");
//				setLastUpdateTime.put("key", "dataset_name");
//				configList.add(setLastUpdateTime);
//
//				Map<String, Object> jsonResponse = new LinkedHashMap<>();
//				jsonResponse.put("config", configList);
//				jsonResponse.put("data", b);
//
//				val = jsonResponse;
//
//			} else
			if (datasetlib.size() == 1) {

				List<String> list = new ArrayList<>();

				JobLibDataJson job = new JobLibDataJson();

				job.setData(list);
				for (JoblibMembers value : joblibMembers) {

					job.setDatasetName(value.getDatasetName());
				}
				for (JoblibMembers value : joblibMembers) {

					list.add(value.getLineText().stripTrailing());
				}

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("data", job);

				val = jsonResponse;

			} else if (!datasetLib.isEmpty()) {
				List<JoblibMembers> joblibMemberss = new ArrayList<>();
				joblibMemberss = jobRepository.findMemberNameByDallass(memberName, datasetLib);
				List<String> list = new ArrayList<>();

				JobLibDataJson job = new JobLibDataJson();

				job.setData(list);
				for (JoblibMembers value : joblibMemberss) {

					job.setDatasetName(value.getDatasetName());
				}
				for (JoblibMembers value : joblibMemberss) {

					list.add(value.getLineText().stripTrailing());
				}

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("data", job);

				val = jsonResponse;
			} else {
				List<String> a = jobRepository.findbymemberNameDallas(memberName);
				List<Map<String, Object>> b = jobRepository.findbymemberandDatasetNameDallas(memberName);
				List<TextResponseData> datas = new ArrayList<>();
				for (String value : a) {
					TextResponseData data = new TextResponseData();
					data.setMemberName(value);
					data.setDatasetName(mainframe_domain);
					datas.add(data);
				}
				response.setData(datas);

				List<Map<String, String>> configList = new ArrayList<>();

				// Add header and key entries directly
				Map<String, String> setLastUpdateDate = new LinkedHashMap<>();
				setLastUpdateDate.put("header", "Member Name");
				setLastUpdateDate.put("key", "membername");
				configList.add(setLastUpdateDate);

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
			joblibMembers = jobRepository.findMemberNameByProd(memberName);
			List<String> datasetlib = joblibMembers.stream().map(JoblibMembers::getDatasetName).distinct()
					.collect(Collectors.toList());
//			if (CollectionUtils.isEmpty(joblibMembers)) {
//				List<String> a = jobRepository.findbymemberNameProd(memberName);
//				List<Map<String, Object>> b = jobRepository.findbymemberandDatasetNameProd(memberName);
//				List<TextResponseData> datas = new ArrayList<>();
//				for (String value : a) {
//					TextResponseData data = new TextResponseData();
//					data.setMemberName(value);
//					datas.add(data);
//				}
//
//				List<Map<String, String>> configList = new ArrayList<>();
//
//				// Add header and key entries directly
//				Map<String, String> setLastUpdateDate = new LinkedHashMap<>();
//				setLastUpdateDate.put("header", "Member Name");
//				setLastUpdateDate.put("key", "membername");
//				configList.add(setLastUpdateDate);
//
//				Map<String, String> setLastUpdateTime = new LinkedHashMap<>();
//				setLastUpdateTime.put("header", "Dataset Name");
//				setLastUpdateTime.put("key", "dataset_name");
//				configList.add(setLastUpdateTime);
//
//				Map<String, Object> jsonResponse = new LinkedHashMap<>();
//				jsonResponse.put("config", configList);
//				jsonResponse.put("data", b);
//
//				response.setData(datas);
//				val = jsonResponse;
//
//			} else

			if (datasetlib.size() == 1) {

				List<String> list = new ArrayList<>();

				JobLibDataJson job = new JobLibDataJson();

				job.setData(list);
				for (JoblibMembers value : joblibMembers) {

					job.setDatasetName(value.getDatasetName());
				}
				for (JoblibMembers value : joblibMembers) {

					list.add(value.getLineText().stripTrailing());
				}

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("data", job);

				val = jsonResponse;

			} else if (!datasetLib.isEmpty()) {

				List<JoblibMembers> joblibMemberss = new ArrayList<>();
				joblibMemberss = jobRepository.findMemberNameByProdd(memberName, datasetLib);
				List<String> list = new ArrayList<>();

				JobLibDataJson job = new JobLibDataJson();

				job.setData(list);
				for (JoblibMembers value : joblibMemberss) {

					job.setDatasetName(value.getDatasetName());
				}
				for (JoblibMembers value : joblibMemberss) {

					list.add(value.getLineText().stripTrailing());
				}

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("data", job);

				val = jsonResponse;

			} else {
				List<String> a = jobRepository.findbymemberNameProd(memberName);
				List<Map<String, Object>> b = jobRepository.findbymemberandDatasetNameProd(memberName);
				List<TextResponseData> datas = new ArrayList<>();
				for (String value : a) {
					TextResponseData data = new TextResponseData();
					data.setMemberName(value);
					datas.add(data);
				}

				List<Map<String, String>> configList = new ArrayList<>();

				// Add header and key entries directly
				Map<String, String> setLastUpdateDate = new LinkedHashMap<>();
				setLastUpdateDate.put("header", "Member Name");
				setLastUpdateDate.put("key", "membername");
				configList.add(setLastUpdateDate);

				Map<String, String> setLastUpdateTime = new LinkedHashMap<>();
				setLastUpdateTime.put("header", "Dataset Name");
				setLastUpdateTime.put("key", "dataset_name");
				configList.add(setLastUpdateTime);

				Map<String, Object> jsonResponse = new LinkedHashMap<>();
				jsonResponse.put("config", configList);
				jsonResponse.put("data", b);

				response.setData(datas);
				val = jsonResponse;

			}
		}

		return val;

	}

	public Object getJoblibwildcard(String memberName, String mainframe_domain) {

		Object val = null;

		if (mainframe_domain.equals("DALLAS") || mainframe_domain.equals("Dallas")) {
			List<Map<String, Object>> jobList = jobRepository.findbymemberandDatasetDallasDrilldown(memberName);
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
			jsonResponse.put("data", jobList);
			val = jsonResponse;

		} else if (mainframe_domain.equals("PROD") || mainframe_domain.equals("Prod")) {

			List<Map<String, Object>> jobList = jobRepository.findbymemberandDatasetProdDrilldown(memberName);

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
			jsonResponse.put("data", jobList);
			val = jsonResponse;

		}

		return val;

	
	}

}
