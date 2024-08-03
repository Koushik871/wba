package com.dxc.wba.xref.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.ddentity.DDLViewsDB2D;
@Repository
public interface DDLViewDb2DRepository extends JpaRepository<DDLViewsDB2D , Long> {

	
	@Query(value = "SELECT distinct dd_view_name  FROM dd.ddlviews_db2d where dd_view_name like %:ddl_view_name%", nativeQuery = true)
	List<Map<String, Object>> getDB2DDDLByViewName(String ddl_view_name);
	
	
	@Query(value = "SELECT * FROM dd.ddlviews_db2d where dd_view_name = :value", nativeQuery = true)
	List<DDLViewsDB2D> getDB2DViewByTableNameandSchemaName(String value);

}
