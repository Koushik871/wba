package com.dxc.wba.xref.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.dbmodel.TableViewEntity;

@Repository
public interface TableViewRepository extends JpaRepository<TableViewEntity, Long> {
	@Query(value = "SELECT * FROM xref.xref_table_view where table_view like %:TableView% ", nativeQuery = true)
	List<TableViewEntity> findBytableView(String TableView);

	@Query(value = "SELECT * FROM xref.xref_table_view where table_view like :tableView ", nativeQuery = true)
	List<TableViewEntity> fetchAllTableDatawithWildcard(String tableView);

	@Query(value = "SELECT *,CONCAT(db2plan, ' ', db2planver) AS db2planverbs ,CONCAT(dbrm, ' ', dbrmversion) AS  dbrmversions FROM xref.xref_table_view where table_view like %:tableView% order by DBRMVersion", nativeQuery = true)
	List<Map<String, Object>> getAlldata(String tableView);

	@Query(value = "SELECT *,CONCAT(db2plan, ' ', db2planver) AS db2planverbs ,CONCAT(dbrm, ' ', dbrmversion) AS  dbrmversions FROM xref.xref_db2plan where table_view like %:tableView%  order by DBRMVersion", nativeQuery = true)
	List<Map<String, Object>> getAlldb2plandata(String tableView);

	@Query(value = "SELECT *,CONCAT(db2plan, ' ', db2planver) AS db2planverbs ,CONCAT(dbrm, ' ', dbrmversion) AS  dbrmversions FROM xref.xref_db2plan where db2plan like %:DB2Plan% ", nativeQuery = true)
	List<TableViewEntity> db2plandata(String DB2Plan);

	@Query(value = "SELECT *  FROM xref.xref_table_view where table_view like :tableView% and verb like %:DB2Plan%", nativeQuery = true)
	List<TableViewEntity> findByDB2PlanOrTableView(String tableView, String DB2Plan);

	@Query(value = "SELECT *  FROM xref.xref_table_view where db2plan like :DB2Plan% and verb like %:tableView%", nativeQuery = true)
	List<TableViewEntity> findByDB2PlanOrTableViews(String DB2Plan, String tableView);

	@Query(value = "SELECT *,CONCAT(db2plan, ' ', db2planver) AS db2planverbs ,CONCAT(dbrm, ' ', dbrmversion) AS  dbrmversions FROM xref.xref_db2plan where table_view like :tableView% AND mainframe_domain = :mainframeDomain order by table_view", nativeQuery = true)
	List<Map<String, Object>> getAlldb2plandata(String tableView, String mainframeDomain);

	@Query(value = "SELECT *,CONCAT(db2plan, ' ', db2planver) AS db2planverbs ,CONCAT(dbrm, ' ', dbrmversion) AS  dbrmversions FROM xref.xref_table_view where table_view like :tableView%  AND mainframe_domain = :mainframeDomain order by table_view", nativeQuery = true)
	List<Map<String, Object>> getAlldata(String tableView, String mainframeDomain);

	@Query(value = "SELECT * FROM xref.xref_table_view where dbrm like :DBRM% and mainframe_domain=:mainframeDomain ", nativeQuery = true)
	List<TableViewEntity> findByDb2PlanStartingWithIgnoreCaseAndMainframeDomain(String DBRM, String mainframeDomain);

}
