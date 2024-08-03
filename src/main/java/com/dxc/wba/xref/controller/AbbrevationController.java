package com.dxc.wba.xref.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.service.AbbrevationsService;
import com.dxc.wba.xref.type.AbbrevationJson;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AbbrevationController {

	@Autowired
	AbbrevationsService abService;

	@GetMapping(value = "/findAbbrevations")
	public AbbrevationJson findAbbrevations() {
		return abService.findAll();

	}

}
