package com.dxc.wba.xref.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.dbmodel.ApplicationFileModel;
import com.dxc.wba.xref.service.ApplicationService;
import com.dxc.wba.xref.type.ApplicationJson;
import com.dxc.wba.xref.type.HeaderType;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@GetMapping("getAppandApplib/{mainframeDomain}/{applName}")
	public Object getbyAppandlib(@PathVariable String mainframeDomain, @PathVariable String applName) {

		Object response = null;
		try {
			applName = applName.replace("*", "");
			if ((mainframeDomain.equalsIgnoreCase("PROD")) || (mainframeDomain.equalsIgnoreCase("Prod"))) {
				response = applicationService.getbyAppandlib(applName, "Prod");
			} else if ((mainframeDomain.equalsIgnoreCase("DALLAS")) || (mainframeDomain.equalsIgnoreCase("Dallas"))) {
				response = applicationService.getbyAppandlib(applName, "Dallas");
			} else {
				throw new IllegalArgumentException("Invalid mainframe_domain value: " + mainframeDomain);
			}

			System.out.println("reterving the list of applib" + applName);

		} catch (Exception e) {

		}
		return response;

	}

	@GetMapping("Getapplib/{mainframeDomain}/{applName}")
	public Object getbyAppfiles(@PathVariable String mainframeDomain, @PathVariable String applName) {

		Object response = null;
		try {

			if ((mainframeDomain.equalsIgnoreCase("PROD")) || (mainframeDomain.equalsIgnoreCase("Prod"))) {
				response = applicationService.getbyAppFiles(applName, "Prod");
			} else if ((mainframeDomain.equalsIgnoreCase("DALLAS")) || (mainframeDomain.equalsIgnoreCase("Dallas"))) {
				response = applicationService.getbyAppFiles(applName, "Dallas");
			} else {
				throw new IllegalArgumentException("Invalid mainframe_domain value: " + mainframeDomain);
			}

			System.out.println("reterving the list of applib" + applName);

		} catch (Exception e) {

		}
		return response;

	}

	@GetMapping(value = "getApplicationWildacardsByapplName/{mainframeDomain}/{Appl_Id}")
	public ApplicationJson getdatasetBysuggestions(
			@PathVariable(required = false, name = "mainframeDomain") String mainframeDomain,
			@PathVariable(required = false, name = "Appl_Id") String Appl_Id) throws NullPointerException {

		ApplicationJson applicationJson = new ApplicationJson();
		try {

			List<ApplicationFileModel> response;

			if ((mainframeDomain.equalsIgnoreCase("PROD")) || (mainframeDomain.equalsIgnoreCase("Prod"))) {
				response = applicationService.fetchAllApplicationswithWildcard(Appl_Id, "Prod");
			} else if ((mainframeDomain.equalsIgnoreCase("DALLAS")) || (mainframeDomain.equalsIgnoreCase("Dallas"))) {
				response = applicationService.fetchAllApplicationswithWildcard(Appl_Id, "Dallas");
			} else {
				throw new IllegalArgumentException("Invalid mainframe_domain value: " + mainframeDomain);
			}

			List<HeaderType> headerTypeList = new ArrayList<>();

			HeaderType headerType = new HeaderType();
			headerType.setHeader("MEMBER");
			headerType.setKey("appl_Id");

			HeaderType headerType2 = new HeaderType();
			headerType2.setHeader("APPLICATION");
			headerType2.setKey("appl_name");

			HeaderType headerType3 = new HeaderType();
			headerType3.setHeader("DESCRIPTION");
			headerType3.setKey("appl_name_Desc_1");
			headerTypeList.add(headerType);
			headerTypeList.add(headerType2);
			headerTypeList.add(headerType3);

			applicationJson.setConfig(headerTypeList);
			applicationJson.setData(response);
			System.out.println("reterving the list of data set========" + Appl_Id);
			return applicationJson;
		} catch (Exception e) {
			System.out.println("NullPointerException thrown!");
			return applicationJson;
		}

	}



}
