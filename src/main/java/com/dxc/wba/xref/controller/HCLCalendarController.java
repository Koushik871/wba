package com.dxc.wba.xref.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.service.HclServiceImpl;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HCLCalendarController {

	@Autowired
	HclServiceImpl hclservice;

	@GetMapping("/getHclcalendar")
	public Object getHclcalendar() {

		Object response = null;
		try {
			response = hclservice.findbymemberName();
			System.out.println("reterving the list of hclcalnder");

		} catch (Exception e) {

		}
		return response;

	}

//	 
//	 @GetMapping("/getAbbreviations/{fileName}")
//	    public List<String> getAbbreviations(@PathVariable(required = false) String fileName)
//	            throws FileNotFoundException, IOException {
//	       return additionalOptionsService.readFile(fileName);
//	    }
//	 
//		

}