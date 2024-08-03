package com.dxc.wba.xref.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.ddentity.DDLGeneration;

@Repository
public interface DdlGenerationRepository extends JpaRepository<DDLGeneration, Long> {

	@Query(value = "SELECT * FROM dd.ddlgeneration where ddl_gen_table_name like :ddl_gen_table_name%", nativeQuery = true)
	List<DDLGeneration> getDDLByTableName(String ddl_gen_table_name);

	@Query(value = "SELECT * FROM dd.ddlgeneration where ddl_gen_table_name = :table_name and  ddl_gen_schema_name =:schema_name order by ddl_col_seq", nativeQuery = true)
	List<DDLGeneration> getDDLByTableNameandSchemaName(String table_name, String schema_name);

}

