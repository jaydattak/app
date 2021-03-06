package com.example.app.dto;

import java.util.Date;

public class ProjectDto {

	private int id;

	private String name;

	private Date startDate;

	private Date endDate;

	private int priority;

	private UserDto manager;

	private int noOfTasks;

	private int noOfCompletedTasks;

	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public UserDto getManager() {
		return manager;
	}

	public void setManager(UserDto manager) {
		this.manager = manager;
	}

	public int getNoOfTasks() {
		return noOfTasks;
	}

	public void setNoOfTasks(int noOfTasks) {
		this.noOfTasks = noOfTasks;
	}

	public int getNoOfCompletedTasks() {
		return noOfCompletedTasks;
	}

	public void setNoOfCompletedTasks(int noOfCompletedTasks) {
		this.noOfCompletedTasks = noOfCompletedTasks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof ProjectDto) {
			ProjectDto dto = (ProjectDto) obj;
			return dto.getId() == this.id;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return this.name != null ? this.name.hashCode() : super.hashCode();
	}

}
