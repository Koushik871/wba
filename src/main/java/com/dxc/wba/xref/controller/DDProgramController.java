package com.dxc.wba.xref.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.service.DDProgramService;
import com.dxc.wba.xref.type.DDCategoryJson;
import com.dxc.wba.xref.type.DDCategoryUpdateJson;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DDProgramController {

	@Autowired
	private DDProgramService ddservice;

	@GetMapping("memberDataBrowseforupdate/{memberName}")
	public DDCategoryUpdateJson memberDataforUpdate(@PathVariable String memberName) {

		DDCategoryUpdateJson response = null;
		try {
			response = ddservice.memberDataBrowseforupdate(memberName);

		} catch (Exception e) {

		}
		return response;
	}

	@PostMapping("/createNewDDMember")
	public DDCategoryJson createMemberFromNewDatas(@RequestBody Map<String, List<String>> requestBody,
			@RequestParam(value = "newMemberId") String newMemberId,

			@RequestParam(value = "copyMemberId") String copyMemberId) {

		try {
			ddservice.createMemberFromNewDatas(copyMemberId, newMemberId, requestBody);
			return ddservice.memberDataBrowse(newMemberId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ddservice.memberDataBrowse(newMemberId);
	}

	@PutMapping("/UpdateDDMember/{memberName}")
	public DDCategoryJson updateMemberDataa(@RequestBody Map<String, List<String>> requestBody,
			@PathVariable String memberName) {

		return ddservice.updateMemberData(memberName, requestBody);

	}

	@GetMapping("memberDataBrowse/{memberName}")
	public DDCategoryJson memberDataBrowse(@PathVariable String memberName) {

		DDCategoryJson response = null;
		try {
			response = ddservice.memberDataBrowse(memberName);

		} catch (Exception e) {

		}
		return response;
	}

	@GetMapping("dataBrowseforNewMember")
	public Object memberDataBrowseforNew(@RequestParam(value = "newMemberId") String newMemberId,
			@RequestParam(value = "copyMemberId") String copyMemberId) {
		try {
			Object response = ddservice.memberDataBrowseforNew(newMemberId, copyMemberId);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



}
