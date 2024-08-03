package com.dxc.wba.xref.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.ddentity.MemberRelation;

@Repository
public interface DfMemberRepository extends JpaRepository<MemberRelation, Long> {
//	@Query(value = "select * from dd.member_relation where  member_name =:related_member", nativeQuery = true)
	public List<MemberRelation> findByMemberName(String related_member);

	@Modifying
	@Transactional
	@Query("delete from MemberRelation where memberName = :memberName")
	public void deleteByMemberName(@Param("memberName") String memberName);

	@Query(value = "select * from dd.member_relation where  member_name =:related_member", nativeQuery = true)
	MemberRelation findByMemberNames(String related_member);

	@Query(value = "select distinct member_name as membername, member_type as type from dd.member_relation where related_member ~ CONCAT('\\m', :related_member, '\\M')", nativeQuery = true)
	public List<Map<String, Object>> findByRelName(String related_member);

	@Query(value = "select * from dd.member_relation where related_member ~ CONCAT('\\m', :related_member, '\\M')", nativeQuery = true)
	List<MemberRelation> findByRelName1(String related_member);

//    @Query(value="select distinct  member_name ,member_type  from dd.member_relation where  related_member between :member and :member1",nativeQuery =true)
//	public List<Map<String, Object>> findByMemberRange(String member, String member1);

	@Query(value = "select distinct  member_name ,member_type  from dd.member_relation  WHERE member_name like %:member% or member_name like :member1%  or  member_name between :member  and :member1", nativeQuery = true)
	public List<Map<String, Object>> findByMemberRange(String member, String member1);

	@Query(value = "select MAX(row_no)   from dd.member_relation  where related_member like :member%", nativeQuery = true)
	public Integer findByRowNo(String member);

	@Query(value = "select DISTINCT member_name as membername,member_type as type  from dd.member_relation   where row_no between :dfrow_no1 and :dfrow_no2", nativeQuery = true)
	public List<Map<String, Object>> findByBetweenRowNo(Integer dfrow_no1, Integer dfrow_no2);

	@Query("update MemberRelation u SET u.relatedMember =:text WHERE u.row_no =:id")
	@Modifying
	@Transactional
	public Integer updatePriceByName(String text, Integer id);

	@Modifying
	@Transactional
	@Query("delete from MemberRelation where memberName = :memberName and memberRelation =:memberRelation")
	public void deleteByMemberNameAndMemberRelation(@Param("memberName") String memberName,
			@Param("memberRelation") String memberRelation);

	@Modifying
	@Transactional
	@Query("delete from MemberRelation where memberName = :memberName")
	public void deleteByMemberName1(@Param("memberName") String memberName);
}