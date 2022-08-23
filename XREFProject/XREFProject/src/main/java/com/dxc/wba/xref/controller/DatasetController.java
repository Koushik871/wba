package com.dxc.wba.xref.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.dbmodel.XrefDataset;
import com.dxc.wba.xref.service.DatasetService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DatasetController {
     @Qualifier("datasetService")
	@Autowired
	private DatasetService datasetService;

	@GetMapping("/getAllDataset")
	public ResponseEntity<Object> getAllDataset() {
		// log.info("Started getAllDataset() with DatasetController");
		try {
			List<XrefDataset> datasets = datasetService.getAllDatasets();
			return new ResponseEntity<>(datasets, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

		
	
	@GetMapping("/getDataset")
	public ResponseEntity<Object> getDatasetName() {
		// log.info("Started getAllDataset() with DatasetController");
		try {
			List<XrefDataset> datasets = datasetService.getAllDatasets();
			return new ResponseEntity<>(datasets, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/*
	 * @GetMapping(value = "list") public JSONObject
	 * fetchMultipleTableDatawithWildcards(@RequestParam (required = false , name =
	 * "Dataset_Name")String Dataset_Name) {
	 * System.out.println("reterving the list of data set"); try { JSONObject
	 * response = new JSONObject();
	 * response.put("dataSet",datasetService.fetchAllDatasetDatawithwildcards(
	 * Dataset_Name)); //
	 * response.put("",dataservice.fetchAllDatasetData(Dataset_Name)); //
	 * response.put("",dataservice.fetchAllDatasetData(Dataset_Name));
	 * response.put("job-name",datasetService.fetchAllJobNameData());
	 * response.put("db2plan",datasetService.fetchAllDB2PlanData());
	 * response.put("db2view",datasetService.fetchAllDb2View()); return response; }
	 * catch (Exception e) { // log.error("Error in Fetchting the dataset :: ", e);
	 * System.out.println(e); return null; }
	 * 
	 * } }
	 */
	@GetMapping("/getDatasetByDatasetName")
    public ResponseEntity<XrefDataset> fetchAllByDatasetname(@RequestParam (required = false , name = "Dataset_Name")String Dataset_Name) {
        System.out.println("reterving the list of data set");
        try {
            
           ResponseEntity<XrefDataset> response = (ResponseEntity<XrefDataset>) datasetService.getDatasetName(Dataset_Name);


           return  response;
        } catch (Exception e) {
//            log.error("Error in Fetchting the dataset :: ", e);
            System.out.println(e);
            return null;
        }
    }

}
