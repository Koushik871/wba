package com.dxc.wba.xref.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dxc.wba.xref.dbmodel.ProclibMembers;

public interface ProclibRepository extends JpaRepository<ProclibMembers, Long> {

	@Query(value = "SELECT distinct u.membername FROM xref.proclib u where u.membername like = memberName%", nativeQuery = true)
	List<String> findbymemberName(@Param("memberName") String memberName);

	@Query(value = "SELECT * FROM xref.proclib u where u.membername = :memberName order by u.linenum asc", nativeQuery = true)
	List<ProclibMembers> findMemberName(@Param("memberName") String memberName);

	@Query(value = "SELECT distinct u.membername FROM xref.proclib u where u.membername like %:memberName% and  u.mainframe_domain = 'Prod' ", nativeQuery = true)
	List<String> findbymemberNameProd(@Param("memberName") String memberName);

	@Query(value = "SELECT distinct u.membername,u.dataset_name FROM xref.proclib u where u.membername like :memberName and  u.mainframe_domain = 'Prod' ", nativeQuery = true)
	List<Map<String, Object>> findbymemberNameandDatasetProd(@Param("memberName") String memberName);

	@Query(value = "SELECT * FROM xref.proclib u where u.membername = :memberName and u.mainframe_domain = 'Prod' order by u.linenum asc", nativeQuery = true)
	List<ProclibMembers> findMemberNameByProd(@Param("memberName") String memberName);

	@Query(value = "SELECT distinct u.membername FROM xref.proclib u where u.membername like :memberName% and  u.mainframe_domain = 'Dallas' ", nativeQuery = true)
	List<String> findbymemberNameDallas(@Param("memberName") String memberName);

	@Query(value = "SELECT distinct u.membername,u.dataset_name FROM xref.proclib u where u.membername like :memberName and  u.mainframe_domain = 'Dallas' ", nativeQuery = true)
	List<Map<String, Object>> findbymemberNameandDatasetDallas(@Param("memberName") String memberName);

	@Query(value = "SELECT * FROM xref.proclib u where u.membername = :memberName and u.mainframe_domain = 'Dallas' order by u.linenum asc", nativeQuery = true)
	List<ProclibMembers> findMemberNameByDallas(@Param("memberName") String memberName);

	@Query(value = "SELECT DISTINCT membername,dataset_name FROM xref.proclib  where membername like :memberName% and mainframe_domain = 'Dallas'", nativeQuery = true)
	List<Map<String, Object>> findDatsetNameDallas(@Param("memberName") String memberName);

	@Query(value = "SELECT * FROM xref.proclib u where u.membername = :memberName and u.dataset_name =:datasetLib and u.mainframe_domain = 'Dallas' order by u.linenum asc", nativeQuery = true)
	List<ProclibMembers> findMemberNameByDallass(String memberName, String datasetLib);
	
	@Query(value = "SELECT * FROM xref.proclib u where u.membername = :memberName and u.dataset_name =:datasetLib and u.mainframe_domain = 'Prod' order by u.linenum asc", nativeQuery = true)
	List<ProclibMembers> findMemberNameByProdd(String memberName, String datasetLib);
	
	
	@Query(value = "SELECT distinct u.membername,u.dataset_name FROM xref.proclib u where u.membername like :memberName% and  u.mainframe_domain = 'Prod' ", nativeQuery = true)
	List<Map<String, Object>> findbymemberandDatasetProdDrilldown(@Param("memberName") String memberName);
	
	@Query(value = "SELECT distinct u.membername,u.dataset_name FROM xref.proclib u where u.membername like :memberName% and  u.mainframe_domain = 'Dallas' ", nativeQuery = true)
	List<Map<String, Object>> findbymemberandDatasetDallasDrilldown(@Param("memberName") String memberName);

}
