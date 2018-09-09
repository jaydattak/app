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

}
