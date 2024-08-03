package com.dxc.wba.xref.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dxc.wba.xref.dbmodel.ParmLibMembers;

public interface ParmLibRepository extends JpaRepository<ParmLibMembers, Long> {

	@Query(value = "SELECT distinct u.membername FROM xref.parmlib u where u.membername like %:memberName%", nativeQuery = true)
	List<String> findbymemberName(@Param("memberName") String memberName);

	@Query(value = "SELECT distinct u.membername FROM xref.parmlib u where u.membername like %:memberName% and u.mainframe_domain= :mainframeDomain", nativeQuery = true)
	List<String> findbymemberNameandMainframeDomain(@Param("memberName") String memberName,
			@Param("mainframeDomain") String mainframeDomain);

	@Query(value = "SELECT distinct u.membername,u.dataset_lib FROM xref.parmlib u where u.membername like :memberName% and u.mainframe_domain= :mainframeDomain", nativeQuery = true)
	List<Map<String, String>> findbymemberNamedatasetlibandMainframeDomain(@Param("memberName") String memberName,
			@Param("mainframeDomain") String mainframeDomain);

	@Query(value = "SELECT * FROM xref.parmlib u where u.membername = :memberName order by u.linenum asc", nativeQuery = true)
	List<ParmLibMembers> findMemberName(@Param("memberName") String memberName);

	@Query(value = "SELECT distinct u.membername FROM xref.parmlib u where u.dataset_lib like %:datasetLib% order by u.membername", nativeQuery = true)
	List<String> findbydatasetLib(@Param("datasetLib") String datasetLib);

	@Query(value = "SELECT distinct u.membername FROM xref.parmlib u where u.membername = :memberName ", nativeQuery = true)
	List<String> findbMemberName(@Param("memberName") String memberName);

	@Query(value = "SELECT * FROM xref.parmlib u where u.dataset_lib = :datasetLib and u.membername = :memberName  order by u.linenum asc", nativeQuery = true)
	List<ParmLibMembers> findByDatasetLibAndMembername(@Param("datasetLib") String datasetLib,
			@Param("memberName") String memberName);

	@Query(value = "SELECT distinct u.membername FROM xref.parmlib u where u.membername like %:param%", nativeQuery = true)
	List<String> findbymemberName1(@Param("param") String param);

	@Query(value = "SELECT * FROM xref.parmlib u where u.membername = :param order by u.linenum asc", nativeQuery = true)
	List<ParmLibMembers> findMemberName1(@Param("param") String param);

	@Query(value = "SELECT * FROM xref.parmlib u where u.dataset_lib = :datasetLib and u.membername = :memberName  order by u.linenum asc", nativeQuery = true)
	List<ParmLibMembers> findByDatasetLibAndMembernamea(@Param("datasetLib") String datasetLib,
			@Param("memberName") String memberName);

	@Query(value = "SELECT distinct u.membername FROM xref.parmlib u where u.membername like %:memberName%", nativeQuery = true)
	List<ParmLibMembers> findbymembername(@Param("memberName") String memberName);

	@Query(value = "SELECT distinct u.membername FROM xref.parmlib u where u.membername like %:memberName%", nativeQuery = true)
	List<String> findbymemberNameandDatase(@Param("memberName") String memberName);

	@Query(value = "SELECT * FROM xref.parmlib u where u.dataset_lib = :datasetLib order by u.dataset_lib", nativeQuery = true)
	List<ParmLibMembers> findByDatasetLib(@Param("datasetLib") String datasetLib);

	@Query(value = "SELECT * FROM xref.parmlib u where u.membername = :memberName and u.mainframe_domain = :mainframeDomain order by u.linenum asc", nativeQuery = true)
	List<ParmLibMembers> findByMemberNameAndDomain(@Param("memberName") String memberName,
			@Param("mainframeDomain") String mainframeDomain);

	@Query(value = "SELECT distinct u.membername FROM xref.parmlib u where u.membername like :memberName% and u.mainframe_domain = :mainframeDomain", nativeQuery = true)
	List<String> findbymemberNameAndDomain(@Param("memberName") String memberName,
			@Param("mainframeDomain") String mainframeDomain);

	@Query(value = "SELECT * FROM xref.parmlib u where u.dataset_lib = :datasetLib and u.mainframe_domain = :mainframeDomain  order by u.linenum asc", nativeQuery = true)
	List<ParmLibMembers> findByDatasetLibAndDomain(@Param("datasetLib") String datasetLib,
			@Param("mainframeDomain") String mainframeDomain);

	@Query(value = "SELECT distinct u.membername FROM xref.parmlib u where u.dataset_lib like %:datasetLib% and u.mainframe_domain = :mainframeDomain order by u.membername", nativeQuery = true)
	List<String> findbydatasetLibAndDomain(String datasetLib, String mainframeDomain);

	@Query(value = "SELECT distinct  membername,dataset_lib FROM xref.parmlib  where dataset_lib like %:datasetLib% and mainframe_domain = :mainframeDomain order by membername", nativeQuery = true)
	List<Map<String, String>> findbydatasetLibAndDomain1(String datasetLib, String mainframeDomain);

	@Query(value = "SELECT * FROM xref.parmlib u where u.dataset_lib = :datasetLib and u.membername = :memberName and u.mainframe_domain = :mainframeDomain order by u.linenum asc", nativeQuery = true)
	List<ParmLibMembers> findByDatasetLibAndMemberNameAndDomain(String datasetLib, String memberName,
			String mainframeDomain);

	@Query(value = "SELECT distinct u.membername,u.dataset_lib FROM xref.parmlib u where u.membername like :memberName% and u.dataset_lib like :datasetLib% and u.mainframe_domain= :mainframeDomain", nativeQuery = true)
	List<Map<String, String>> findbymemberNamedatasetlibandMainframeDomain(String memberName, String datasetLib,
			String mainframeDomain);
}
