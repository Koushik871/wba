package com.dxc.wba.xref.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.dbmodel.CatProcModel;

@Repository
public interface CatProcRepository extends JpaRepository<CatProcModel, Long> {

	@Query(value = "SELECT * FROM xref.xref_cat_proc  where procName like %:procName% ", nativeQuery = true)
	List<CatProcModel> getCatProcByProcName(String procName);

	@Query(value = "SELECT * FROM xref.xref_cat_proc  where procName like :procName% ", nativeQuery = true)
	List<CatProcModel> getCatProcByProcNameByWildcards(String procName);

	@Query(value = "SELECT * FROM xref.xref_cat_proc  where procName = :procName ", nativeQuery = true)
	List<CatProcModel> getCatProcByProcNames(String procName);

	@Query(value = "SELECT distinct procname as member FROM xref.xref_cat_proc where procname = :procName ", nativeQuery = true)
	List<Map<String, Object>> getProcName(String procName);

	@Query(value = "SELECT distinct procname as member FROM xref.xref_cat_proc where procname like :procName% ", nativeQuery = true)
	List<Map<String, Object>> getProcNamewithwildcards(String procName);

	@Query(value = "SELECT * FROM xref.xref_proc_job_progm  where procname like :procName% and mainframe_domain = :mainframeDomain", nativeQuery = true)
	List<CatProcModel> getCatProcByProcNameByWildcardsAndMainframeDomain(String procName, String mainframeDomain);

	@Query(value = "SELECT * FROM xref.xref_proc_job_progm  where procname = :procName and mainframe_domain = :mainframeDomain", nativeQuery = true)
	List<CatProcModel> getCatProcByProcNamesAndMainframeDomain(String procName, String mainframeDomain);

}
