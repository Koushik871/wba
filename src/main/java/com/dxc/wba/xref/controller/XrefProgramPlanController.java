package com.dxc.wba.xref.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.dbmodel.XrefProcJobProgm;
import com.dxc.wba.xref.dto.DbCombineData;
import com.dxc.wba.xref.service.XrefProgramPlanService;
import com.dxc.wba.xref.type.HeaderType;
import com.dxc.wba.xref.type.ProgramPlanJson;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class XrefProgramPlanController {

	@Autowired
	private XrefProgramPlanService xrefProgramPlanService;

//	@Autowired
//	private TableViewService tableViewService;

	@GetMapping(value = "getProgramPlanByprog/{mainframeDomain}/{progName}")
	public DbCombineData getProgNameAndMainframeDomain(
			
			
			@PathVariable(required = false, name = "mainframeDomain") String mainframeDomain,
			@PathVariable(required = false, name = "progName") String progName) {
		progName = progName.replace('*', ' ').trim();
		return xrefProgramPlanService.getProgramPlanByprogNameAndMainframeDomain(progName, mainframeDomain);
	}

	@GetMapping(value = "getProgramPlanByprogName/{mainframeDomain}/{progName}")
	public ProgramPlanJson getProgramPlanByprogName(@PathVariable(required = false, name = "progName") String progName,
			@PathVariable(required = false, name = "mainframeDomain") String mainframeDomain) {

		ProgramPlanJson programPlanJson = new ProgramPlanJson();
		try {
			List<XrefProcJobProgm> response = null;
			if (mainframeDomain != null
					&& (mainframeDomain.equalsIgnoreCase("PROD") || mainframeDomain.equalsIgnoreCase("Prod"))) {
				response = xrefProgramPlanService.getProgramPlanByprogNameAndMainframeDomains(progName, "Prod");
			} else if (mainframeDomain != null
					&& (mainframeDomain.equalsIgnoreCase("DALLAS") || mainframeDomain.equalsIgnoreCase("DALLAS"))) {
				response = xrefProgramPlanService.getProgramPlanByprogNameAndMainframeDomains(progName, "Dallas");
			}
			Planheader();
			programPlanJson.setConfig(Planheader());
			programPlanJson.setData(response);

			System.out.println("retrieving the list of Jobprogram ========" + progName);
			return programPlanJson;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@GetMapping(value = "getProgramPlanByprog/{progName}")
	public DbCombineData getAll(@PathVariable(required = false, name = "progName") String progName) {

		return xrefProgramPlanService.findAll(progName);
	}

//	@GetMapping(value = "getProgramPlanByprogName/{progName}")
//	public ProgramPlanJson getProgramPlanByprogName(
//			@PathVariable(required = false, name = "progName") String progName) {
//		System.out.println("reterving the list of job prog" + progName);
//
//		ProgramPlanJson programPlanJson = new ProgramPlanJson();
//		try {
//
//			List<XrefProgramPlan> response = xrefProgramPlanService.getProgramPlanByprogName(progName);
//
//			Planheader();
//			programPlanJson.setConfig(Planheader());
//			programPlanJson.setData(response);
//
//			System.out.println("reterving the list of Jobprogram ========" + progName);
//			return programPlanJson;
//		} catch (Exception e) {
//			System.out.println(e);
//			return null;
//		}
//	}

//	@GetMapping(value = "getProgramPlanBysuggestions/{progName}")
//	public ProgramPlanJson getProgramPlanBysuggestions(
//			@PathVariable(required = false, name = "progName") String progName) {
//		System.out.println("reterving the list of data set-" + progName);
//
//		ProgramPlanJson programPlanJson = new ProgramPlanJson();
//		try {
//			if (progName != null && (progName.endsWith("*") || progName.endsWith("+")))
//				progName = progName.replace("*", "%");
//			progName = progName.replace("+", "%");
//
//			List<XrefProgramPlan> response = xrefProgramPlanService.fetchAllprogNameDatawithwildcards(progName);
//
//			List<HeaderType> headerTypeList = new ArrayList<>();
//
//			HeaderType headerType = new HeaderType();
//			headerType.setHeader("progName");
//			headerType.setKey("progName");
//			headerTypeList.add(headerType);
//			programPlanJson.setConfig(headerTypeList);
//			programPlanJson.setData(response);
//			System.out.println("reterving the list of data set========" + progName);
//			return programPlanJson;
//		} catch (Exception e) {
//			System.out.println(e);
//			return null;
//		}

//	}

	private List<HeaderType> Planheader() {
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
