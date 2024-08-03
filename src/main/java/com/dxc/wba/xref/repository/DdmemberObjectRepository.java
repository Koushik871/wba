package com.dxc.wba.xref.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.ddentity.MemberDefinitionObject;

@Repository
public interface DdmemberObjectRepository extends JpaRepository<MemberDefinitionObject, Long> {

//	@Query(value="select distinct  member_name as membername,member_type as  type from dd.member_definition where member_name like %:memberName%",nativeQuery =true)
	public List<MemberDefinitionObject> findByMemberName(String memberName);

	@Query(value = "select distinct  member_name as membername,member_type as  type from dd.dd_db_member_definition where member_name like %:memberName%   order by membername  ", nativeQuery = true)
public List<MemberDefinitionObject> findByMemberNames(String memberName);

	@Query(value = "select distinct  member_name as membername,member_type as  type from dd.dd_db_member_definition where member_name like :memberName% order by membername ", nativeQuery = true)
	public List<Map<String, Object>> findByMemberName1(String memberName);

	@Query(value = "select distinct  member_name as membername,member_type as  type from dd.dd_db_member_definition  where member_name between :member and :member1", nativeQuery = true)
	public List<Map<String, Object>> findByMemberRange(String member, String member1);

	@Query(value = "select MIN(row_no)  from dd.dd_db_member_definition  where member_name like :member%", nativeQuery = true)
	public Integer findByRowNo(String member);

	@Query(value = "select MAX(row_no)   from dd.dd_db_member_definition  where member_name like :member1%", nativeQuery = true)
	public Integer findByRowNo2(String member1);

	@Query(value = "select DISTINCT member_name as membername,member_type as type  from dd.dd_db_member_definition  where row_no between :row_no1 and :row_no2 order by membername", nativeQuery = true)
	public List<Map<String, Object>> findByBetweenRowNo(Integer row_no1, Integer row_no2);

	@Query(value = "select Distinct  member_type  from dd.dd_db_member_definition where member_name = :value ", nativeQuery = true)
	public String findbyprog(String value);

	@Query("update MemberDefinitionObject u SET u.memberLineText =:text WHERE u.row_no =:id")
	@Modifying
	@Transactional
	public Integer updatePriceByName(String text, int id);

	@Modifying
	@Transactional
	@Query("delete from MemberDefinitionObject  where member_name=:memberName ")
	void deleteByMemberName(String memberName);

	@Query(value = "select distinct  member_name as membername ,member_type as type from dd.dd_db_member_definition where  member_name like :memberName%", nativeQuery = true)
	public List<Map<String, Object>> findByMemName(String memberName);

}
