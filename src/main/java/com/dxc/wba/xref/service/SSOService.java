package com.dxc.wba.xref.service;

import org.springframework.stereotype.Service;

import com.dxc.wba.xref.ddentity.SSOUser;

@Service
public class SSOService {

	public SSOUser authenticateUser(String email) {
		SSOUser user = getUserFromActiveDirectory(email);

		return user;
	}

	private SSOUser getUserFromActiveDirectory(String email) {
		// Implementing the logic to retrieve user details from Active Directory based
		// on the email

		SSOUser user = new SSOUser();
		user.setEmail(email);

		return user;
	}
}