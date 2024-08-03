package com.dxc.wba.xref.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.dbmodel.OperationalInstructions;
import com.dxc.wba.xref.repository.OperInsRepository;
import com.dxc.wba.xref.service.OperInsService;
import com.dxc.wba.xref.type.HeaderType;
import com.dxc.wba.xref.type.OperInsJson;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class OperInsController {

	@Autowired
	OperInsService operInsService;

	@Autowired
	OperInsRepository opinsrepo;

	@PostMapping(value = "/addInstructions")
	public OperationalInstructions addInstructions(@RequestBody OperationalInstructions OperationalInstructions) {

		return operInsService.addInstruction(OperationalInstructions);
	}

	@PutMapping(value = "/updateInstruction/{applId}/{opNo}")
	public OperationalInstructions updateInstruction(@PathVariable String applId, @PathVariable String opNo,
			@RequestBody OperationalInstructions operationalInstructions) {
		return operInsService.updateInstruction(operationalInstructions);
	}

	@GetMapping(value = "/OperInsList/{applId}")
	public OperInsJson findByApplName(@PathVariable(required = false) String applId) {

		OperInsJson operInsJson = new OperInsJson();
		try {
			List<OperationalInstructions> response = operInsService.findByapplId(applId);

			operInsJson.setConfig(opheaders());
			operInsJson.setData(response);

			System.out.println("reterving the list " + applId);
			return operInsJson;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	@PostMapping(value = "/OperInstView")
	public OperInsJson findByApplNameandOpNo(@RequestParam(name = "applId") String applId,
			@RequestParam(name = "opNo") String opNo) {

		OperInsJson operInsJson = new OperInsJson();
		try {

			List<OperationalInstructions> response = operInsService.findByapplIdAndOpNo(applId, opNo);

//			response = response.stream().sorted((a, b) -> a.getLineNo().compareTo(b.getLineNo()))
//					.collect(Collectors.toList());

//			String value = "";
//			for (OperationalInstructions operationalInstructions : response) {
//
//				value = value + operationalInstructions.getInsOper();
//			}
//
//			for (OperationalInstructions operationalInstructions : response) {
//
//				operationalInstructions.setInsOper(value);
//			}

			opheaders1();

			operInsJson.setConfig(opheaders1());
			operInsJson.setData(response);

//			System.out.println("reterving the list of data set========" + applId);
			return operInsJson;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	private List<HeaderType> opheaders1() {
		List<HeaderType> headerTypeList = new ArrayList<>();

		HeaderType headerType = new HeaderType();
		headerType.setHeader("APPL ID");
		headerType.setKey("applId");

		HeaderType headerType1 = new HeaderType();
		headerType1.setHeader("OP No.");
		headerType1.setKey("opNo");

		HeaderType headerType2 = new HeaderType();
		headerType2.setHeader("valid to");
		headerType2.setKey("validTo");

		HeaderType headerType3 = new HeaderType();
		headerType3.setHeader("valid from");
		headerType3.setKey("validFrom");

		HeaderType headerType4 = new HeaderType();
		headerType4.setHeader("Instructions");
		headerType4.setKey("insOper");

		headerTypeList.add(headerType);
		headerTypeList.add(headerType1);
		headerTypeList.add(headerType2);
		headerTypeList.add(headerType3);
		headerTypeList.add(headerType4);
		return headerTypeList;

	}

	private List<HeaderType> opheaders() {
		List<HeaderType> headerTypeList = new ArrayList<>();

		HeaderType headerType = new HeaderType();
		headerType.setHeader("Member");
		headerType.setKey("memberName");

		HeaderType headerType1 = new HeaderType();
		headerType1.setHeader("Application");
		headerType1.setKey("applId");

		HeaderType headerType2 = new HeaderType();
		headerType2.setHeader("CPU ID");
		headerType2.setKey("opNo");

		headerTypeList.add(headerType);
		headerTypeList.add(headerType1);
		headerTypeList.add(headerType2);
		return headerTypeList;
	}

}
