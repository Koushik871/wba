package com.dxc.wba.xref.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.service.JobProgmService;
import com.dxc.wba.xref.service.ProclibService;
import com.dxc.wba.xref.type.JobProgJson;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JobProgrmController {

	@Autowired
	JobProgmService jobProgmService;

	@Autowired
	private ProclibService proclibServices;
	
	@GetMapping(value = "getDataByJobname/{mainframeDomain}/{jobName}")
	public JobProgJson getByJobPrograms(@PathVariable(required = false, name = "jobName") String jobName,
			@PathVariable(required = false, name = "mainframeDomain") String mainframeDomain) {
		System.out.println("reterving the list of job prog" + jobName);

		try {
			if (jobName.contains("*")) {

				jobName = jobName.replace('*', ' ').trim();

				return jobProgmService.getByJobNames(jobName, mainframeDomain);

			} else {

				return jobProgmService.findByJobNames(jobName, mainframeDomain);

			}

		} catch (Exception e) {
		}
		return null;
	}

	@GetMapping(value = "getCatProc/{mainframeDomain}/{procName}")
	public JobProgJson getCatProcByProcName1(@PathVariable(required = false, name = "procName") String procName,
			@PathVariable(required = false, name = "mainframeDomain") String mainframeDomain) {
		try {
			System.out.println("retrieving the list of job prog" + procName);
			if (procName.contains("*")) {
				procName = procName.replace('*', ' ').trim();
				return proclibServices.getCatProcByProcName1(procName, mainframeDomain);
			} else {
				return proclibServices.getCatProcsByProcName(procName, mainframeDomain);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping(value = "getDataByJobname/{jobName}")
	public JobProgJson getByJobProgram(@PathVariable(required = false, name = "jobName") String jobName) {
		System.out.println("reterving the list of job prog" + jobName);

		try {
			if (jobName.contains("*")) {

				jobName = jobName.replace('*', ' ').trim();

				return jobProgmService.getByJobName(jobName);

			} else {

				return jobProgmService.findByJobName(jobName);

			}

		} catch (Exception e) {
		}
		return null;
	}

	

}
