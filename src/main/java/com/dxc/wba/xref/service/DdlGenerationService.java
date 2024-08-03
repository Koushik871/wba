package com.dxc.wba.xref.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.wba.xref.ddentity.DDLGeneration;
import com.dxc.wba.xref.repository.DdlGenerationRepository;
import com.dxc.wba.xref.type.DDLJson;
import com.dxc.wba.xref.type.Header;

@Service
public class DdlGenerationService {

	@Autowired
	private DdlGenerationRepository ddlRepo;

	public DDLJson getDDLByTableName(String ddl_gen_table_name) {

		DDLJson ddl = new DDLJson();
		try {
			List<DDLGeneration> response = new ArrayList<>();
			response = ddlRepo.getDDLByTableName(ddl_gen_table_name);

			headersforddl();
			ddl.setConfig(headersforddl());
			ddl.setData(response);
			return ddl;

		} catch (Exception e) {
			return null;
		}

	}

	public DDLJson getDDLByTableNameandSchemaName(String ddl_gen_table_name, String ddl_gen_schema_name) {

		DDLJson ddl = new DDLJson();
		try {

			List<DDLGeneration> response = new ArrayList<>();
			response = ddlRepo.getDDLByTableNameandSchemaName(ddl_gen_table_name, ddl_gen_schema_name);

			headersforddlgen();
			ddl.setConfig(headersforddlgen());
			ddl.setData(response);
			return ddl;

		} catch (Exception e) {
			return null;
		}

	}

	private List<Header> headersforddlgen() {

		List<Header> headerList = new ArrayList<>();

		Header headerType6 = new Header();
		headerType6.setHeader("Table Name");
		headerType6.setKey("ddlGenTableName");

		Header headerType7 = new Header();
		headerType7.setHeader("Schema Name");
		headerType7.setKey("ddlGenSchemaName");

		Header headerType = new Header();
		headerType.setHeader("Column Name");
		headerType.setKey("ddlColumnName");

		Header headerType1 = new Header();
		headerType1.setHeader("Column Type");
		headerType1.setKey("ddlColType");
		
		Header headerType8 = new Header();
		headerType8.setHeader("Column Length ");
		headerType8.setKey("ddlColLength");

		Header headerType2 = new Header();
		headerType2.setHeader("Primary Key");
		headerType2.setKey("ddlPrimarykeyseq");

		;
		Header headerType3 = new Header();
		headerType3.setHeader("Foreign Key");
		headerType3.setKey("ddlForeignkey");

		Header headerType4 = new Header();
		headerType4.setHeader("Table Space");
		headerType4.setKey("ddlTablespace");

		headerList.add(headerType6);
		headerList.add(headerType7);
		headerList.add(headerType);
		headerList.add(headerType1);
		headerList.add(headerType8);
		headerList.add(headerType2);
		headerList.add(headerType3);
		headerList.add(headerType4);

		return headerList;

	}

	private List<Header> headersforddl() {

		List<Header> headerList = new ArrayList<>();

		Header headerType = new Header();
		headerType.setHeader("Schema Name");
		headerType.setKey("ddlGenSchemaName");

		Header headerType1 = new Header();
		headerType1.setHeader("Table Name");
		headerType1.setKey("ddlGenTableName");

		headerList.add(headerType);
		headerList.add(headerType1);

		return headerList;
	}

}
