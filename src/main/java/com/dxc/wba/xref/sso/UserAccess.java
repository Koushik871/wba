package com.dxc.wba.xref.sso;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_access", schema = "xref")
public class UserAccess {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private LocalDateTime issueTime;

	private LocalDateTime lastAccessedTime;

	private String userType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(LocalDateTime issueTime) {
		this.issueTime = issueTime;
	}

	public LocalDateTime getLastAccessedTime() {
		return lastAccessedTime;
	}

	public void setLastAccessedTime(LocalDateTime lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
