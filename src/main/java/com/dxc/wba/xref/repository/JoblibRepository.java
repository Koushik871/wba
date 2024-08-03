package com.dxc.wba.xref.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.dbmodel.JoblibMembers;

@Repository
public interface JoblibRepository extends JpaRepository<JoblibMembers, Long> {

//	@Query(value = "SELECT membername FROM xref.joblib where memberName like %:memberName%", nativeQuery = true)
	@Query(value = "SELECT distinct u.membername FROM xref.joblib u where u.membername like %:memberName%", nativeQuery = true)
	List<String> findbymemberName(@Param("memberName") String memberName);

	@Query(value = "SELECT * FROM xref.joblib u where u.membername = :memberName order by u.linenum asc", nativeQuery = true)
	List<JoblibMembers> findMemberName(@Param("memberName") String memberName);

	@Query(value = "SELECT distinct u.membername FROM xref.joblib u where u.membername like %:memberName% and  u.mainframe_domain = 'Prod' ", nativeQuery = true)
	List<String> findbymemberNameProd(@Param("memberName") String memberName);

	@Query(value = "SELECT * FROM xref.joblib u where u.membername = :memberName and u.mainframe_domain = 'Prod' order by u.linenum asc", nativeQuery = true)
	List<JoblibMembers> findMemberNameByProd(@Param("memberName") String memberName);

	@Query(value = "SELECT distinct u.membername FROM xref.joblib u where u.membername like :memberName% and  u.mainframe_domain = 'Dallas' ", nativeQuery = true)
	List<String> findbymemberNameDallas(@Param("memberName") String memberName);

	@Query(value = "SELECT distinct u.membername,u.dataset_name FROM xref.joblib u where u.membername like :memberName and  u.mainframe_domain = 'Dallas' ", nativeQuery = true)
	List<Map<String, Object>> findbymemberandDatasetNameDallas(@Param("memberName") String memberName);

	@Query(value = "SELECT distinct u.membername,u.dataset_name FROM xref.joblib u where u.membername like :memberName and  u.mainframe_domain = 'Prod' ", nativeQuery = true)
	List<Map<String, Object>> findbymemberandDatasetNameProd(@Param("memberName") String memberName);

	@Query(value = "SELECT * FROM xref.joblib u where u.membername = :memberName and u.mainframe_domain = 'Dallas' order by u.linenum asc", nativeQuery = true)
	List<JoblibMembers> findMemberNameByDallas(@Param("memberName") String memberName);

	@Query(value = "SELECT * FROM xref.joblib u where u.membername = :memberName and u.dataset_name=:datasetLib and u.mainframe_domain = 'Dallas' order by u.linenum asc", nativeQuery = true)
	List<JoblibMembers> findMemberNameByDallass(String memberName, String datasetLib);

	@Query(value = "SELECT * FROM xref.joblib u where u.membername = :memberName and u.dataset_name=:datasetLib and u.mainframe_domain = 'Prod' order by u.linenum asc", nativeQuery = true)
	List<JoblibMembers> findMemberNameByProdd(String memberName, String datasetLib);

	@Query(value = "SELECT distinct u.membername,u.dataset_name FROM xref.joblib u where u.membername like :memberName% and  u.mainframe_domain = 'Prod' ", nativeQuery = true)
	List<Map<String, Object>> findbymemberandDatasetProdDrilldown(@Param("memberName") String memberName);

	@Query(value = "SELECT distinct u.membername,u.dataset_name FROM xref.joblib u where u.membername like :memberName% and  u.mainframe_domain = 'Dallas' ", nativeQuery = true)
	List<Map<String, Object>> findbymemberandDatasetDallasDrilldown(@Param("memberName") String memberName);

}
