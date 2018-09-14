package com.example.app.service;

import java.util.List;

import com.example.app.dto.TaskDto;

public interface TaskService {
	public List<TaskDto> getTaskList();

	public void addTask(TaskDto task);

	public void deleteTask(int id);

	public void updateTask(TaskDto project, int id);

	public List<TaskDto> searchTask(String searchText);

	public List<TaskDto> sortTasks(String sortBy);

	public List<TaskDto> getTaskListByProject(int id);

	public List<TaskDto> getTaskListByProjectWithSort(int id, String sortBy);

}
