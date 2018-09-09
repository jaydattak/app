package com.example.app.service;

import java.util.List;

import com.example.app.dto.TaskDto;

public interface TaskService {
	public List<TaskDto> getTaskList();

	public void addTask(TaskDto task);

}
