package com.dxc.wba.xref.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.dbmodel.OperationalInstructions;

@Repository
public interface OperInsRepository extends JpaRepository<OperationalInstructions, Long> {
	@Query(value = "SELECT * FROM xref.operational_instructions where applid like :applId%", nativeQuery = true)
	List<OperationalInstructions> findByapplId(String applId);

	@Query(value = "SELECT * FROM xref.operational_instructions where applid= :applId and op_no like %:opNo%", nativeQuery = true)
	List<OperationalInstructions> findByapplIdAndOpNo(String applId, String opNo);

	@Query(value = "SELECT * FROM xref.operational_instructions where applid like :applId% and op_no like %:opNo%", nativeQuery = true)
	OperationalInstructions findByapplIdAndOpNos(String applId, String opNo);

}
