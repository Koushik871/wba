package com.dxc.wba.xref.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.service.JoblibService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class JoblibController {

	@Autowired
	JoblibService joblibService;

	@GetMapping("Getjoblib/{mainframeDomain}/{memberName}")
	public Object GetProClib(@PathVariable String memberName, @PathVariable String mainframeDomain) {

		Object response = null;
		try {

			String datasetLib = null;
			String membername = null;
			System.out.println("retrieving the list of job prog" + memberName);
			if (memberName.endsWith("*")) {
				memberName = memberName.replace('*', ' ').trim();
				response = joblibService.getJoblibwildcard(memberName, mainframeDomain);

			} else if (memberName.contains("(") && memberName.endsWith(")")) {
				String[] params = memberName.split("\\(");
				datasetLib = params[0];
				if (params[1].contains(")")) {
					membername = params[1].substring(0, params[1].length() - 1);
				} else {
					membername = params[1];
				}
				response = joblibService.getJoblibFiless(membername, datasetLib, mainframeDomain);
			} else {
				membername = memberName;
				response = joblibService.getJoblibFiles(memberName, mainframeDomain);
			}

			System.out.println("retrieving the list of data set" + memberName);
		} catch (Exception e) {
			// handle exception
		}
		return response;
	}

	@GetMapping("Getjoblib/{memberName}")
	public Object GetJobfiles(@PathVariable String memberName) {

		Object response = null;
		try {
			response = joblibService.getjobFiles(memberName);
			System.out.println("reterving the list of data set" + memberName);

		} catch (Exception e) {

		}
		return response;

	}

}