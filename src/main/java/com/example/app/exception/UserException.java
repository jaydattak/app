package com.example.app.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class UserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8605943188935529071L;

	private String reason;

	public UserException(String message) {
		super(message);
	}

	public UserException(String message, Exception e) {
		super(message);

		if (e instanceof DataIntegrityViolationException) {
			this.reason = "Incorrect Data for save or update";
		}
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
