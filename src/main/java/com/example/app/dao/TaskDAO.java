package com.example.app.dao;

import java.util.List;

import com.example.app.entity.Task;

public interface TaskDAO {
	public List<Task> getTaskList();

	public void addTask(Task task);

	public void deleteTask(Task map);

	public void updateTask(Task map);

	public List<Task> searchTasks(String searchText);

	public List<Task> sortTasks(String flag);

	public List<Task> getTaskListByProject(int id);

	public List<Task> getTaskListByProjectWithSort(int id, String sortBy);

}
