package com.dxc.wba.xref.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.dbmodel.XrefProcJobProgm;

@Repository
public interface XrefProcJobProgmRepository extends JpaRepository<XrefProcJobProgm, Long> {

	@Query(value = "SELECT * FROM xref.xref_proc_job_progm  where jobname like :jobName% order by procName", nativeQuery = true)
	List<XrefProcJobProgm> getByJobName(String jobName);

	List<XrefProcJobProgm> findByJobName(String jobName);

	@Query(value = "SELECT * FROM xref.xref_proc_job_progm  where jobname like :jobName% and mainframe_domain =:mainframeDomain order by procName", nativeQuery = true)
	List<XrefProcJobProgm> findByJobNameAndMainframeDomains(String jobName, String mainframeDomain);

	List<XrefProcJobProgm> findByJobNameAndMainframeDomain(String jobName, String mainframeDomain);

	@Query(value = "SELECT * FROM xref.xref_proc_job_progm  where procname like :procName% and mainframe_domain = :mainframeDomain", nativeQuery = true)
	List<XrefProcJobProgm> getCatProcByProcNameByWildcardsAndMainframeDomain(String procName, String mainframeDomain);

	@Query(value = "SELECT * FROM xref.xref_proc_job_progm  where procname = :procName and mainframe_domain = :mainframeDomain", nativeQuery = true)
	List<XrefProcJobProgm> getCatProcByProcNamesAndMainframeDomain(String procName, String mainframeDomain);

	@Query(value = "SELECT * FROM xref.xref_proc_job_progm WHERE progname LIKE %:progName% AND mainframe_domain = :mainframeDomain ", nativeQuery = true)
	List<XrefProcJobProgm> findByProgNameStartingWithAndMainframeDomain(String progName, String mainframeDomain);

	@Query(value = "SELECT * FROM xref.xref_xref_program_plan WHERE progname LIKE %:progName% AND mainframe_domain = :mainframeDomain", nativeQuery = true)
	List<XrefProcJobProgm> getProgramPlanByprogNameAndMainframeDomain(@Param("progName") String progName,
			@Param("mainframeDomain") String mainframeDomain);
}
