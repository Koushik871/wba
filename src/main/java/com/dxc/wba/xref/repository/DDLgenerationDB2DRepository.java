package com.dxc.wba.xref.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.ddentity.DDLGenerationDB2D;

@Repository
public interface DDLgenerationDB2DRepository extends JpaRepository<DDLGenerationDB2D, Long> {
	
	
	@Query(value = "SELECT * FROM dd.ddlgeneration_db2d where ddl_gen_table_name like :ddl_gen_table_name%", nativeQuery = true)
	List<DDLGenerationDB2D> getDDLDB2DByTableName(String ddl_gen_table_name);

	@Query(value = "SELECT * FROM dd.ddlgeneration_db2d where ddl_gen_table_name = :table_name and  ddl_gen_schema_name =:schema_name order by ddl_col_seq", nativeQuery = true)
	List<DDLGenerationDB2D> getDDLDB2DByTableNameandSchemaName(String table_name, String schema_name);

}
