package com.dxc.wba.xref.changedatacapture;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableChangesController {

	@Autowired
	private TrackingTableRepository trackingTableRepository;

	@GetMapping("/lastUpdate")
	public ResponseEntity<TableChangeResponse> getLastUpdateDate() {
		// Query the tracking table for the last update entry
		try {
			TrackingTableEntry lastUpdateEntry = trackingTableRepository.findLastUpdate();

			if (lastUpdateEntry == null) {
				return ResponseEntity.notFound().build();
			}

			// Create a response object with the formatted last update date
			TableChangeResponse response = new TableChangeResponse();
			response.setLastUpdateDate(
					lastUpdateEntry.getChangeTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			response.setLastUpdateTime(lastUpdateEntry.getChangeTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
			response.setTimestamp(lastUpdateEntry.getChangeTime());
			return ResponseEntity.ok(response);
		} catch (Exception e) {

			e.printStackTrace();

		}
		return null;
	}

	@GetMapping("/lastUpdates")
	public ResponseEntity<Map<String, Object>> getLastUpdateDates() {
	    try {
	        TrackingTableEntry lastUpdateEntry = trackingTableRepository.findLastUpdate();

	        List<Map<String, String>> configList = new ArrayList<>();

	        // Add header and key entries directly
	        Map<String, String> setLastUpdateDate = new LinkedHashMap<>();
	        setLastUpdateDate.put("header", "LastUpdateDate");
	        setLastUpdateDate.put("key", "lastUpdateDate");
	        configList.add(setLastUpdateDate);

	        Map<String, String> setLastUpdateTime = new LinkedHashMap<>();
	        setLastUpdateTime.put("header", "LastUpdateTime");
	        setLastUpdateTime.put("key", "lastUpdateTime");
	        configList.add(setLastUpdateTime);

	        Map<String, String> setTimestamp = new LinkedHashMap<>();
	        setTimestamp.put("header", "Timestamp");
	        setTimestamp.put("key", "timestamp");
	        configList.add(setTimestamp);

	        TableChangeResponse response = new TableChangeResponse();
	        
	        if (lastUpdateEntry != null) {
	            response.setLastUpdateDate(
	                    lastUpdateEntry.getChangeTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	            response.setLastUpdateTime(lastUpdateEntry.getChangeTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
	            response.setTimestamp(lastUpdateEntry.getChangeTime());
	        }

	        List<TableChangeResponse> responseData = new ArrayList<>();
	        if (lastUpdateEntry != null) {
	            responseData.add(response);
	        }

	        Map<String, Object> jsonResponse = new LinkedHashMap<>();
	        jsonResponse.put("config", configList);
	        jsonResponse.put("data", responseData);

	        return ResponseEntity.ok(jsonResponse);

	    } catch (Exception e) {
	        // Handle the exception gracefully, you can create a custom error response class
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}


}
