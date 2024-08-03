package com.dxc.wba.xref.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.dbmodel.XrefProgramPlan;

@Repository
public interface XrefProgramPlanRepository extends JpaRepository<XrefProgramPlan, Long> {

	@Query(value = "SELECT * FROM xref.xref_xref_program_plan where progname like %:progName% ", nativeQuery = true)
	List<XrefProgramPlan> getProgramPlanByprogName(String progName);

	@Query(value = "SELECT * FROM xref.xref_xref_program_plan where progname like :progName ", nativeQuery = true)
	List<XrefProgramPlan> getSuggetionsByprogName(String progName);

	@Query(value = "SELECT * FROM xref.xref_xref_program_plan   where progname like :progName", nativeQuery = true)
	List<XrefProgramPlan> getDataOfProgram(String progName);

	@Query(value = "SELECT * FROM xref.xref_xref_program_plan where progname like %:progName% ", nativeQuery = true)
	List<Object> getProgramByprogName(String progName);



	@Query(value = "SELECT * FROM xref.xref_xref_program_plan WHERE progname LIKE %:progName% AND mainframe_domain = :mainframeDomain ", nativeQuery = true)
	List<XrefProgramPlan> findByProgNameStartingWithAndMainframeDomain(String progName, String mainframeDomain);

}
