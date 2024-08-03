package com.dxc.wba.xref.sso;

import java.time.LocalDateTime;

public class LastAccessedTimeResponse {
	private LocalDateTime lastAccessedTime;
	private long hours;
	private long minutes;
	private long seconds;
	private LocalDateTime sessionExpiration;

	public LastAccessedTimeResponse(LocalDateTime lastAccessedTime, long hours, long minutes, long seconds,
			LocalDateTime sessionExpiration) {
		this.lastAccessedTime = lastAccessedTime;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		this.sessionExpiration = sessionExpiration;
	}

	public LocalDateTime getLastAccessedTime() {
		return lastAccessedTime;
	}

	public void setLastAccessedTime(LocalDateTime lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}

	public long getHours() {
		return hours;
	}

	public void setHours(long hours) {
		this.hours = hours;
	}

	public long getMinutes() {
		return minutes;
	}

	public void setMinutes(long minutes) {
		this.minutes = minutes;
	}

	public long getSeconds() {
		return seconds;
	}

	public void setSeconds(long seconds) {
		this.seconds = seconds;
	}

	public LocalDateTime getSessionExpiration() {
		return sessionExpiration;
	}

	public void setSessionExpiration(LocalDateTime sessionExpiration) {
		this.sessionExpiration = sessionExpiration;
	}
}
