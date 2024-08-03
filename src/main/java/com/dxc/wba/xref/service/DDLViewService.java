package com.dxc.wba.xref.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.wba.xref.ddentity.DDLViews;
import com.dxc.wba.xref.ddentity.DDLViewsDB2D;
import com.dxc.wba.xref.repository.DDLViewDb2DRepository;
import com.dxc.wba.xref.repository.DDLViewRepository;
import com.dxc.wba.xref.type.DB2DViewsJson;
import com.dxc.wba.xref.type.DDLViewJson;
import com.dxc.wba.xref.type.DdviewJson;
import com.dxc.wba.xref.type.Header;

@Service

public class DDLViewService {

	@Autowired
	private DDLViewRepository repository;

	@Autowired
	private DDLViewDb2DRepository db2drepo;

	public DdviewJson getDDLByViewName(String ddl_view_name) {
		DdviewJson ddl = new DdviewJson();
		try {

			List<Map<String, Object>> response = new ArrayList<>();
//			List<DDLViews> response = new ArrayList<>();
			response = repository.getDDLByViewName(ddl_view_name);

			headersforddl();
			ddl.setConfig(headersforddl());
			ddl.setData(response);
			return ddl;

		} catch (Exception e) {
			return null;
		}

	}

	public DDLViewJson getViewbyViewName(String ddl_view_name) {
		DDLViewJson ddl = new DDLViewJson();
		try {
			List<DDLViews> response = new ArrayList<>();
			response = repository.getViewByViewName(ddl_view_name);

			headersforview();
			ddl.setConfig(headersforview());
			ddl.setData(response);
			return ddl;

		} catch (Exception e) {
			return null;
		}

	}

	public DDLViewJson getViewByTableNameandSchemaName(String schema_name, String table_name) {

		DDLViewJson ddl = new DDLViewJson();
		try {

			String value = new String();
			value = value.replace(value, schema_name + "." + table_name);
			List<DDLViews> response = new ArrayList<>();
			response = repository.getDDLByTableNameandSchemaName(value);

			headersforview();
			ddl.setConfig(headersforview());
			ddl.setData(response);
			return ddl;

		} catch (Exception e) {
			return null;
		}

	}

	public DdviewJson getDB2DDDLByViewName(String ddl_view_name) {
		DdviewJson ddl = new DdviewJson();
		try {

			List<Map<String, Object>> response = new ArrayList<>();
//				List<DDLViews> response = new ArrayList<>();
			response = db2drepo.getDB2DDDLByViewName(ddl_view_name);

			headersforddl();
			ddl.setConfig(headersforddl());
			ddl.setData(response);
			return ddl;

		} catch (Exception e) {
			return null;
		}

	}

	public DB2DViewsJson getDB2DViewByTableNameandSchemaName(String schema_name, String table_name) {

		DB2DViewsJson ddl = new DB2DViewsJson();
		try {

			String value = new String();
			value = value.replace(value, schema_name + "." + table_name);
			List<DDLViewsDB2D> response = new ArrayList<>();
			response = db2drepo.getDB2DViewByTableNameandSchemaName(value);

			headersforview();
			ddl.setConfig(headersforview());
			ddl.setData(response);
			return ddl;

		} catch (Exception e) {
			return null;
		}

	}

	private List<Header> headersforview() {

		List<Header> headerList = new ArrayList<>();
		Header headerType3 = new Header();
		headerType3.setHeader("ddlViewlinetext");
		headerType3.setKey("ddViewLineText");

		headerList.add(headerType3);
		return headerList;

	}

	private List<Header> headersforddl() {

		List<Header> headerList = new ArrayList<>();
		Header headerType1 = new Header();
		headerType1.setHeader("ddlViewName");
		headerType1.setKey("dd_view_name");
		headerList.add(headerType1);
		return headerList;

	}

}
