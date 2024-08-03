package com.dxc.wba.xref.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.service.DDLViewService;
import com.dxc.wba.xref.type.DB2DViewsJson;
import com.dxc.wba.xref.type.DDLViewJson;
import com.dxc.wba.xref.type.DdviewJson;
@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
public class DDLViewController {
	
	@Autowired
	private DDLViewService service;
	
	
	/*
	 * PARTIAL INPUT
	 * To fetch the data by ViewName with one Parameter:: ViewName
	 * 
	 * */
	
	@GetMapping("/getView/{ddl_view_name}")
	public DdviewJson getDDLByViewName(@PathVariable(required = false) String ddl_view_name) {
		return service.getDDLByViewName(ddl_view_name);
	}
		
	
	/*
	 *FULLY QUALIFIED
	 * To fetch the data by "Schema Name" and "ViewName  with two Parameter::"Schema Name" "ViewName"
	 * API  input combines both parameters 
	 * 
	 *  
	 * */
	
	@GetMapping(value="/getViewByTableandSchema/{schema_name}/{table_name}")
	public DDLViewJson getViewByTableNameandSchemaName(@PathVariable(required = false) String schema_name,
			@PathVariable(required = false) String table_name) {
		return service.getViewByTableNameandSchemaName(schema_name, table_name);
	}
	
	/*
	 * PARTIAL INPUT
	 * To fetch the data by ViewName with one Parameter:: ViewName from  DDLViewsDB2D table
	 * 
	 * */
	
	
	// DDLViewsDB2D 
	
	@GetMapping("/getViewDB2D/{ddl_view_name}")
	public DdviewJson getDB2DDDLByViewName(@PathVariable(required = false) String ddl_view_name) {
		return service.getDB2DDDLByViewName(ddl_view_name);
	}
		
	
	@GetMapping(value="/getDB2DViewByTableandSchema/{schema_name}/{table_name}")
	public DB2DViewsJson getDB2DViewByTableNameandSchemaName(@PathVariable(required = false) String schema_name,
			@PathVariable(required = false) String table_name) {
		return service.getDB2DViewByTableNameandSchemaName(schema_name, table_name);
	}


}
