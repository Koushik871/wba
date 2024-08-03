package com.dxc.wba.xref.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.service.ProclibService;
import com.dxc.wba.xref.type.CatProcJson;
import com.dxc.wba.xref.type.ProcNameJson;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ProclibController {

	@Autowired
	private ProclibService proclibService;

	@GetMapping("GetProClib/{mainframeDomain}/{memberName}")
	public Object GetProClib(@PathVariable String memberName, @PathVariable String mainframeDomain) {

		Object response = null;
		try {

			String datasetLib = null;
			String membername = null;
			if (memberName.endsWith("*")) {
                memberName= memberName.replace('*', ' ').trim();
				response = proclibService.getProclibwildcard(memberName, mainframeDomain);

			} else if (memberName.contains("(") && memberName.endsWith(")")) {
				String[] params = memberName.split("\\(");
				datasetLib = params[0];
				if (params[1].contains(")")) {
					membername = params[1].substring(0, params[1].length() - 1);
				} else {
					membername = params[1];
				}
				response = proclibService.getProclibFiless(membername, datasetLib, mainframeDomain);
			} else {
				membername = memberName;
				response = proclibService.getProclibFiles(memberName, mainframeDomain);
			}

			System.out.println("retrieving the list of data set" + memberName);
		} catch (Exception e) {
			// handle exception
		}
		return response;
	}

	@GetMapping(value = "getCatProc/{procName}")
	public CatProcJson getCatProcByProcName1(@PathVariable(required = false, name = "procName") String procName) {
		System.out.println("reterving the list of job prog" + procName);

		if (procName.contains("*")) {

			procName = procName.replace('*', ' ').trim();
			return proclibService.getCatProcByProcName1(procName);
		} else {
			return proclibService.getCatProcsByProcName(procName);
		}

	}

	@GetMapping(value = "getCatProcByProcName/{procName}")
	public CatProcJson getCatProcByProcName(@PathVariable(required = false, name = "procName") String procName) {

		return proclibService.getCatProcByProcName(procName);

	}

	// Inspecting a catproc

	@GetMapping(value = "/getProcName/{procName}")
	public ProcNameJson getCatProcName(@PathVariable(required = false, name = "procName") String procName) {

		return proclibService.getProcName(procName);
	}

	@GetMapping("GetProClib/{memberName}")
	public Object GetProClib(@PathVariable String memberName) {

		Object response = null;
		try {
			response = proclibService.getProclibFiles(memberName);
			System.out.println("reterving the list of data set" + memberName);

		} catch (Exception e) {

		}
		return response;

	}

	@GetMapping("GetProClibDrilldown/{memberName}")
	public Object GetProClibs(@PathVariable String memberName) {

		Object response = null;
		try {
			response = proclibService.getProclibFiles(memberName);
			System.out.println("reterving the list of data set" + memberName);

		} catch (Exception e) {

		}
		return response;

	}

}