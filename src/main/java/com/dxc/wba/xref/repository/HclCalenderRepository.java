package com.dxc.wba.xref.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.dbmodel.HclCalender;

@Repository
public interface HclCalenderRepository extends JpaRepository<HclCalender, Long> {

	@Query(value = "SELECT distinct u.membername FROM xref.hcl_calender u where u.membername = %:memberName% ", nativeQuery = true)
	List<HclCalender> findbyMembername(@Param("memberName") String memberName);

}
