package com.dxc.wba.xref.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.ddentity.DDMemberdefinition;

@Repository
public interface DDProgramRepository extends JpaRepository<DDMemberdefinition, Long> {

	@Query(value = "select * from dd.dd_dd_member_definition where member_name = :memberName order by line_no   ", nativeQuery = true)
	List<DDMemberdefinition> findMemberByMemberName(String memberName);
	
	
	
	
	
	
	@Query(value = "select * from dd.dd_dd_member_definition where member_name = :memberName    ", nativeQuery = true)
	DDMemberdefinition findMemberByMember(String memberName);

	@Modifying
	@Transactional
	@Query("delete from DDMemberdefinition where memberName = :memberName")
	public void deleteByMemberName(@Param("memberName") String memberName);
	
	

}
