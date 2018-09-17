package com.example.app.dto;

import com.example.app.exception.UserException;

public class ResponseMessage {

	private boolean status;
	private String message;
	private String reason;

	public ResponseMessage(boolean status, String message) {
		this.status = status;
		this.message = message;
	}

	public ResponseMessage(boolean status, String message, String reason) {
		this.status = status;
		this.message = message;
		this.reason = reason;
	}

	public ResponseMessage(boolean status, String message, UserException excp) {
		this.status = status;
		this.message = message;
		this.reason = excp.getReason();
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
