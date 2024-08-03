package com.dxc.wba.xref.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.dbmodel.XrefDataset;

@Repository("xrefDatasetRepository")
public interface XrefDatasetRepository extends JpaRepository<XrefDataset, Long> {

	
	List<XrefDataset> findByDatasetName(String datasetName);

    @Query(value ="SELECT * FROM xref.xref_dataset where datasetname like %:datasetName% order by dataset_id asc LIMIT 3000 " , nativeQuery = true)
	List<XrefDataset> fetchAllDatasetDatawithWildcard(String datasetName);

    @Query(value = "SELECT * FROM xref.xref_dataset WHERE datasetname LIKE %:datasetName% AND mainframe_domain = :mainframeDomain ORDER BY dataset_id ASC LIMIT 3000", nativeQuery = true)
    List<XrefDataset> fetchAllDatasetDatawithWildcardAndMainframeDomain(String datasetName, String mainframeDomain);


		

}
