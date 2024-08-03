package com.dxc.wba.xref.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.dxc.wba.xref.dbmodel.DB2PlanEntity;
import com.dxc.wba.xref.dbmodel.TableViewEntity;

@Repository
public interface DB2PlanRepository extends JpaRepository<DB2PlanEntity, Long> {
	@Query(value = "SELECT * FROM xref.xref_db2plan where db2plan =:DB2Plan ", nativeQuery = true)
	List<DB2PlanEntity> getDB2PlandataBydb2plan(String DB2Plan);

	@Query(value = "SELECT * FROM xref.tableview", nativeQuery = true)
	List<TableViewEntity> getAlldata();

	@Query(value = "SELECT * FROM xref.xref_db2plan ", nativeQuery = true)
	List<Object> getDb2planDataBydb2plan(String DB2Plan);

	@Query(value = "SELECT * FROM xref.xref_db2plan where table_view like :tableView% ", nativeQuery = true)
	List<DB2PlanEntity> findBytableViews(String tableView);

	@Query(value = "SELECT * FROM xref.xref_db2plan where db2plan Like %:DB2Plan% ", nativeQuery = true)
	List<DB2PlanEntity> getAllByDb2plan(String DB2Plan);

	@Query(value = "SELECT *  FROM xref.xref_db2plan where table_view like :table_view% and verb like %:DB2Plan%", nativeQuery = true)
	List<DB2PlanEntity> findByDB2PlanOrTableView(String table_view, String DB2Plan);

	@Query(value = "SELECT *  FROM xref.xref_db2plan where db2plan like :DB2Plan% and verb like %:table_view%", nativeQuery = true)
	List<DB2PlanEntity> findByDB2PlanOrTableViews(String DB2Plan, String table_view);

	@Query(value = "SELECT *  FROM xref.xref_db2plan where dbrm like :DBRM% and mainframe_domain = :mainframeDomain ", nativeQuery = true)
	List<DB2PlanEntity> findByDb2planStartingWithAndMainframeDomain(String DBRM, String mainframeDomain);

}
