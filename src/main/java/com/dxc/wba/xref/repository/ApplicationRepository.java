package com.dxc.wba.xref.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.dbmodel.ApplicationFileModel;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationFileModel, Long> {

	@Query(value = "SELECT * FROM xref.xref_application where appl_name =:applName", nativeQuery = true)
	ApplicationFileModel getApplicationFilesByapplName(String applName);

	@Query(value = "SELECT * FROM xref.xref_application where appl_name like %:applName%", nativeQuery = true)
	List<ApplicationFileModel> getApplicationfilesWildcards(String applName);

	@Query(value = "SELECT * FROM xref.xref_application where appl_name like %:applName% AND mainframe_domain = :mainframeDomain ", nativeQuery = true)
	List<ApplicationFileModel> getApplicationfilesWildcards(String applName, String mainframeDomain);

}
