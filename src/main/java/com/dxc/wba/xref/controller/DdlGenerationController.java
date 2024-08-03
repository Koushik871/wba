package com.dxc.wba.xref.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.service.DdlGenerationService;
import com.dxc.wba.xref.type.DDLJson;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DdlGenerationController {
	
	@Autowired
	private DdlGenerationService service;
	
	@GetMapping("/getTable/{ddl_gen_table_name}")
	public DDLJson getDDLByTableName(@PathVariable(required = false) String ddl_gen_table_name) {
		return service.getDDLByTableName(ddl_gen_table_name);
	}
	
	@GetMapping("/getTableandSchema/{ddl_gen_table_name}/{ddl_gen_schema_name}")
	public DDLJson getDDLByTableNameandSchemaName(@PathVariable(required = false) String ddl_gen_table_name,@PathVariable(required = false) String ddl_gen_schema_name) {
		return service.getDDLByTableNameandSchemaName(ddl_gen_table_name,ddl_gen_schema_name);
	}


}
