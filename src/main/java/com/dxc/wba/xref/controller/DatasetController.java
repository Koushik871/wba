package com.dxc.wba.xref.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.dbmodel.XrefDataset;
import com.dxc.wba.xref.service.DatasetService;
import com.dxc.wba.xref.type.DatasetJson;
import com.dxc.wba.xref.type.HeaderType;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DatasetController {
	@Qualifier("datasetService")
	@Autowired
	private DatasetService datasetService;

	@GetMapping("GetParamLib/{mainframeDomain}/{param}")
	public Object findByMembernameandMainframeDomain(@PathVariable("mainframeDomain") String mainframeDomain,
			@PathVariable("param") String param) {
		String datasetLib = null;
		String membername = null;
		param = param.replace('*', ' ').trim();
		if (param.contains("(") && param.endsWith(")")) {
			String[] params = param.split("\\(");
			datasetLib = params[0];
			if (params[1].contains(")")) {
				membername = params[1].substring(0, params[1].length() - 1);
			} else {
				membername = params[1];
			}
		} else if (param.contains(".")) {
			datasetLib = param;
		}

		return datasetService.findByMemberandMainframeDomain(datasetLib, membername, mainframeDomain);
//		return datasetService.findByMembernameandMainframeDomain(datasetLib, membername, mainframeDomain);
	}

	@GetMapping(value = "getByDatasetNameByWildcards/{mainframe_domain}/{datasetName}")
	public DatasetJson getByDatasetNameByWildcards(
			@PathVariable(required = true, name = "datasetName") String datasetName,
			@PathVariable(required = true, name = "mainframe_domain") String mainframeDomain) {

		DatasetJson datasetJson = new DatasetJson();
		try {
			datasetName = datasetName.replace('*', ' ').trim();

			List<XrefDataset> response;

			if ((mainframeDomain.equalsIgnoreCase("PROD")) || (mainframeDomain.equalsIgnoreCase("Prod"))) {
				response = datasetService.fetchAllDatasetDatawithWildcardAndMainframeDomain(datasetName, "Prod");
			} else if ((mainframeDomain.equalsIgnoreCase("DALLAS")) || (mainframeDomain.equalsIgnoreCase("Dallas"))) {
				response = datasetService.fetchAllDatasetDatawithWildcardAndMainframeDomain(datasetName, "Dallas");
			} else {
				throw new IllegalArgumentException("Invalid mainframe_domain value: " + mainframeDomain);
			}

			headers();
			datasetJson.setConfig(headers());
			datasetJson.setData(response);

			return datasetJson;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

//	@GetMapping("GetParamLib/{param}")
//	public Object findByDatasetLibAndMembernamea(@PathVariable("param") String param) {
//		String datasetLib = null;
//		String membername = null;
//		if (param.contains("(") && param.endsWith(")")) {
//			String[] params = param.split("\\(");
//			datasetLib = params[0];
//			if (params[1].contains(")")) {
//				membername = params[1].substring(0, params[1].length() - 1);
//			} else {
//				membername = params[1];
//			}
//		} else if (param.contains(".")) {
//			datasetLib = param;
//		} else {
//			membername = param;
//		}
//		return datasetService.findByDatasetLibAndMembernamea(datasetLib, membername);
//	}

//	@GetMapping(value = "getByDatasetNameByWildcards/{datasetName}")
//	public DatasetJson getByDatasetNameByWildcards(
//			@PathVariable(required = false, name = "datasetName") String datasetName) {
//		System.out.println("reterving the list of data set" + datasetName);
//
//		DatasetJson datasetJson = new DatasetJson();
//		try {
//			datasetName = datasetName.replace('*', ' ').trim();
////			datasetName = datasetName.replace('+', ' ').trim();
//			List<XrefDataset> response = datasetService.fetchAllDatasetDatawithWildcard(datasetName);
//
//			datasetheader();
//			datasetJson.setConfig(datasetheader());
//			datasetJson.setData(response);
//
//			System.out.println("reterving the list of data set========" + datasetName);
//			return datasetJson;
//		} catch (Exception e) {
//			System.out.println(e);
//			return null;
//		}
//
//	}

//	@GetMapping("GetParamLibs/{memberName}")
//	public Object GetParamLib(@PathVariable String memberName) {
//
//		Object response = null;
//		try {
//			response = datasetService.getParamLibs(memberName);
//			System.out.println("reterving the list of data set" + memberName);
//
//		} catch (Exception e) {
//
//		}
//		return response;
//
//	}

	private List<HeaderType> headers() {
		List<HeaderType> headerTypeList = new ArrayList<>();

		HeaderType headerType = new HeaderType();
		headerType.setHeader("datasetname");
		headerType.setKey("datasetName");

		HeaderType headerType1 = new HeaderType();
		headerType1.setHeader("T");
		headerType1.setKey("region");

		HeaderType headerType2 = new HeaderType();
		headerType2.setHeader("Refs");
		headerType2.setKey("refCt");

		HeaderType headerType3 = new HeaderType();
		headerType3.setHeader("Member");
		headerType3.setKey("dsnMbrName");

		HeaderType headerType4 = new HeaderType();
		headerType4.setHeader("Source");
		headerType4.setKey("dsnMbrSource");

		HeaderType headerType5 = new HeaderType();
		headerType5.setHeader("C:D copy step or Procedure");
		headerType5.setKey("procName");

		HeaderType headerType6 = new HeaderType();
		headerType6.setHeader("Refs");
		headerType6.setKey("dsnProcRefs");

		HeaderType headerType7 = new HeaderType();
		headerType7.setHeader("C:D Process or Job");
		headerType7.setKey("jobName");

		HeaderType headerType8 = new HeaderType();
		headerType8.setHeader("Refs");
		headerType8.setKey("dsnJobRefs");

		headerTypeList.add(headerType);
		headerTypeList.add(headerType1);
		headerTypeList.add(headerType2);
		headerTypeList.add(headerType3);
		headerTypeList.add(headerType4);
		headerTypeList.add(headerType5);
		headerTypeList.add(headerType6);
		headerTypeList.add(headerType7);
		headerTypeList.add(headerType8);
		return headerTypeList;
	}
//
//	private List<HeaderType> datasetheader() {
//		List<HeaderType> headerTypeList = new ArrayList<>();
//
//		HeaderType headerType = new HeaderType();
//		headerType.setHeader("datasetname");
//		headerType.setKey("datasetName");
//
//		HeaderType headerType1 = new HeaderType();
//		headerType1.setHeader("T");
//		headerType1.setKey("region");
//
//		HeaderType headerType2 = new HeaderType();
//		headerType2.setHeader("Refs");
//		headerType2.setKey("refCt");
//
//		HeaderType headerType3 = new HeaderType();
//		headerType3.setHeader("Member");
//		headerType3.setKey("dsnMbrName");
//
//		HeaderType headerType4 = new HeaderType();
//		headerType4.setHeader("Source");
//		headerType4.setKey("dsnMbrSource");
//
//		HeaderType headerType5 = new HeaderType();
//		headerType5.setHeader("C:D copy step or Procedure");
//		headerType5.setKey("procName");
//
//		HeaderType headerType6 = new HeaderType();
//		headerType6.setHeader("Refs");
//		headerType6.setKey("dsnProcRefs");
//
//		HeaderType headerType7 = new HeaderType();
//		headerType7.setHeader("C:D Process or Job");
//		headerType7.setKey("jobName");
//
//		HeaderType headerType8 = new HeaderType();
//		headerType8.setHeader("Refs");
//		headerType8.setKey("dsnJobRefs");
//
//		headerTypeList.add(headerType);
//		headerTypeList.add(headerType1);
//		headerTypeList.add(headerType2);
//		headerTypeList.add(headerType3);
//		headerTypeList.add(headerType4);
//		headerTypeList.add(headerType5);
//		headerTypeList.add(headerType6);
//		headerTypeList.add(headerType7);
//		headerTypeList.add(headerType8);
//		return headerTypeList;
//	}

}
