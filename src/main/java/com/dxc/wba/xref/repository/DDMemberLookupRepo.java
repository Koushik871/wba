package com.dxc.wba.xref.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.ddentity.DDMemberLookup;

@Repository
public interface DDMemberLookupRepo extends JpaRepository<DDMemberLookup, Double> {

	@Query(value = "select distinct member_org as obj_type, member_name as membername,member_type as  type from dd.member_definition_lookup where member_name like :value%  limit 1000 ", nativeQuery = true)
	public List<Map<String, Object>> findByMemberNamePandG(String value);

	@Query(value = "SELECT member_org AS obj_type, member_name AS membername, member_type AS type FROM dd.member_definition_lookup WHERE member_name LIKE :memberName% AND member_org LIKE :dDType% LIMIT 1000", nativeQuery = true)
	public List<Map<String, Object>> findByProgramMember(@Param("memberName") String memberName,
			@Param("dDType") String dDType);

	@Query(value = "SELECT member_org AS obj_type, member_name AS membername, member_type AS type FROM dd.member_definition_lookup WHERE member_name between CONCAT(:member, '%') AND CONCAT(:member1, '%')  or   member_name like CONCAT(:member1, '%') AND member_org LIKE :dDType% LIMIT 1000", nativeQuery = true)
	public List<Map<String, Object>> findPgAndDbRange(@Param("member") String member, @Param("member1") String member1,
			@Param("dDType") String dDType);

//	@Query(value = "select  member_org as obj_type, member_name as membername,member_type as  type from dd.member_definition_lookup where member_name between :member and :member1 limit 1000 ", nativeQuery = true)
//	public List<Map<String, Object>> findByDatabaseMember(String memberName, String member1);

	@Query(value = "SELECT distinct member_org AS obj_type, member_name AS membername, member_type AS type \r\n"
			+ "FROM dd.member_definition_lookup \r\n"
			+ "WHERE member_name BETWEEN CONCAT(:member, '%') AND CONCAT(:member1, '%') or member_name like CONCAT(:member1, '%') or member_name =:member ", nativeQuery = true)
	public List<Map<String, Object>> findByMemberLookupRange(String member, String member1);

	@Query(value = "SELECT  member_name AS membername, member_type AS type \r\n"
			+ "FROM dd.member_definition_lookup \r\n"
			+ "WHERE member_name BETWEEN CONCAT(:member, '%') AND CONCAT(:member1, '%') order by member_name ", nativeQuery = true)
	public List<Map<String, Object>> findByMemberLookupRange1(String member, String member1);

	@Query(value = "SELECT  member_name AS membername, member_type AS type \r\n"
			+ "FROM dd.member_definition_lookup \r\n" + "WHERE member_name = :member ", nativeQuery = true)
	public List<Map<String, Object>> findByMember1(String member);

	@Query(value = "select row_no from dd.member_definition_lookup  where member_name like CONCAT(:member, '%') order by row_no asc limit 1", nativeQuery = true)
	public Integer findBydfRowNo(String member);

	@Query(value = "select row_no  from dd.member_definition_lookup  where member_name like CONCAT(:member1, '%') order by row_no desc limit 1", nativeQuery = true)
	public Integer findBydfRowNo2(String member1);

	@Query(value = "select MAX(row_no)   from dd.member_definition_lookup  where member_name <= :member1 and  member_name like CONCAT(:member1, '%')", nativeQuery = true)
	public Integer findByRowNo3(String member1);

	@Query(value = "Select * from (select DISTINCT row_no, member_name as membername,member_type  as type  from dd.member_definition_lookup  where row_no between :row_no1 and :row_no2)p order by membername asc", nativeQuery = true)
	public List<Map<String, Object>> findRangePandD(Integer row_no1, Integer row_no2);

	@Query(value = "SELECT row_no, member_name AS membername, member_type AS type FROM dd.member_definition_lookup WHERE member_name like :member% ", nativeQuery = true)
	public List<Map<String, Object>> findByMemberPgandDB(String member);

	@Modifying
	@Transactional
	@Query("delete from DDMemberLookup  where member_name=:memberName ")
	public void deleteByMemberName(String memberName);

	@Query(value = "SELECT * from dd.member_definition_lookup  where member_name=:memberName", nativeQuery = true)
	public List<Map<String, Object>> findByMember(String memberName);

	@Query(value = "SELECT * from dd.member_definition_lookup  where member_name=:memberName", nativeQuery = true)
	public List<DDMemberLookup> findByMembername(String memberName);

	@Query(value = "Select distinct  member_name as membername ,member_type as type from dd.member_definition where  member_line_text like %:related_member%", nativeQuery = true)
	public List<Map<String, Object>> findByMemName(String related_member);

	@Query(value = "select member_name  from dd.member_definition_lookup  where member_name like :member% order by member_name asc limit 1", nativeQuery = true)
	public String findmaxmembername1(String member);

	@Query(value = "select MIN(member_name)  from dd.member_definition_lookup  where member_name >=  :member", nativeQuery = true)
	public String findmaxrowmembername1(String member);

	@Query(value = "select member_name  from dd.member_definition_lookup  where  member_name like :member1% order by member_name desc limit 1", nativeQuery = true)
	public String findmaxmembername2(String member1);

	@Query(value = "select MAX(member_name)  from dd.member_definition_lookup  where  member_name <= :member1", nativeQuery = true)
	public String findmaxrowmembername2(String member1);

	@Query(value = "select *  from dd.member_definition_lookup ", nativeQuery = true)
	public List<DDMemberLookup> findAllValues();

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO dd.member_definition_lookup (row_no,member_name, member_type, prod_status) VALUES (:rowNo,:memberName, :memberType, :prodStatus)", nativeQuery = true)
	void insertMemberDefinition(@Param("rowNo") long rowNo, @Param("memberName") String memberName,
			@Param("memberType") String memberType, @Param("prodStatus") String prodStatus);

	@Query(value = "select MAX(row_no)  from dd.member_definition_lookup ", nativeQuery = true)
	public long maxrow();

	@Query(value = "select row_no from dd.member_definition_lookup  where member_name =:member", nativeQuery = true)
	public double findByRowNumber(String member);

	@Query(value = "select member_name  from dd.member_definition_lookup  where  member_name like :member%", nativeQuery = true)
	public List<String> findByMemberss(String member);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO dd.member_definition_lookup (row_no,member_name, member_type, prod_status) VALUES (:rowNo,:memberName, :memberType, :prodStatus)", nativeQuery = true)
	public void insertMemberDefinition1(@Param("rowNo") Double rowNo, @Param("memberName") String memberName,
			@Param("memberType") String memberType, @Param("prodStatus") String prodStatus);

//	@Query(value = "SELECT MAX(row_no) FROM dd.member_definition_lookup WHERE member_name LIKE 'member2' OR member_name IS NULL", nativeQuery = true)
//    Long findMaxRowNoForMember2();

}
