package com.example.app.service;

import java.util.List;

import com.example.app.dto.ParentTaskDto;

public interface ParentTaskService {
	public List<ParentTaskDto> getTaskList();

	public void addTask(ParentTaskDto task);

	public void deleteTask(int id);

	public void updateTask(ParentTaskDto project, int id);

	public List<ParentTaskDto> searchTask(String searchText);

}
