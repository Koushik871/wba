package com.dxc.wba.xref.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.dbmodel.ApplicationGroup;

@Repository
public interface ApplicationGroupRepository extends JpaRepository<ApplicationGroup, Long >{

	
	 @Query(value = "SELECT * FROM  xref.xref_application_group where appl_group like %:applGroup%", nativeQuery = true)
	 List<ApplicationGroup> getAllApplicationsByapplId(@Param ("applGroup") String applGroup);

	 
	 @Query(value ="SELECT * FROM xref.xref_application_group where appl_group like %?1% " , nativeQuery = true)
	List<ApplicationGroup> getAllByWildcards(String applGroup);

	
}
