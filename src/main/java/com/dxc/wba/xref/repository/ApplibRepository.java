package com.dxc.wba.xref.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.dbmodel.Applibmembers;

@Repository
public interface ApplibRepository extends JpaRepository<Applibmembers, Long> {

	@Query(value = "SELECT * FROM xref.applib u where u.appl_name = :applName order by u.linenum asc", nativeQuery = true)
	List<Applibmembers> findbyapplName(@Param("applName") String memberName);

	@Query(value = "SELECT * FROM xref.applib u where u.appl_name = :applName  AND mainframe_domain = :mainframeDomain order by u.linenum asc", nativeQuery = true)
	List<Applibmembers> findbyapplName(String applName, String mainframeDomain);

	@Query(value = "SELECT * FROM xref.applib u where u.appl_name like :applName% AND mainframe_domain = :mainframeDomain  order by u.linenum asc limit 1000", nativeQuery = true)
	List<Applibmembers> findbymemberNameDallas(String applName, String mainframeDomain);

}
