package com.example.app.service;

import java.util.List;

import com.example.app.dto.TaskDto;
import com.example.app.exception.UserException;

public interface TaskService {
	public List<TaskDto> getTaskList();

	public void addTask(TaskDto task) throws UserException;

	public void deleteTask(int id) throws UserException;

	public void updateTask(TaskDto project, int id) throws UserException;

	public List<TaskDto> searchTask(String searchText);

	public List<TaskDto> sortTasks(String sortBy);

	public List<TaskDto> getTaskListByProject(int id);

	public List<TaskDto> getTaskListByProjectWithSort(int id, String sortBy);

}
