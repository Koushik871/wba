package com.dxc.wba.xref.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.ddentity.MemberDefinitonTemp;

@Repository
public interface MemberdefinitionTempRepository extends JpaRepository<MemberDefinitonTemp, Long> {

	@Query(value = "select * from dd.member_definition_temp where member_name like %:memberName%", nativeQuery = true)
	public List<MemberDefinitonTemp> findByMemberName(String memberName);

	@Query(value = "select * from dd.member_definition_temp where member_name = :memberName", nativeQuery = true)
	public List<Map<String, Object>> findByMember(String memberName);

	@Query("update MemberDefinitonTemp u SET u.memberLineText =:text WHERE u.row_no =:id")
	@Modifying
	@Transactional
	public Integer updatePriceByName(String text, int id);

	@Query(value = "select distinct  member_name as membername,member_type as  type,obj_type from dd.member_definition_temp where member_name like :memberName% order by membername ", nativeQuery = true)
	public List<Map<String, Object>> findByMemberName1(String memberName);

	@Query(value = "select distinct  member_name as membername,member_type as  type,obj_type from dd.member_definition_temp where member_name like :memberName% and obj_type like :dDtype% order by membername ", nativeQuery = true)
	public List<Map<String, Object>> findByMemberName1(String memberName, String dDtype);

	@Modifying
	@Transactional
	@Query("delete from MemberDefinitonTemp  where member_name=:memberName ")
	public void deleteByMemberName(String memberName);

	@Query(value = "select * from dd.member_definition_temp where member_name like :memberName%", nativeQuery = true)
	public List<String> findByMemberNames(String memberName);

	@Query(value = "select distinct  member_name as membername,member_type as  type from dd.member_definition_temp where member_name like :member", nativeQuery = true)
	public String findByMemberNameByMemberName(String member);

	@Query(value = "Select * from (select DISTINCT member_name as membername,member_type  as type  from dd.member_definition_temp  where member_name between :member and :member1)p order by membername asc", nativeQuery = true)
	public List<Map<String, Object>> findRange(String member, String member1);

	@Query(value = "select distinct  member_name as membername,member_type as  type from dd.member_definition_temp where member_name like :member1", nativeQuery = true)
	public String findByMemberNameByMemberNames(String member1);

}
