package com.dxc.wba.xref.service;

import com.dxc.wba.xref.dbmodel.XrefDataset;
import com.dxc.wba.xref.repository.XrefDatasetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DatasetService {

	@Autowired
	private XrefDatasetRepository xrefDatasetRepository;

	public List<XrefDataset> getAllDatasets() throws Exception {
		return xrefDatasetRepository.findAll();
	}
	public ResponseEntity<XrefDataset> getDatasetName(String Dataset_Name) throws Exception {
		return xrefDatasetRepository.findAllByDatasetName(Dataset_Name);
	}

}
