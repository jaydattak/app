package com.example.app.dto;

import java.util.Date;

public class TaskDto {

	private int id;

	private String name;

	private ParentTaskDto parentTask;

	private ProjectDto project;

	private UserDto user;

	private Date startDate;

	private Date endDate;

	private int priority;

	private String status;

	private boolean isMainTask;

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

	public ParentTaskDto getParentTask() {
		return parentTask;
	}

	public void setParentTask(ParentTaskDto parentTask) {
		this.parentTask = parentTask;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ProjectDto getProject() {
		return project;
	}

	public void setProject(ProjectDto project) {
		this.project = project;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public boolean isMainTask() {
		return isMainTask;
	}

	public void setMainTask(boolean isMainTask) {
		this.isMainTask = isMainTask;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof TaskDto) {
			TaskDto taskDto = (TaskDto) obj;
			return taskDto.getId() == this.id;
		} else {
			return false;
		}

	}

	@Override
	public int hashCode() {
		return this.name != null ? this.name.hashCode() : super.hashCode();
	}
}
