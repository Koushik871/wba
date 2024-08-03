package com.dxc.wba.xref.sso;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LastAccessTimeController {
	private final UserAccessRepository userAccessRepository;

	public LastAccessTimeController(UserAccessRepository userAccessRepository) {
		this.userAccessRepository = userAccessRepository;
	}

	@GetMapping("/last-accessed-timer/{username}")
	public ResponseEntity<String> getLastAccessedTimer(@PathVariable String username) {
		List<UserAccess> userAccessList = userAccessRepository.findByUsername(username);
		if (userAccessList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body("{\"session_status\": \"Session is expired\"}");
		}

		LocalDateTime currentTime = LocalDateTime.now();
		Map<String, Object> response = new HashMap<>();

		UserAccess userAccess = userAccessList.get(userAccessList.size() - 1); // Get the latest user access record
		LocalDateTime lastAccessedTime = userAccess.getIssueTime();
		Duration duration = Duration.between(lastAccessedTime, currentTime);
		long minutes = duration.toMinutes();

		if (minutes > 15) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body("{\"session_status\": \"Session expired due to user in-activity, please login again\",\"error\": \"401\"}");
		}

		// Calculating time left until timeout (5 minutes)
		long timeLeft = 15 - minutes;

//		 Updating the timestamp with the current time
		userAccess.setIssueTime(currentTime);
		userAccessRepository.save(userAccess);

		// Calculating the elapsed time in hours, minutes, and seconds
		long hours = duration.toHours();
		long elapsedMinutes = duration.toMinutesPart();
		long elapsedSeconds = duration.toSecondsPart();

		response.put("lastAccessedTime", lastAccessedTime.toString());
		response.put("message", "The API request was " + hours + " hours, " + elapsedMinutes + " minutes, "
				+ elapsedSeconds + " seconds ago");
		response.put("timeLeft", timeLeft);
		response.put("userType", userAccess.getUserType());

		// Converting the map to JSON string using Gson
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(response);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(jsonResponse);
	}

	@PostMapping("/user-access")
	public ResponseEntity<String> createUserAccess(@RequestParam String username) {
		List<UserAccess> existingUserAccessList = userAccessRepository.findByUsername(username);
		if (!existingUserAccessList.isEmpty()) {
			// Delete all existing user access records for the given username
			userAccessRepository.deleteAll(existingUserAccessList);
		}

		// Create a new user access record
		UserAccess newUserAccess = new UserAccess();
		newUserAccess.setUsername(username);
		newUserAccess.setIssueTime(LocalDateTime.now());
		userAccessRepository.save(newUserAccess);

		// Create a map for the JSON response
		Map<String, Object> response = new HashMap<>();
		response.put("message", "User access created successfully.");
		response.put("username", newUserAccess.getUsername());
		response.put("issueTime", newUserAccess.getIssueTime().toString());

		// Convert the map to JSON string using Gson
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(response);

		// Set the Content-Type header to application/json
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(jsonResponse, headers, HttpStatus.CREATED);
	}

//	@GetMapping("/last-accessed-time/{username}")
//	public ResponseEntity<String> getLastAccessedTime(@PathVariable String username) {
//		List<UserAccess> userAccessList = userAccessRepository.findByUsername(username);
//		if (userAccessList.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//					.body("{\"session_status\": \"Session is expired\"}");
//		}
//
//		LocalDateTime currentTime = LocalDateTime.now();
//		List<Map<String, Object>> responseList = new ArrayList<>();
//
//		for (UserAccess userAccess : userAccessList) {
//			LocalDateTime lastAccessedTime = userAccess.getIssueTime();
//			Duration duration = Duration.between(lastAccessedTime, currentTime);
//			long minutes = duration.toMinutes();
//
//			if (minutes > 5) {
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//						.body("{\"session_status\": \"Session is expired\"}");
//			}
//
//			// Calculate time left until timeout (5 minutes)
//			long timeLeft = 5 - minutes;
//
//			// Update the timestamp with the current time
//			userAccess.setIssueTime(currentTime);
//			userAccessRepository.save(userAccess);
//
//			// Calculate the elapsed time in hours, minutes, and seconds
//			long hours = duration.toHours();
//			long elapsedMinutes = duration.toMinutesPart();
//			long elapsedSeconds = duration.toSecondsPart();
//
//			// Create a map for the current user access record
//			Map<String, Object> userAccessMap = new HashMap<>();
//			userAccessMap.put("lastAccessedTime", lastAccessedTime.toString());
//			userAccessMap.put("timeLeft", timeLeft);
//			userAccessMap.put("message", "The API request was " + hours + " hours, " + elapsedMinutes + " minutes, "
//					+ elapsedSeconds + " seconds ago");
//			
//
//			responseList.add(userAccessMap);
//		}
//
//		// Convert the list to JSON string using Gson
//		Gson gson = new Gson();
//		String jsonResponse = gson.toJson(responseList);
//
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//				.body(jsonResponse);
//	}

//	@GetMapping("/last-accessed-times/{username}")
//	public String getLastAccessedTime(@PathVariable String username) {
//		UserAccess userAccess = userAccessRepository.findByUsername(username);
//		if (userAccess != null) {
//			LocalDateTime currentTime = LocalDateTime.now();
//			LocalDateTime issueTime = userAccess.getIssueTime();
//			Duration duration = Duration.between(issueTime, currentTime);
//			long minutes = duration.toMinutes();
//			if (minutes > 5) {
//				throw new UsernameAccessException("Username access time exceeded 5 minutes.");
//			} else {
//				userAccess.setLastAccessedTime(currentTime);
//				userAccessRepository.save(userAccess);
//				String result = "Accessed time: " + issueTime.toString() + ", Elapsed time: " + duration.toHours()
//						+ " hours, " + duration.toMinutesPart() + " minutes, " + duration.toSecondsPart() + " seconds";
//				return result;
//			}
//		} else {
//			throw new UsernameAccessException("No access record found for the username.");
//		}
//	}

//	@PostMapping("/user-access")
//	public String createUserAccess(@RequestParam String username) {
//		UserAccess userAccess = new UserAccess();
//		userAccess.setUsername(username);
//		userAccess.setIssueTime(LocalDateTime.now());
//		userAccessRepository.save(userAccess);
//		return "User access created successfully.";
//	}

}
