package com.dxc.wba.xref.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.ddentity.DDNewMemberTable;

@Repository
public interface NewMemberRepository extends JpaRepository<DDNewMemberTable, Long> {
	
	

	List<DDNewMemberTable> findByMemberName(String copyMemberId);
	
	@Modifying
	@Transactional
	@Query("delete from DDNewMemberTable where memberName = :memberName")
	public void deleteByMemberName(@Param("memberName") String memberName);
	
	
	@Query(value = "select distinct  member_name as membername,member_type as  type from dd.dd_new_member_table where member_name like :memberName% order by membername    ", nativeQuery = true)
	List<Map<String, Object>> findByMember(String memberName);

	
	@Query(value = "select * from dd.dd_new_member_table where member_name = :memberName order by line_no   ", nativeQuery = true)
	List<DDNewMemberTable> findMemberByMember(String memberName);


	

}
