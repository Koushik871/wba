package com.dxc.wba.xref.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.ddentity.MemberRelationObject;
@Repository
public interface DdMemberRelationRepository extends JpaRepository<MemberRelationObject, Long> {
	
	//@Query(value="select distinct  related_member as membername,related_mem_type as  type from dd.dd_db_member_relation where  related_member like :memberName%",nativeQuery =true)
	public List<MemberRelationObject> findByMemberName(String memberName);
    @Query(value="select distinct  related_member  as membername ,member_type  as type from dd.dd_db_member_relation where  related_member like :related_member%  order by membername",nativeQuery =true)
	public List<Map<String, Object>> findByRelName(String related_member);
    
//    @Query(value="select distinct  member_name ,member_type  from dd.member_relation where  related_member between :member and :member1",nativeQuery =true)
//	public List<Map<String, Object>> findByMemberRange(String member, String member1);
    
    
    @Query(value="select distinct  member_name ,member_type  from dd.dd_db_member_relation  WHERE member_name like %:member% or member_name like :member1%  or  member_name between :member  and :member1",nativeQuery =true)
	public List<Map<String, Object>> findByMemberRange(String member, String member1);

    @Query("update MemberRelationObject u SET u.relatedMember =:text WHERE u.row_no =:id")
	@Modifying
	@Transactional
	public Integer updatePriceByName(String text, Integer id);
}
