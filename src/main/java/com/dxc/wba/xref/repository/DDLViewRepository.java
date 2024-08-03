package com.dxc.wba.xref.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dxc.wba.xref.ddentity.DDLViews;

public interface DDLViewRepository extends JpaRepository<DDLViews , Long> {
	
	@Query(value = "SELECT distinct dd_view_name  FROM dd.ddlviews where dd_view_name like %:ddl_view_name%", nativeQuery = true)
	List<Map<String, Object>> getDDLByViewName(String ddl_view_name);
	
	
	@Query(value = "SELECT * FROM dd.ddlviews where dd_view_name = :ddl_view_name", nativeQuery = true)
	List<DDLViews> getViewByViewName(String ddl_view_name);

	@Query(value = "SELECT * FROM dd.ddlviews where dd_view_name = :value", nativeQuery = true)
	List<DDLViews> getDDLByTableNameandSchemaName(String value);

}
