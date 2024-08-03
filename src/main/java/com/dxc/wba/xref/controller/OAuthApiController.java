package com.dxc.wba.xref.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.token.TokenService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dxc.wba.xref.exceptions.UnauthorizedException;
import com.dxc.wba.xref.login.ClientCredentials;
import com.dxc.wba.xref.repository.ClientDetailsRepository;
import com.dxc.wba.xref.sso.UserAccess;
import com.dxc.wba.xref.sso.UserAccessRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class OAuthApiController {

//	@Autowired
//	private TokenService tokenService;
	private static final Logger logger = LoggerFactory.getLogger(OAuthApiController.class);
	private final RestTemplate restTemplate;

//	@Value("${oauth.client.secret}") // Read client secret from application.properties
//	private String oauthClientSecret;

	public OAuthApiController() {
		this.restTemplate = new RestTemplate();
	}

	@Autowired
	private UserAccessRepository userAccessRepository;

	@Autowired
	private ClientDetailsRepository clientrepo;

	@PostMapping("/requestAccessToken")
	public ResponseEntity<Object> requestsTokens(@RequestParam("code") String code, HttpSession session)
			throws MalformedURLException {
//		String tokenUrl = "https://sso.walgreens.com/as/token.oauth2";
//		String jwksUrl = "https://sso.walgreens.com/pf/JWKS";
		
		String tokenUrl = "https://ssotest.walgreens.com/as/token.oauth2";
		String jwksUrl = "https://ssotest.walgreens.com/pf/JWKS";

		try {

//		headers.set("Authorization",
//				"Basic " + Base64.getEncoder().encodeToString(("XREF_DD:Ehn6iGGKi03PpqqtEAJGMey5nQ40hLTrYmDdKaoCsqGsrTbq5n0Bq48GxvKcw8XF").getBytes()));

			MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			HttpHeaders headers = new HttpHeaders();
//			ClientCredentials clientCredentials = clientrepo.findByServerName("PROD");
			ClientCredentials clientCredentials = clientrepo.findByServerName("PREPROD");
			headers.setBasicAuth(clientCredentials.getClientId(), clientCredentials.getClientSecret());

			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			body.add("grant_type", "authorization_code");
			body.add("code", code);
			body.add("redirect_uri", "https://aeurmsdms1lw010.corp.internal:8443/XREF-SSO/");
//			body.add("redirect_uri", "https://aeurmsdmp1lw006.corp.internal:8443/XREF-SSO/");

			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

//			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<Map> responseEntity = restTemplate.exchange(tokenUrl, HttpMethod.POST, requestEntity,
					Map.class);
			Map<String, Object> responseBody = responseEntity.getBody();

			// Extract the required values from the response body
//			String accessToken = (String) responseBody.get("access_token");
//			String tokenType = (String) responseBody.get("token_type");
//			int expiresIn = (int) responseBody.get("expires_in");
//			String refreshToken = (String) responseBody.get("refresh_token");
			String id_token = (String) responseBody.get("id_token");
			System.out.println("id_token" + id_token);

//			session.setAttribute("access_token", accessToken);
////			session.setAttribute("refresh_token", refreshToken); 
////			session.setAttribute("id_token", id_token);
//			session.setAttribute("issue_time", LocalDateTime.now());
//			session.setMaxInactiveInterval(1800);

			String decodedBody;
	        try {
	            String[] splitToken = id_token.split("\\.");
	            byte[] decodedBytes = Base64.getDecoder().decode(splitToken[1]);
	            decodedBody = new String(decodedBytes, StandardCharsets.UTF_8);
	        } catch (IllegalArgumentException e) {
	            System.err.println("Regular Base64 decoding failed for id_token. Trying Base64Url decoding.");
                String[] splitToken = id_token.split("\\.");
	            System.out.println(splitToken[1]);
	            byte[] decodedBytes = Base64.getUrlDecoder().decode(splitToken[1]);
	            decodedBody = new String(decodedBytes, StandardCharsets.UTF_8);
	        }
//			String[] splitToken = id_token.split("\\.");
//			splitToken[1] = splitToken[1].replace("-", "");
//			byte[] decodedBytes = Base64.getDecoder().decode(splitToken[1]);
//			String decodedBody = new String(decodedBytes, StandardCharsets.UTF_8);
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(decodedBody);
			String givenName = jsonNode.get("GivenName").asText();
			String sn = jsonNode.get("sn").asText();
			String mail = jsonNode.get("sub").asText();
			List<String> groups = objectMapper.readValue(jsonNode.get("Groups").toString(),
					new TypeReference<List<String>>() {
					});
			int admin = 7;
			int user = 5;

			List<UserAccess> existingUserAccessList = userAccessRepository.findByUsername(givenName);
			if (!existingUserAccessList.isEmpty()) {
				// Deleting all existing user access records for the given username
				userAccessRepository.deleteAll(existingUserAccessList);
			}

			// Create a new user access record
			UserAccess newUserAccess = new UserAccess();
			newUserAccess.setUsername(givenName);
			newUserAccess.setIssueTime(LocalDateTime.now());
			for (String group : groups) {
				if (group.contains("APP_IM_APP_XREF_DD_Access_Write_List")) {
					newUserAccess.setUserType("XREF_ADMIN");
					break;
				} else if (group.contains("APP_IM_APP_XREF_DD_Access_List")) {
					newUserAccess.setUserType("XREF_USER");
				}

			}
			newUserAccess.setIssueTime(LocalDateTime.now());
			userAccessRepository.save(newUserAccess);

			Map<String, Object> newResponseBody = new HashMap<>();

			// Fetching the JWK set from the provided URL
			JWKSet jwkSet = JWKSet.load(new URL(jwksUrl));

			// Creating a JWK source with the remote JWK set
			JWKSource keySource = new RemoteJWKSet(new URL(jwksUrl));

			// Creating a JWT processor
			ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();

			// Set the JWK key source for the JWT processor
			jwtProcessor.setJWSKeySelector(new JWSVerificationKeySelector<>(JWSAlgorithm.RS256, keySource));

			// Processing and validate the JWT token
			JWTClaimsSet claimsSet = jwtProcessor.process(id_token, null);

			// Printing the validated claims
			String subject = claimsSet.getSubject();
			String issuer = claimsSet.getIssuer();
			// Add more claims as needed
//
//			// Returning a success response with the validated claims
//			newResponseBody.put("userType", "JWT token is valid.\nSubject: " + subject + "\nIssuer: " + issuer);
//			newResponseBody.put("mail", mail);

			for (String group : groups) {
				if (group.contains("APP_IM_APP_XREF_DD_Access_Write_List")) {
					newResponseBody.put("returnCode", admin);
					newResponseBody.put("userType", "XREF_ADMIN");
					newResponseBody.put("mail", mail);
					newResponseBody.put("userName", givenName);
					newResponseBody.put("GivenName", givenName + " " + sn);
					break;
				} else if (group.contains("APP_IM_APP_XREF_DD_Access_List")) {
					newResponseBody.put("returnCode", user);
					newResponseBody.put("userType", "XREF_USER");
					newResponseBody.put("mail", mail);
					newResponseBody.put("userName", givenName);
					newResponseBody.put("GivenName", givenName + " " + sn);
					break;
				} else {
					// User is unauthorized
					throw new UnauthorizedException("User is unauthorized.");
				}
			}
			LocalDateTime currentTime = LocalDateTime.now();
			newResponseBody.put("issue_time", currentTime);

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setContentType(MediaType.APPLICATION_JSON);

			// Return the updated response body with the appropriate headers
			return new ResponseEntity<Object>(newResponseBody, responseHeaders, responseEntity.getStatusCode());
		} catch (RestClientException e) {
			e.printStackTrace();
			String errorMessage = "An error occurred while processing the request.";
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("error", errorMessage);

			ObjectMapper mapper = new ObjectMapper();
			try {
				String jsonError = mapper.writeValueAsString(errorResponse);
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.setContentType(MediaType.APPLICATION_JSON);
				return new ResponseEntity<>(jsonError, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (JsonProcessingException ex) {
				ex.printStackTrace();
				return new ResponseEntity<>("An error occurred while processing the error response.",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (java.text.ParseException | IOException | BadJOSEException | JOSEException e) {
			e.printStackTrace();
			String errorMessage = "Invalid or expired JWT token.";
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("error", "401");
			errorResponse.put("session_status", errorMessage);

			ObjectMapper mapper = new ObjectMapper();
			try {
				String jsonError = mapper.writeValueAsString(errorResponse);
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.setContentType(MediaType.APPLICATION_JSON);
				return new ResponseEntity<>(jsonError, responseHeaders, HttpStatus.UNAUTHORIZED);
			} catch (JsonProcessingException ex) {
				ex.printStackTrace();
				return new ResponseEntity<>("An error occurred while processing the error response.",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (UnauthorizedException e) {
			e.printStackTrace();
			String errorMessage = "User is unauthorized.";
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("error", "401");
			errorResponse.put("session_status", errorMessage);

			ObjectMapper mapper = new ObjectMapper();
			try {
				String jsonError = mapper.writeValueAsString(errorResponse);
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.setContentType(MediaType.APPLICATION_JSON);
				return new ResponseEntity<>(jsonError, responseHeaders, HttpStatus.UNAUTHORIZED);
			} catch (JsonProcessingException ex) {
				ex.printStackTrace();
				return new ResponseEntity<>("An error occurred while processing the error response.",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	

}
