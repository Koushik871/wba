package com.dxc.wba.xref.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.wba.xref.dbmodel.XrefProcJobProgm;
import com.dxc.wba.xref.repository.XrefProcJobProgmRepository;
import com.dxc.wba.xref.type.HeaderType;
import com.dxc.wba.xref.type.JobProgJson;

@Service
public class JobProgmService {

	@Autowired
	XrefProcJobProgmRepository xrefProcJobProgmRepository;

	public JobProgJson getByJobName(String jobName) {

		JobProgJson jobprogJson = new JobProgJson();

		List<XrefProcJobProgm> response = xrefProcJobProgmRepository.getByJobName(jobName);

		jbheader();
		jobprogJson.setConfig(jbheader());
		jobprogJson.setData(response);

		System.out.println("reterving the list of Jobprogram ========" + jobName);
		return jobprogJson;

	}

	public JobProgJson findByJobName(String jobName) {

		JobProgJson jobprogJson = new JobProgJson();

		List<XrefProcJobProgm> response = xrefProcJobProgmRepository.findByJobName(jobName);

		jobprogJson.setConfig(jbheader());
		jobprogJson.setData(response);

		System.out.println("reterving the list of Jobprogram ========" + jobName);
		return jobprogJson;

	}

	public JobProgJson getByJobNames(String jobName, String mainframeDomain) {

		JobProgJson jobprogJson = new JobProgJson();
		List<XrefProcJobProgm> response = null;
		if (mainframeDomain != null && ((mainframeDomain.equals("PROD")) || mainframeDomain.equals("Prod"))) {
			response = xrefProcJobProgmRepository.findByJobNameAndMainframeDomains(jobName, "Prod");
		} else if (mainframeDomain != null
				&& ((mainframeDomain.equals("DALLAS")) || mainframeDomain.equals("Dallas"))) {
			response = xrefProcJobProgmRepository.findByJobNameAndMainframeDomains(jobName, "Dallas");
		}
		response = response.stream().filter(x -> !x.getAppId().equalsIgnoreCase("N/A")).collect(Collectors.toList());
		jbheader();

		Collections.sort(response,
				Comparator.comparing(XrefProcJobProgm::getAppId).thenComparing(XrefProcJobProgm::getCpuId));
		jobprogJson.setConfig(jbheader());
		jobprogJson.setData(response);

		System.out.println("reterving the list of Jobprogram ========" + jobName);
		return jobprogJson;

	}

	public JobProgJson findByJobNames(String jobName, String mainframeDomain) {
		JobProgJson jobprogJson = new JobProgJson();
		List<XrefProcJobProgm> response = null;
		if (mainframeDomain != null && ((mainframeDomain.equals("PROD")) || mainframeDomain.equals("Prod"))) {
			response = xrefProcJobProgmRepository.findByJobNameAndMainframeDomain(jobName, "Prod");
		} else if (mainframeDomain != null
				&& ((mainframeDomain.equals("DALLAS")) || mainframeDomain.equals("Dallas"))) {
			response = xrefProcJobProgmRepository.findByJobNameAndMainframeDomain(jobName, "Dallas");
		}
		response = response.stream().filter(x -> !x.getAppId().equalsIgnoreCase("N/A")).collect(Collectors.toList());
		jbheader();

		Collections.sort(response,
				Comparator.comparing(XrefProcJobProgm::getAppId).thenComparing(XrefProcJobProgm::getCpuId));
		jobprogJson.setConfig(jbheader());
		jobprogJson.setData(response);

		System.out.println("reterving the list of Jobprogram ========" + jobName);
		return jobprogJson;

	}

	private List<HeaderType> jbheader() {
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

}
