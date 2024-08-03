package com.dxc.wba.xref.repository;

import com.dxc.wba.xref.dbmodel.XrefDataset;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository("xrefDatasetRepository")
public interface XrefDatasetRepository extends JpaRepository<XrefDataset, Long> {

	@Query(value = "SELECT  x FROM xref_dataset x WHERE x.dataset_name = ?", nativeQuery = true)
	ResponseEntity<XrefDataset> findAllByDatasetName(String dataset_Name);

}
