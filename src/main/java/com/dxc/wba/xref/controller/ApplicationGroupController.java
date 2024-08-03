package com.dxc.wba.xref.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.dbmodel.ApplicationGroup;
import com.dxc.wba.xref.service.ApplicationGroupService;
import com.dxc.wba.xref.type.ApplicationGroupJson;
import com.dxc.wba.xref.type.HeaderType;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ApplicationGroupController {

	@Autowired
	private ApplicationGroupService applicationGroupService;

	@GetMapping(value = "getAllApplicationsByApplId/{applGroup}")
	public ApplicationGroupJson getByApplications(
			@PathVariable(required = false, name = "applGroup") String applGroup) {

		ApplicationGroupJson applicationGroupJson = new ApplicationGroupJson();
		try {

			List<ApplicationGroup> response = applicationGroupService.getApplicationsByapplId(applGroup);

			applicationGroupJson.setConfig(applheaders());
			applicationGroupJson.setData(response);

			return applicationGroupJson;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	@GetMapping(value = "getApplicationBysuggestions/{applGroup}")
	public ApplicationGroupJson getApplicationsBysuggestions(
			@PathVariable(required = false, name = "applGroup") String applGroup) {

		ApplicationGroupJson applicationGroupJson = new ApplicationGroupJson();
		try {
			if (applGroup != null && (applGroup.endsWith("*") || applGroup.endsWith("+")))
				applGroup = applGroup.replace("*", "%");
			applGroup = applGroup.replace("+", "%");

			List<ApplicationGroup> response = applicationGroupService.getApplicationsByWildcards(applGroup);

			List<HeaderType> headerTypeList = new ArrayList<>();

			HeaderType headerType = new HeaderType();
			headerType.setHeader("applGroup");
			headerType.setKey("applGroup");
			headerTypeList.add(headerType);
			applicationGroupJson.setConfig(headerTypeList);
			applicationGroupJson.setData(response);

			return applicationGroupJson;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	private List<HeaderType> applheaders() {
		List<HeaderType> headerTypeList = new ArrayList<>();

		HeaderType headerType = new HeaderType();
		headerType.setHeader("App .Group");
		headerType.setKey("applGroup");

		HeaderType headerType1 = new HeaderType();
		headerType1.setHeader("App .Group Name");
		headerType1.setKey("applGroupName");

		HeaderType headerType2 = new HeaderType();
		headerType2.setHeader("Application");
		headerType2.setKey("application");

		HeaderType headerType3 = new HeaderType();
		headerType3.setHeader("Application Name");
		headerType3.setKey("applName");

		headerTypeList.add(headerType);
		headerTypeList.add(headerType1);
		headerTypeList.add(headerType2);
		headerTypeList.add(headerType3);

		return headerTypeList;
	}
}
