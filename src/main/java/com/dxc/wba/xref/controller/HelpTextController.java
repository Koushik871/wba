package com.dxc.wba.xref.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.service.HelpTextService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HelpTextController {
	@Autowired
	HelpTextService helpservice;

	@GetMapping(value = "getXrefHelp/{screenName}")
	private Object getXrefHelpText(@PathVariable String screenName) {

		return helpservice.getXrefHelpText(screenName);
	}

	@GetMapping(value = "getXrefFullHelp")
	private Object getXrefFullHelpText() {

		return helpservice.getXrefFullHelpText();
	}

	@GetMapping(value ="getDDHelp/{screenName}")
	private Object getDDHelpText(@PathVariable String screenName) {
		return helpservice.getDDHelpText(screenName);

	}

	@GetMapping(value = "getDDFullHelp")
	private Object getDDHelp() {
		return helpservice.getDDHelp();

	}

}
