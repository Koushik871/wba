package com.dxc.wba.xref.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.repository.DDMemberLookupRepo;
import com.dxc.wba.xref.service.DdMemberService;
import com.dxc.wba.xref.type.DDLdb2dJson;
import com.dxc.wba.xref.type.DDWduJson;
import com.dxc.wba.xref.type.DdMemJson;
import com.dxc.wba.xref.type.Mjson;

import edu.emory.mathcs.backport.java.util.Collections;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DdMemberController {

	@Autowired
	private DDMemberLookupRepo ddMemberlookupRepo;
	@Autowired
	private DdMemberService ddMemberService;

	@GetMapping("/getMemberpg/{member}")
	public DdMemJson getDDMemberPandD(@PathVariable(required = false) String member) {
		return ddMemberService.getDDMemberPandD(member);
	}

	@PostMapping(value = "/getRangeBetweenPandD")
	public DdMemJson getRangePandD(@RequestParam(value = "member") String member,
			@RequestParam(value = "member1") String member1) {
		return ddMemberService.getRangePandD(member, member1);
	}

	@GetMapping("/getWDU/{related_member}")
	public DDWduJson getWdu(@PathVariable(required = false) String related_member) {
		return ddMemberService.getWduMember(related_member);
	}

	@DeleteMapping("/deleteMember/{memberName}")
	public Mjson deleteUser(@PathVariable String memberName) {
		Mjson m = new Mjson();
		ddMemberService.deleteByMemberName(memberName);
		return m;

	}

	@GetMapping("/getDB2DTable/{ddl_gen_table_name}")
	public DDLdb2dJson getDDLDB2DByTableName(@PathVariable(required = false) String ddl_gen_table_name) {
		return ddMemberService.getDDLDB2DByTableName(ddl_gen_table_name);
	}

	@GetMapping("/getDB2DTableandSchema/{ddl_gen_table_name}/{ddl_gen_schema_name}")
	public DDLdb2dJson getDDLDB2DByTableNameandSchemaName(@PathVariable(required = false) String ddl_gen_table_name,
			@PathVariable(required = false) String ddl_gen_schema_name) {
		return ddMemberService.getDDLDB2DByTableNameandSchemaName(ddl_gen_table_name, ddl_gen_schema_name);
	}

	@GetMapping("/getPreviousMember")
	public Object getPreviousMember(@RequestParam String member) {

		try {
			// Step 4: Perform a database query to find the member
			List<String> ddMember = ddMemberlookupRepo.findByMemberss(member);

			if (ddMember != null) {
				String firstLetter = member.substring(0, 1);
				List<String> namesStartingWithFirstLetter = ddMemberlookupRepo.findByMemberss(firstLetter);
				namesStartingWithFirstLetter.add(member);
				// Step 6: Sort the names and pick the one before the member param
				Collections.sort(namesStartingWithFirstLetter);

				int indexOfMember = namesStartingWithFirstLetter.indexOf(member);
				if (indexOfMember > 0) {
					String previousName = namesStartingWithFirstLetter.get(indexOfMember - 1);
					double row = ddMemberlookupRepo.findByRowNumber(previousName);

					row = row += 0.1;

					return row;
				}
			} 
		} catch (Exception e) {
			// Handle exceptions here
			return null;
		}
		return member;

	}
}